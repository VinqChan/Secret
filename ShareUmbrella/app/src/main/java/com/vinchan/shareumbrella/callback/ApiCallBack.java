package com.vinchan.shareumbrella.callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2018/6/3/003.
 */

public interface ApiCallBack {
    void success(Object response);
    void fail();
}
