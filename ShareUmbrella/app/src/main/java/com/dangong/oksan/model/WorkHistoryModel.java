package com.dangong.oksan.model;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12/012.
 */

public class WorkHistoryModel {

    /**
     * code : 200
     * result : [{"time":"2018-06-09 12:11:14","longUmbrella":"10","shorUmbrella":"10","name":"香山商铺","alpenstock":"10","siteId":"111111","siteStatus":"1"},{"time":"2018-06-09 12:11:14","longUmbrella":"10","shorUmbrella":"10","name":"香山商铺","alpenstock":"10","siteId":"111111","siteStatus":"1"}]
     */

    private int code;
    private List<ResultBean> result;
    private boolean isSuccess;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isSuccess() {
        return code == 200;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * time : 2018-06-09 12:11:14
         * longUmbrella : 10
         * shorUmbrella : 10
         * name : 香山商铺
         * alpenstock : 10
         * siteId : 111111
         * siteStatus : 1
         */

        private String time;
        private String longUmbrella;
        private String shorUmbrella;
        private String name;
        private String alpenstock;
        private String siteId;
        private String siteStatus;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLongUmbrella() {
            return longUmbrella;
        }

        public void setLongUmbrella(String longUmbrella) {
            this.longUmbrella = longUmbrella;
        }

        public String getShorUmbrella() {
            return shorUmbrella;
        }

        public void setShorUmbrella(String shorUmbrella) {
            this.shorUmbrella = shorUmbrella;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlpenstock() {
            return alpenstock;
        }

        public void setAlpenstock(String alpenstock) {
            this.alpenstock = alpenstock;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getSiteStatus() {
            return siteStatus;
        }

        public void setSiteStatus(String siteStatus) {
            this.siteStatus = siteStatus;
        }
    }
}
