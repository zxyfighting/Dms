package com.faw.hq.dmp.spark.imp.dms.inter


import org.apache.spark.streaming.dstream.DStream

/**
  * @ program: Dms
  * @ description
  * @ author: ZhangXiuYun
  * @ create: 2020-01-13 17:26
  **/

object ImplOrders {

  //客户总到店量计算------根据处理后的数据，将结果累加
  def pvUpdate(rdd: DStream[(String, Long)]): DStream[(String, Long)] = {
    rdd.updateStateByKey {
      case (seq, buffer) => { //seq序列当前周期中数量对集合，buffer表缓冲当中的值，所谓的checkPoint
        val sumCount = seq.sum + buffer.getOrElse(0L)
        Option(sumCount) //表往缓存里边更新对值　　它需要返回一个Option
      }
    }
  }

}
