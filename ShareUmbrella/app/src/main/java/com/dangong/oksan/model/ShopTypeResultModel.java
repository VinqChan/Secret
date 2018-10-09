package com.dangong.oksan.model;

import java.util.List;

/**
 * Created by vinchan on 2018/10/9.
 */

public class ShopTypeResultModel {

    /**
     * result : [{"createTime":"2017-12-09 19:05:20","typeName":"公安局","id":4,"status":1}]
     * code : 200
     */

    private String code;
    private List<ResultBean> result;
    private boolean isSuccess ;
    private String message;
    public boolean isSuccess() {
        return code.equals("200");
    }
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
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
         * createTime : 2017-12-09 19:05:20
         * typeName : 公安局
         * id : 4
         * status : 1
         */

        private String createTime;
        private String typeName;
        private int id;
        private int status;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
