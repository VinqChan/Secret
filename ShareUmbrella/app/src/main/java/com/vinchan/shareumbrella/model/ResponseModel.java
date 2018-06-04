package com.vinchan.shareumbrella.model;

import java.io.Serializable;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class ResponseModel implements Serializable {
    private String code;
    private String message;
    private String result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
