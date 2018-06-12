package com.vinchan.shareumbrella.model;

/**
 * Created by Vinchan on 2018/6/3/003.
 */


public class ScannerModel {

    /**
     * code : 200
     * result : {"siteId":"1101","siteNum":"1"}
     */

    private int code;
    private Result result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        /**
         * siteId : 1101
         * siteNum : 1
         */

        private String siteId;
        private String siteNum;

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getSiteNum() {
            return siteNum;
        }

        public void setSiteNum(String siteNum) {
            this.siteNum = siteNum;
        }
    }

}



