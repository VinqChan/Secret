package com.vinchan.shareumbrella.callback;

/**
 * Created by Jian on 2018/6/12.
 */

public class ErrorModel {

    /**
     * code : 404
     * result : 用户或者密码不正确
     */

    private int code;
    private String result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
