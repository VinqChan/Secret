package com.vinchan.shareumbrella.api;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.vinchan.shareumbrella.callback.ApiCallBack;
import com.vinchan.shareumbrella.callback.ResultCallBack;
import com.vinchan.shareumbrella.constants.Constants;
import com.vinchan.shareumbrella.model.Result;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class ApiUtils {
    public static void getRegisterCode(String phone, final ApiCallBack callBack) {
        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .url(Constants.SERVICE_BASE_URL + "/msgcode/registercode")
                .build()
                .execute(new ResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("服务异常，请稍后再试！");
                    }

                    @Override
                    public void onResponse(Result response, int id) {
                        LogUtils.d("[oksan]"+response.getMessage());
                        if (response.isSuccess()) {
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort("服务异常，请稍后再试！");
                        }
                    }
                });
    }

}
