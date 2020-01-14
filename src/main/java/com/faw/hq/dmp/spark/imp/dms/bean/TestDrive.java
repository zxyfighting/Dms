package com.faw.hq.dmp.spark.imp.dms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @program: dmsSpark
 * @description
 * @author: ZhangXiuYun
 * @create: 2019-11-16 15:21
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDrive {
    private String seriesCode; //试驾车系id
    public String getSeriesCode() {
        return seriesCode;
    }
    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }
    public static String getHive(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\N;");

        return sb.toString();
    }
    public String jsonToLine() {
        StringBuilder sb = new StringBuilder();
        if (seriesCode==null||seriesCode.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(seriesCode).append(";");
        }
        return sb.toString();
    }

}