package com.faw.hq.dmp.spark.imp.dms.bean;

/**
 * @program: dmsSpark
 * @description
 * @author: ZhangXiuYun
 * @create: 2019-12-03 13:26
 **/
public class ResultDms {
    private String dataType;
    private String reportTime;
    private UserInfo userInfo;
    private DealerInfo dealerInfo;
    private ArrivalRecord arriveData;
    private TestDrive testDriveData;
    private VehicleDeliver vehicleData;
    private String returnType;         //回店原因
    private String refData;            //预留字段

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public DealerInfo getDealerInfo() {
        return dealerInfo;
    }

    public void setDealerInfo(DealerInfo dealerInfo) {
        this.dealerInfo = dealerInfo;
    }

    public ArrivalRecord getArriveData() {
        return arriveData;
    }

    public void setArriveData(ArrivalRecord arriveData) {
        this.arriveData = arriveData;
    }

    public TestDrive getTestDriveData() {
        return testDriveData;
    }

    public void setTestDriveData(TestDrive testDriveData) {
        this.testDriveData = testDriveData;
    }

    public VehicleDeliver getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(VehicleDeliver vehicleData) {
        this.vehicleData = vehicleData;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getRefData() {
        return refData;
    }

    public void setRefData(String refData) {
        this.refData = refData;
    }
    public String jsonToLine() {
        StringBuilder sb = new StringBuilder();
        sb.append((dataType==null ||dataType.trim().length()==0) ? "\\N;" : (dataType+";")).append((reportTime==null ||reportTime.trim().length()==0) ? "\\N;":
                (reportTime+";")).append(userInfo==null ? UserInfo.getHive():userInfo.jsonToLine()).append(dealerInfo==null ? DealerInfo.getHive():
                dealerInfo.jsonToLine()).append(arriveData==null ? ArrivalRecord.getHive(): arriveData.jsonToLine()).append(testDriveData==null ? TestDrive.getHive():
                testDriveData.jsonToLine()).append(vehicleData==null ? VehicleDeliver.getHive() :vehicleData.jsonToLine()).append(returnType==null ||returnType.trim().length()==0?
                "\\N;": returnType+";").append(refData==null ||refData.trim().length()==0 ? "\\N" :refData);

        return sb.append(";").append("dms").toString();
    }


}