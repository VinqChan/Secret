package com.dangong.oksan.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jian on 2018/9/29.
 */

public class SiteWorkLoggModel implements Serializable{

    /**
     * result : [{"alpenstock":"12","name":"五花马","siteSatus":"4","siteId":"0001","time":"2018-09-29 00:00:38","longUmbrella":"18","shorUmbrella":"22"}]
     * code : 200
     */

    private int code;
    private List<ResultBean> result;
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
         * alpenstock : 12
         * name : 五花马
         * siteSatus : 4
         * siteId : 0001
         * time : 2018-09-29 00:00:38
         * longUmbrella : 18
         * shorUmbrella : 22
         */

        private String alpenstock;
        private String name;
        private String siteSatus;
        private String siteId;
        private String time;
        private String longUmbrella;
        private String shorUmbrella;

        public String getAlpenstock() {
            return alpenstock;
        }

        public void setAlpenstock(String alpenstock) {
            this.alpenstock = alpenstock;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSiteSatus() {
            return siteSatus;
        }

        public void setSiteSatus(String siteSatus) {
            this.siteSatus = siteSatus;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

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
    }
}
