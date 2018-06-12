package com.vinchan.shareumbrella.api;

import android.util.Base64;
import android.util.Log;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.vinchan.shareumbrella.callback.ApiCallBack;
import com.vinchan.shareumbrella.callback.LoginResultCallBack;
import com.vinchan.shareumbrella.callback.ResultCallBack;
import com.vinchan.shareumbrella.constants.Constants;
import com.vinchan.shareumbrella.model.BaseTransferEntity;
import com.vinchan.shareumbrella.model.LoginResult;
import com.vinchan.shareumbrella.model.ResponseModel;
import com.vinchan.shareumbrella.model.ScannerModel;
import com.vinchan.shareumbrella.model.ScannerRequestModel;
import com.vinchan.shareumbrella.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class ApiUtils {

    /**
     * 注册发送验证码接口
     * @param phone
     * @param callBack
     */
    public static void getRegisterCode(String phone, final ApiCallBack callBack) {

        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .url(Constants.SERVICE_BASE_URL + "/msgcode/registercode")
                .build()
                .execute(new ResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("[oksan] {getRegisterCode}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(ResponseModel response, int id) {
                        LogUtils.d("[oksan] {getRegisterCode}" + response.getResult());
                        if (response.isSuccess()) {
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getResult());
                        }
                    }
                });
    }

    /**
     * 注册
     * @param phone
     * @param code
     * @param password
     * @param inviteCode
     * @param roleId
     * @param province
     * @param city
     * @param callBack
     */
    public static void register(String phone, String code, String password, String inviteCode, String roleId, String province, String city, final ApiCallBack callBack) {

        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .addParams("code", code)
                .addParams("password", password)
                .addParams("inviteCode", inviteCode)
                .addParams("roleId", roleId)
                .addParams("province", province)
                .addParams("city", city)
                .url(Constants.SERVICE_BASE_URL + "/appuser/register")
                .build()
                .execute(new ResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {register}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(ResponseModel response, int id) {
                        LogUtils.d("[oksan] {register}" + response.getResult());
                        if (response.isSuccess()) {
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getResult());
                        }
                    }
                });
    }

    /**
     * 登录发送验证码接口
     * @param phone
     * @param callBack
     */
    public static void logincode(String phone, final ApiCallBack callBack) {

        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .url(Constants.SERVICE_BASE_URL + "/msgcode/logincode")
                .build()
                .execute(new ResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {logincode}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(ResponseModel response, int id) {
                        LogUtils.d("[oksan] {logincode}" + response.getResult());
                        if (response.isSuccess()) {
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getResult());
                        }
                    }
                });
    }

    /**
     * 修改密码发送验证码接口
     * @param phone
     * @param callBack
     */
    public static void modifycode(String phone, final ApiCallBack callBack) {

        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .url(Constants.SERVICE_BASE_URL + "/msgcode/modifycode")
                .build()
                .execute(new ResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {logincode}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(ResponseModel response, int id) {
                        LogUtils.d("[oksan] {logincode}" + response.getResult());
                        if (response.isSuccess()) {
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getResult());
                        }
                    }
                });
    }

    /**
     * 修改密码
     * @param phone
     * @param code
     * @param password
     * @param callBack
     */
    public static void psdmodify(String phone, String code,String password,final ApiCallBack callBack) {

        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .addParams("code", code)
                .addParams("password", password)
                .url(Constants.SERVICE_BASE_URL + "/appuser/psdmodify")
                .build()
                .execute(new ResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {logincode}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(ResponseModel response, int id) {
                        LogUtils.d("[oksan] {logincode}" + response.getMessage());
                        if (response.isSuccess()) {
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 验证码登录
     * @param phone
     * @param code
     * @param callBack
     */
    public static void codelogin(String phone, String code, final ApiCallBack callBack) {

        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .addParams("code", code)
                .url(Constants.SERVICE_BASE_URL + "/appuser/codelogin")
                .build()
                .execute(new LoginResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {logincode}" + call);
                        ToastUtils.showShort(e.getMessage());
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(LoginResult response, int id) {
                        LogUtils.d("[oksan] {logincode}" + response.getMessage());
                        if (response.isSuccess()) {
                            Constants.RANDOM_KEY =response.getResult().getRandomKey();
                            Constants.TOKEN =response.getResult().getToken();
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 账号密码登录
     * @param phone
     * @param password
     * @param callBack
     */
    public static void pswlogin(String phone, String password, final ApiCallBack callBack) {

        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .addParams("password", password)
                .url(Constants.SERVICE_BASE_URL + "/appuser/psdlogin")
                .build()
                .execute(new LoginResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {logincode}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(LoginResult response, int id) {
                        LogUtils.d("[oksan] {logincode}" + response.getMessage());
                        if (response.isSuccess()) {
                            Constants.RANDOM_KEY =response.getResult().getRandomKey();
                            Constants.TOKEN =response.getResult().getToken();
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
                        }
                    }
                });
    }

    public static void scanner(String tdCode, final ApiCallBack callBack) {
        String token =Constants.TOKEN;
        String salt =Constants.RANDOM_KEY;

        ScannerRequestModel model = new ScannerRequestModel();
        model.setTdCode(tdCode);

        String jsonString = new Gson().toJson( model);
        String encode =  Base64.encodeToString(jsonString.getBytes(), Base64.DEFAULT).replace("\n","");
        String md5 = MD5Util.encrypt(encode+salt);
        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);
        String json = new Gson().toJson(baseTransferEntity);
        LogUtils.d("-------json="+json);

        final Request request = new Request.Builder()
                .url(Constants.SERVICE_BASE_URL + "/site/scan")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+token)
                .post(RequestBody.create(MediaType.parse("application/json"),json))
                .build();

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              LogUtils.e(request.body().toString());
                ScannerModel model=  new Gson().fromJson( response.body().string(), ScannerModel.class);
                callBack.success(model);
                if (model != null) {
                    Constants.SITEID = model.getResult().getSiteId();
                }
            }
        });

    }
}
