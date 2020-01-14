package com.faw.hq.dmp.spark.imp.dms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @program: dmsSpark
 * @description
 * @author: ZhangXiuYun
 * @create: 2019-12-02 21:07
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerInfo {
    private String dealerCode; //经销商编码
    private String dealerName; //经销名称

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    public static String getHive(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\N;").append("\\N;");

        return sb.toString();
    }
    public String jsonToLine() {
        StringBuilder sb = new StringBuilder();
        if (dealerCode==null||dealerCode.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(dealerCode).append(";");
        }
        if (dealerName==null||dealerName.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(dealerName).append(";");
        }
        return sb.toString();
    }
}
