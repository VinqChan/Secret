package com.dangong.oksan.model;

/**
 * Created by Jian on 2018/6/12.
 */

public class SiteModel {

    /**
     * code : 200
     * result : {"longUmbrella":"10","shorUmbrella":"10","alpenstock":"12"}
     */

    private int code;
    private ResultBean result;
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * longUmbrella : 10
         * shorUmbrella : 10
         * alpenstock : 12
         */

        private String longUmbrella;
        private String shorUmbrella;
        private String alpenstock;

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

        public String getAlpenstock() {
            return alpenstock;
        }

        public void setAlpenstock(String alpenstock) {
            this.alpenstock = alpenstock;
        }
    }
}
