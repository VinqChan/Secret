package com.dangong.oksan.model;

/**
 * Created by Administrator on 2018/6/12/012.
 */

public class WorkHistoryRequestModel {
    String limit ;     //每页多少条数据
    String offset ;   //每页的偏移量(本页当前有多少条)

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
