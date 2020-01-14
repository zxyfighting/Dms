package com.faw.hq.dmp.spark.imp.dms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @program: dmsSpark
 * @description
 * @author: ZhangXiuYun
 * @create: 2019-12-02 21:06
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private String  userId;  //客户id
    private String  phoneNo; //手机号
    private String  userName; //客户名称
    private String  faceId;   //从数字化门店获取的faceId
    private String  idType;   //证件类型
    private String  idNo;     //证件编码

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public static String getHive(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\N;").append("\\N;").append("\\N;").append("\\N;").append("\\N;").append("\\N;");

        return sb.toString();
    }
    public String jsonToLine() {
        StringBuilder sb = new StringBuilder();
        if (userId==null||userId.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(userId).append(";");
        }
        if (phoneNo==null||phoneNo.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(phoneNo).append(";");
        }
        if (userName==null||userName.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(userName).append(";");
        }
        if (faceId==null||faceId.trim().length()==0) {
            sb.append("\\N;");
        } else {
            sb.append(faceId).append(";");
        }
        if (idType==null||idType.trim().length()==0) {
            sb.append("\\N").append(";");
        } else {
            sb.append(idType).append(";");
        }
        if (idNo==null||idNo.trim().length()==0) {
            sb.append("\\N");
        } else {
            sb.append(idNo);
        }
        return sb.toString();
    }

}
