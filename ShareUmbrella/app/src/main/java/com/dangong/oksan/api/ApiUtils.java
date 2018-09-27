package com.dangong.oksan.api;

import android.util.Base64;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.callback.LoginResultCallBack;
import com.dangong.oksan.callback.NearShopResultCallBack;
import com.dangong.oksan.callback.ResultCallBack;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.model.BaseTransferEntity;
import com.dangong.oksan.model.GetNearShopRequestModel;
import com.dangong.oksan.model.LoginResult;
import com.dangong.oksan.model.NearShopModel;
import com.dangong.oksan.model.ResponseModel;
import com.dangong.oksan.model.ScannerModel;
import com.dangong.oksan.model.ScannerRequestModel;
import com.dangong.oksan.model.ShopModel;
import com.dangong.oksan.model.SiteIdRequestModel;
import com.dangong.oksan.model.SiteModel;
import com.dangong.oksan.model.StockModel;
import com.dangong.oksan.model.WorkHistoryModel;
import com.dangong.oksan.model.WorkHistoryRequestModel;
import com.dangong.oksan.util.MD5Util;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class ApiUtils {

    /**
     * 注册发送验证码接口
     *
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
     *
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
     *
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
     *
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
     *
     * @param phone
     * @param code
     * @param password
     * @param callBack
     */
    public static void psdmodify(String phone, String code, String password, final ApiCallBack callBack) {

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
     *
     * @param phone
     * @param code
     * @param callBack
     */
    public static void codelogin(final String phone, String code, final ApiCallBack callBack) {

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
                            Constants.RANDOM_KEY = response.getResult().getRandomKey();
                            Constants.TOKEN = response.getResult().getToken();
                            Constants.PHONE = phone;
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
     *
     * @param phone
     * @param password
     * @param callBack
     */
    public static void pswlogin(final String phone, String password, final ApiCallBack callBack) {

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
                            Constants.RANDOM_KEY = response.getResult().getRandomKey();
                            Constants.TOKEN = response.getResult().getToken();
                            Constants.PHONE = phone;
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
                        }
                    }
                });
    }


    /**
     * 添加店铺
     *
     * @param callBack
     */
    public static void addShop(ShopModel model, final ApiCallBack callBack) {

        final Request request = getRequest(model, "/add/shop");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body().toString());

                ResponseModel model = new Gson().fromJson(response.body().string(), ResponseModel.class);

                if (model.isSuccess()) {
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 查看上期末站点数量
     *
     * @param callBack
     */
    public static void checkgoods(String siteId, final ApiCallBack callBack) {
        SiteIdRequestModel model = new SiteIdRequestModel();
        model.setSiteId(siteId);
        final Request request = getRequest(model, "/site/checkgoods");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body().toString());

                SiteModel model = new Gson().fromJson(response.body().string(), SiteModel.class);

                if (model.isSuccess()) {
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 撤销站点
     *
     * @param callBack
     */
    public static void removeSite(String siteId, final ApiCallBack callBack) {
        SiteIdRequestModel model = new SiteIdRequestModel();
        model.setSiteId(siteId);
        final Request request = getRequest(model, "/site/destory");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body().toString());

                ResponseModel model = new Gson().fromJson(response.body().string(), ResponseModel.class);

                if (model.isSuccess()) {
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 导游获取邀请码
     *
     * @param callBack
     */
    public static void getinvitecode(final ApiCallBack callBack) {
        SiteIdRequestModel model = new SiteIdRequestModel();
        model.setSiteId("");
        final Request request = getRequest(model, "/appuser/getinvitecode");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body().toString());

                ResponseModel model = new Gson().fromJson(response.body().string(), ResponseModel.class);

                if (model.isSuccess()) {
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 进货/减货/汇报表
     *
     * @param model
     * @param type
     * @param callBack
     */
    public static void stock(StockModel model, int type, final ApiCallBack callBack) {
        String url = "";
        if (type == 0) {
            url = "/site/outstock";
        } else if (type == 1) {
            url = "/site/stock";
        } else {
            url = "/site/report";
        }
        final Request request = getRequest(model, url);

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body().toString());

                ResponseModel model = new Gson().fromJson(response.body().string(), ResponseModel.class);

                if (model.isSuccess()) {
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 扫码获取siteid
     *
     * @param tdCode
     * @param callBack
     */
    public static void scanner(String tdCode, final ApiCallBack callBack) {
        ScannerRequestModel model = new ScannerRequestModel();
        model.setTdCode(tdCode);
        final Request request = getRequest(model, "/site/scan");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body().toString());

                ScannerModel model = new Gson().fromJson(response.body().string(), ScannerModel.class);

                if (model.isSuccess()) {
                    if (model != null) {
                        Constants.SITEID = model.getResult().getSiteId();
                        Constants.SNCODE = model.getResult().getSiteNum();
                    }
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 查看工作历史记录
     *
     * @param limit
     * @param offset
     * @param callBack
     */
    public static void getWorkHistory(String limit, String offset, final ApiCallBack callBack) {
        WorkHistoryRequestModel model = new WorkHistoryRequestModel();
        model.setLimit(limit);
        model.setOffset(offset);
        final Request request = getRequest(model, "/site/logging");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body().toString());

                WorkHistoryModel model = new Gson().fromJson(response.body().string(), WorkHistoryModel.class);

                if (model.isSuccess()) {
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    public static void getNearShop(double longitude, double latitude, int range,String city,final ApiCallBack callBack) {
        HashMap<String, String> hearder = new HashMap<>();
        hearder.put("Content-Type", "application/form-data");
        hearder.put("Authorization", "Bearer " + Constants.TOKEN);
        OkHttpUtils
                .post()
                .headers(hearder)
                .addParams("longitude",longitude+"")
                .addParams("latitude", latitude+"")
                .addParams("range", range+"")
                .addParams("city", city)
                .url(Constants.SERVICE_BASE_URL + "/shop/getNearShop")
                .build()
                .execute(new NearShopResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {getNearShop}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(NearShopModel response, int id) {
                        LogUtils.d("[oksan] {getNearShop}" + response.getResult());
                        if (response.isSuccess()) {
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
                        }
                    }
                });
//        GetNearShopRequestModel model = new GetNearShopRequestModel();
//        model.setCity(city);
//        model.setLatitude(latitude);
//        model.setLongitude(longitude);
//        model.setRange(range);
//        final Request request = getRequest(model, "shop/getNearShop");
//
//        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                LogUtils.e(e.toString());
//                ToastUtils.showShort("服务异常，请稍后再试！");
//                callBack.fail();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                LogUtils.e(request.body());
//
//                NearShopModel model = new Gson().fromJson(response.body().string(), NearShopModel.class);
//
//                if (model.isSuccess()) {
//                    callBack.success(model);
//                } else {
//                    ToastUtils.showShort(model.getMessage());
//                    callBack.fail();
//                }
//
//            }
//        });

    }


    public static BaseTransferEntity getBaseTransferEntity(Object object) {
        String jsonString = new Gson().toJson(object);
        String encode = (Base64.encodeToString(jsonString.getBytes(), Base64.DEFAULT));
        if (encode.contains("\n")) {
            encode = encode.replace("\n", "");
        }
        String md5 = MD5Util.encrypt(encode + Constants.RANDOM_KEY);
        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);
        return baseTransferEntity;
    }

    private static Request getRequest(Object object, String url) {
        String json = new Gson().toJson(getBaseTransferEntity(object));
        if (json.contains("\\u003d")) {
            json = json.replace("\\u003d", "=");
        }
        return new Request.Builder()
                .url(Constants.SERVICE_BASE_URL + url)
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Authorization", "Bearer " + Constants.TOKEN)
                .post(RequestBody.create(MediaType.parse("application/json"), json))
                .build();
    }

}
