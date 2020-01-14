package com.faw.hq.dmp.spark.imp.dms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @program: dmsSpark
 * @description
 * @author: ZhangXiuYun
 * @create: 2019-11-16 15:18
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrivalRecord {

    private Boolean  firstArrive; //是否是初次到店

    public Boolean getFirstArrive() {
        return firstArrive;
    }

    public void setFirstArrive(Boolean firstArrive) {
        this.firstArrive = firstArrive;
    }

    public static String getHive(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\N;");

        return sb.toString();
    }
    public String jsonToLine() {
        StringBuilder sb = new StringBuilder();
        if (firstArrive==null) {
            sb.append("\\N;");
        } else {
            sb.append(firstArrive).append(";");
        }
        return sb.toString();
    }
}