package com.dangong.oksan.model;

/**
 * Created by vinchan on 2018/9/29.
 */


public class SiteWorkLoggRequest {
    private String limit ; //每页多少条数据
    private String offset ; //每页的偏移量(本页当前有多少条)

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}