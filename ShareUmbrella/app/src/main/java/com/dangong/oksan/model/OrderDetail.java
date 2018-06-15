package com.dangong.oksan.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vinchan on 2017/10/14.
 */

public class OrderDetail implements Serializable {

    private List<OrderDetailItem> result;
    private String dealFlag;

    public void setResult(List<OrderDetailItem> result) {
        this.result = result;
    }

    public List<OrderDetailItem> getResult() {
        return result;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public class OrderDetailItem implements Serializable{
        private String inspectProjectId;
        private String projectName;
        private String projectCycle;
        private String deviceTypeId;
        private String deviceId;
        private String deviceName;
        private String projectRemarks;
        private int isCheck;
        private String workOrderId;
        private String isFlag ;

        public String getIsFlag() {
            return isFlag;
        }

        public void setIsFlag(String isFlag) {
            this.isFlag = isFlag;
        }

        public String getWorkOrderId() {
            return workOrderId;
        }

        public void setWorkOrderId(String workOrderId) {
            this.workOrderId = workOrderId;
        }

        public int getIsCheck() {
            return isCheck;
        }

        public void setIsCheck(int isCheck) {
            this.isCheck = isCheck;
        }

        public void setInspectProjectId(String inspectProjectId) {
            this.inspectProjectId = inspectProjectId;
        }
        public String getInspectProjectId() {
            return inspectProjectId;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
        public String getProjectName() {
            return projectName;
        }

        public void setProjectCycle(String projectCycle) {
            this.projectCycle = projectCycle;
        }
        public String getProjectCycle() {
            return projectCycle;
        }

        public void setDeviceTypeId(String deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }
        public String getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }
        public String getDeviceName() {
            return deviceName;
        }

        public void setProjectRemarks(String projectRemarks) {
            this.projectRemarks = projectRemarks;
        }
        public String getProjectRemarks() {
            return projectRemarks;
        }

    }

}
