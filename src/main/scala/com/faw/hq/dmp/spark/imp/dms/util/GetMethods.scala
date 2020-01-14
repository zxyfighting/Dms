package com.faw.hq.dmp.spark.imp.dms.util

import java.util.{Calendar, Date}

import com.fasterxml.jackson.databind.ObjectMapper
import com.faw.hq.dmp.spark.imp.dms.bean.ResultDms


/**
  * author:zhangxiuyun
  * purpose：存放方法
  */
object GetMethods {

  /**
  * json解析方法
  *
  */
 def getDmsResult(json:String): String ={
   val objectMapper = new ObjectMapper
   val visitInfo = objectMapper.readValue(json, classOf[ResultDms])
    visitInfo.jsonToLine()
 }

  def  getSecondsNextEarlyMorning() ={
    var cal = Calendar.getInstance()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    // 改成这样就好了
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.MILLISECOND, 0)
    (cal.getTimeInMillis() - System.currentTimeMillis())
  }


  def main(args: Array[String]): Unit = {
    val json="{\"refData\":\"WhtQymC\",\"userInfo\":{\"faceId\":\"66897441\",\"idNo\":\"xWHxv\",\"idType\":\"fhLzC\",\"phoneNo\":\"02047545951\",\"userId\":\"9331711174\",\"userName\":\"CECzGX\"},\"dealerInfo\":{\"dealerCode\":\"7sdYSKd\",\"dealerName\":\"JBULQzm\"},\"vehicleData\":{\"productCode\":\"yBJzpRnaey\"},\"dataType\":\"book\",\"reportTime\":\"2019/11/14 19:19:58\"}"
    val json1="{\"refData\":\"string\",\"userInfo\":{\"idType\":\"string\",\"faceId\":\"string\",\"userName\":\"string\",\"userId\":\"string\",\"idNo\":\"string\",\"phoneNo\":\"string\"},\"dealerInfo\":{\"dealerName\":\"string\",\"dealerCode\":\"string\"},\"dataType\":\"arrivalRecord\",\"arriveData\":{\"firstArrive\":true},\"reportTime\":1577160544000}"
    val json2="{\"userInfo\":{\"userId\":\"string\",\"phoneNo\":\"string\",\"userName\":\"string\",\"faceId\":\"string\",\"idType\":\"string\",\"idNo\":\"string\"},\"dealerInfo\":{\"dealerCode\":\"zt\",\"dealerName\":\"string\"},\"reportTime\":\"2019-12-24 04:16:53\",\"refData\":\"string\",\"dataType\":\"vehicleDeliver\",\"vehicleData\":{\"productCode\":\"string\"}}"
    val json3="{\"userInfo\":{\"userId\":\"string\",\"phoneNo\":\"string\",\"userName\":\"string\",\"faceId\":\"string\",\"idType\":\"string\",\"idNo\":\"string\"},\"dealerInfo\":{\"dealerCode\":\"zt\",\"dealerName\":\"string\"},\"reportTime\":\"2019-12-24 04:16:28\",\"refData\":\"string\",\"dataType\":\"book\",\"vehicleData\":{\"productCode\":\"string\"}}"
    val objectMapper = new ObjectMapper
    val visitInfo = objectMapper.readValue(json3, classOf[ResultDms])
    println(getSecondsNextEarlyMorning())
    System.out.println(visitInfo.jsonToLine())
  }






}


