package com.faw.hq.dmp.spark.imp.dms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Field;

/**
 * @program: dmsSpark
 * @description
 * @author: ZhangXiuYun
 * @create: 2019-11-16 15:25
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDeliver {
    private String productCode ;//车辆产品编码

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public static String getHive(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\N;");
        return sb.toString();
    }
    public String jsonToLine() {
        StringBuilder sb = new StringBuilder();
        if (productCode==null||productCode.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(productCode).append(";");
        }
        return sb.toString();
    }



}
