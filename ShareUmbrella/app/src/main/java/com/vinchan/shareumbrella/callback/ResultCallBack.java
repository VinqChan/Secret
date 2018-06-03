package com.vinchan.shareumbrella.callback;


import com.google.gson.Gson;
import com.vinchan.shareumbrella.model.Result;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2018/6/3/003.
 */

public abstract class ResultCallBack extends Callback<Result> {
    @Override
    public Result parseNetworkResponse(Response response, int id) throws Exception {
        return new Gson().fromJson( response.body().string(), Result.class);
    }
}
