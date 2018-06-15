package com.dangong.oksan.callback;


import com.blankj.utilcode.util.LogUtils;
import com.dangong.oksan.model.LoginResult;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public abstract class LoginResultCallBack extends Callback<LoginResult> {
    @Override
    public LoginResult parseNetworkResponse(Response response, int id) throws Exception {
        LogUtils.e("[oksan] LoginResultCallBack="+response.body().toString());
        return new Gson().fromJson( response.body().string(), LoginResult.class);
    }
}
