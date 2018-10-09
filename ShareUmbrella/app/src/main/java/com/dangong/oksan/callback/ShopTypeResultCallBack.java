package com.dangong.oksan.callback;


import com.blankj.utilcode.util.LogUtils;
import com.dangong.oksan.model.LoginResult;
import com.dangong.oksan.model.ShopTypeResultModel;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public abstract class ShopTypeResultCallBack extends Callback<ShopTypeResultModel> {
    @Override
    public ShopTypeResultModel parseNetworkResponse(Response response, int id) throws Exception {
        LogUtils.e("[oksan] LoginResultCallBack="+response.body().toString());
        return new Gson().fromJson( response.body().string(), ShopTypeResultModel.class);
    }
}
