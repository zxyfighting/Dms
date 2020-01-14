package com.faw.hq.dmp.spark.imp.dms.realize

import com.faw.hq.dmp.spark.imp.dms.contanst.KafkaParams
import com.faw.hq.dmp.spark.imp.dms.inter.{ImplJDBC, ImplOrders, Implkafka}
import com.faw.hq.dmp.spark.imp.dms.util._
import org.apache.log4j.Logger
import org.apache.spark.{ SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.DStream

/**
  * program: dmsSpark
  * description 采集从dms推送到kafka中的数据
  * author: ZhangXiuYun
  * create: 2019-11-16 14:40
  **/
object KafkaConsumer {
  private val logger: Logger = Logger.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    while (true) {
      //配置spark上下文环境对象
      val processInterval = 59
      val conf = new SparkConf().setAppName("dms").setMaster("local[*]")
      val sc = new SparkContext(conf)
      //设置采集器每十秒批次拉取数据
      val sparksc = new StreamingContext(sc, Seconds(processInterval))
      //利用checkpoint作为容错处理
      sparksc.sparkContext.setCheckpointDir(KafkaParams.chkDir)

      // 初始化 Redis 连接池
      JedisPoolUtils.makePool(RedisConfig("prod.dbaas.private", 16359, 30000, "realtime123", 1000, 100, 50))
      val kafkaStream = KafkaRedisUtils.createDirectStream(sparksc, KafkaParams.kafkaParams1, KafkaParams.module, KafkaParams.groupid, KafkaParams.topics)

      //将offset交给redis维护
      Implkafka.getOffset(KafkaParams.module,KafkaParams.groupid,kafkaStream)
      //解析json数据
      val dmsDestream: DStream[String] = kafkaStream.map(_.value()).map(rdd => {
        val authInfo = GetMethods.getDmsResult(rdd)
        authInfo
      })
      //由于resultDestream这个RDD用的频繁，减少数据计算量，用cache基于内存的存储数据，便于计算
      dmsDestream.cache()

      /**
        *
        * 将数据解析，并根据时间进行追加到hdfs上
        *
        */
      //将数据追加到hdfs上封装
      dmsDestream.foreachRDD(rdd => {
        Implkafka.toHDFS(rdd)
      })

      //统计一天客户总到店数
      val dmsDe: DStream[(String, Long)] = dmsDestream.map(rdd => (KafkaParams.dateString, 1L))
      val totalDestream: DStream[(String, Long)] =ImplOrders.pvUpdate(dmsDe)
      //将统计的客户总到店数写入mysql中
      val sql = "replace into dmp_behavior_dms_customers(dms_customers_dt,dms_customers_amounts,create_time,update_time) values(?,?,?,?)"
      ImplJDBC.puvCounts(sql,totalDestream)

      //每天客户初次到店建档人数
      val arriveDestream: DStream[String] = dmsDestream.filter(rdd => {
        val strings = rdd.split(";")
        strings(10).equals("true")
      })
      val arrDes: DStream[(String, Long)] = arriveDestream.map(rdd => (KafkaParams.dateString, 1L))
      val totalArriveDestream: DStream[(String, Long)] =ImplOrders.pvUpdate(arrDes)
      //将数据写入mysql中
      val sql1 = "replace into dmp_behavior_dms_first_amounts(dms_first_arrive_dt,dms_first_arrive_amounts,create_time,update_time) values(?,?,?,?)"
      ImplJDBC.puvCounts(sql1,totalArriveDestream)

      //每天客户到店下单数
      var totalOrderDestream: DStream[(String, Long)] = dmsDestream.filter(rdd => {
        val strings = rdd.split(";")
        strings(0).equals("book")
      }).map(rdd => (KafkaParams.dateString, 1))
      var totalOrderDestream1: DStream[(String, Long)] =  ImplOrders.pvUpdate(totalOrderDestream)
      val sql2 = "replace into dmp_behavior_dms_order_amounts(dms_order_dt,dms_order_amounts,create_time,update_time) values(?,?,?,?)"
      ImplJDBC.puvCounts(sql2,totalOrderDestream1)

      //开启采集器
      sparksc.start()
      sparksc.awaitTerminationOrTimeout(GetMethods.getSecondsNextEarlyMorning())
      sparksc.stop(false, true)
    }
  }
}
