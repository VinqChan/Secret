package com.dangong.oksan.callback;


import com.blankj.utilcode.util.LogUtils;
import com.dangong.oksan.model.LoginResult;
import com.dangong.oksan.model.NearShopModel;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public abstract class NearShopResultCallBack extends Callback<NearShopModel> {
    @Override
    public NearShopModel parseNetworkResponse(Response response, int id) throws Exception {
        LogUtils.e("[oksan] NearShopResultCallBack="+response.body().toString());
        return new Gson().fromJson( response.body().string(), NearShopModel.class);
    }
}
