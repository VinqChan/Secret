package com.vinchan.shareumbrella.api;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.vinchan.shareumbrella.callback.ApiCallBack;
import com.vinchan.shareumbrella.callback.LoginResultCallBack;
import com.vinchan.shareumbrella.callback.ResultCallBack;
import com.vinchan.shareumbrella.constants.Constants;
import com.vinchan.shareumbrella.model.LoginResult;
import com.vinchan.shareumbrella.model.ResponseModel;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

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

//        //客户端接收到token
//        String token ="eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiIxNTI3ODU3NjUyMDY4IiwiZXhwIjoxNTI4NDYyNDUyLCJpYXQiOjE1Mjc4NTc2NTJ9.LXB2eGFGZcYS4P1vd-6KxFL9bv9PWj4td4SgYB4VOTJ2ZwSN1mVPNR7m1j2lS2wmmyQUVZZM3OImSsvDUCvg2g";
//        String salt ="1527857652068";  //randomKey
//
//        Person person = new Person();
//        person.setAge("17");
//        person.setName("tantan");
//
//        String jsonString = JSON.toJSONString(person);
//        String encode = new Base64SecurityAction().doAction(jsonString);
//        String md5 = MD5Util.encrypt(encode+salt);
//
//        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
//        baseTransferEntity.setObject(encode);
//        baseTransferEntity.setSign(md5);
//        System.out.println(JSON.toJSONString(baseTransferEntity));
//        //MediaType  设置Content-Type 标头中包含的媒体类型值
//        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
//                , json);
//        .addHeader("content-type", "application/json;charset:utf-8")
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
}
