package com.dangong.oksan.callback;


import com.blankj.utilcode.util.LogUtils;
import com.dangong.oksan.model.ResponseModel;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public abstract class ResultCallBack extends Callback<ResponseModel> {
    @Override
    public ResponseModel parseNetworkResponse(Response response, int id) throws Exception {
        LogUtils.e("[oksan] response="+response.body().toString());
        return new Gson().fromJson( response.body().string(), ResponseModel.class);
    }
}
