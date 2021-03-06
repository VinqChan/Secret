package com.dangong.oksan.model;

import java.io.Serializable;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class LoginResult implements Serializable {
    private String code;
    private String message;
    private Result result;
    private boolean isSuccess ;

    public boolean isSuccess() {
        return code.equals("200");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public class Result implements Serializable{
        private String randomKey;
        private String token;
        private String account;
        private String name;
        private String phone;
        private String email;
        private String province;
        private String city;
        private String status;
        private String roleId;//17-景点管理员 18-维护人员 19-导游
        private String checkState; // 0-待审核 1-审核通过 2-审核不通过 3-未提交审核
        private String workNo;

        public String getWorkNo() {
            return workNo;
        }

        public void setWorkNo(String workNo) {
            this.workNo = workNo;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getCheckState() {
            return checkState;
        }

        public void setCheckState(String checkState) {
            this.checkState = checkState;
        }

        public String getRandomKey() {
            return randomKey;
        }

        public void setRandomKey(String randomKey) {
            this.randomKey = randomKey;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
