package com.dangong.oksan.api;

import android.util.Base64;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.callback.LoginResultCallBack;
import com.dangong.oksan.callback.ResultCallBack;
import com.dangong.oksan.callback.ShopTypeResultCallBack;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.model.BaseTransferEntity;
import com.dangong.oksan.model.GetNearShopRequestModel;
import com.dangong.oksan.model.LoginResult;
import com.dangong.oksan.model.NearShopModel;
import com.dangong.oksan.model.OpenSanCaoRequest;
import com.dangong.oksan.model.ReportRequestModel;
import com.dangong.oksan.model.ResponseModel;
import com.dangong.oksan.model.ScannerModel;
import com.dangong.oksan.model.ScannerRequestModel;
import com.dangong.oksan.model.ShopModel;
import com.dangong.oksan.model.ShopTypeResultModel;
import com.dangong.oksan.model.SiteIdRequestModel;
import com.dangong.oksan.model.SiteModel;
import com.dangong.oksan.model.SiteWorkLoggModel;
import com.dangong.oksan.model.SiteWorkLoggRequest;
import com.dangong.oksan.model.StockModel;
import com.dangong.oksan.model.StockRequest;
import com.dangong.oksan.model.WorkHistoryModel;
import com.dangong.oksan.model.WorkHistoryRequestModel;
import com.dangong.oksan.util.MD5Util;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    private static final String TAG = "oksan";

    /**
     * 注册发送验证码接口
     *
     * @param phone
     * @param callBack
     */
    public static void getRegisterCode(String phone, final ApiCallBack callBack) {
        Log.e(TAG, "getRegisterCode paramter: " + phone);
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
                        if (response.isSuccess()) {
                            Log.e(TAG, "------getRegisterCode ----: " + new Gson().toJson(response.getResult()));
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
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
    public static void register(String phone, String name, String code, String password, String inviteCode, String roleId, String province, String city, final ApiCallBack callBack) {
        Log.e(TAG, "getRegisterCode paramter: " + phone + "," + city + "," + password + "," + inviteCode + "," + roleId + "," + province + "," + city);
        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .addParams("code", code)
                .addParams("password", password)
                .addParams("inviteCode", inviteCode)
                .addParams("roleId", roleId)
                .addParams("province", province)
                .addParams("name", name)
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
                        if (response.isSuccess()) {
                            Log.e(TAG, "------register ----: " + new Gson().toJson(response.getResult()));
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
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
        Log.e(TAG, "logincode paramter: " + phone);
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

                        if (response.isSuccess()) {
                            Log.e(TAG, "------logincode ----: " + new Gson().toJson(response.getResult()));
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
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
        Log.e(TAG, "modifycode paramter: " + phone);
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

                        if (response.isSuccess()) {
                            Log.e(TAG, "------modifycode ----: " + new Gson().toJson(response.getResult()));
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
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
        Log.e(TAG, "psdmodify paramter: " + phone + "," + code);
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

                        if (response.isSuccess()) {
                            Log.e(TAG, "------psdmodify ----: " + new Gson().toJson(response.getResult()));
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
        Log.e(TAG, "codelogin paramter: " + phone + "," + code);
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

                        if (response.isSuccess()) {
                            Log.e(TAG, "------codelogin ----: " + new Gson().toJson(response.getResult()));
                            Constants.loginInfo.setAccount(response.getResult().getAccount());
                            Constants.loginInfo.setCheckState(response.getResult().getCheckState());
                            Constants.loginInfo.setCity(response.getResult().getCity());
                            Constants.loginInfo.setEmail(response.getResult().getEmail());
                            Constants.loginInfo.setName(response.getResult().getName());
                            Constants.loginInfo.setPhone(response.getResult().getPhone());
                            Constants.loginInfo.setProvince(response.getResult().getProvince());
                            Constants.loginInfo.setRandomKey(response.getResult().getRandomKey());
                            Constants.loginInfo.setRoleId(response.getResult().getRoleId());
                            Constants.loginInfo.setStatus(response.getResult().getStatus());
                            Constants.loginInfo.setWorkNo(response.getResult().getWorkNo());
                            Constants.loginInfo.setToken(response.getResult().getToken());

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
        Log.e(TAG, "pswlogin paramter: " + phone + "," + password);
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
                        if (response.isSuccess()) {
                            Log.e(TAG, "-------pswlogin ----: " + new Gson().toJson(response.getResult()));
                            Constants.loginInfo.setAccount(response.getResult().getAccount());
                            Constants.loginInfo.setCheckState(response.getResult().getCheckState());
                            Constants.loginInfo.setCity(response.getResult().getCity());
                            Constants.loginInfo.setEmail(response.getResult().getEmail());
                            Constants.loginInfo.setName(response.getResult().getName());
                            Constants.loginInfo.setPhone(response.getResult().getPhone());
                            Constants.loginInfo.setProvince(response.getResult().getProvince());
                            Constants.loginInfo.setRandomKey(response.getResult().getRandomKey());
                            Constants.loginInfo.setRoleId(response.getResult().getRoleId());
                            Constants.loginInfo.setStatus(response.getResult().getStatus());
                            Constants.loginInfo.setWorkNo(response.getResult().getWorkNo());
                            Constants.loginInfo.setToken(response.getResult().getToken());
                            callBack.success(response);
                        } else {
                            callBack.fail();
                            ToastUtils.showShort(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取商铺类型列表
     *
     * @param callBack
     */
    public static void shoptype(final ApiCallBack callBack) {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "multipart/form-data");
        header.put("Authorization", "Bearer " + Constants.loginInfo.getToken());

        OkHttpUtils
                .post()
                .headers(header)
                .url(Constants.SERVICE_BASE_URL + "/shop/shoptype")
                .build()
                .execute(new ShopTypeResultCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("[oksan] {shoptype}" + e.getMessage());
                        ToastUtils.showShort("服务异常，请稍后再试！");
                        callBack.fail();
                    }

                    @Override
                    public void onResponse(ShopTypeResultModel response, int id) {
                        if (response.isSuccess()) {
                            Log.e(TAG, "-------shoptype ----: " + new Gson().toJson(response.getResult()));

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
        Log.e(TAG, "addShop paramter: " + new Gson().toJson(model));
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

                ResponseModel model = new Gson().fromJson(response.body().string(), ResponseModel.class);

                if (model.isSuccess()) {
                    Log.e(TAG, "-------addShop ----: " + new Gson().toJson(model.getResult()));
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
        Log.e(TAG, "checkgoods paramter: " + new Gson().toJson(model));
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
                    Log.e(TAG, "-------checkgoods ----: " + new Gson().toJson(model.getResult()));
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
        Log.e(TAG, "removeSite paramter: " + new Gson().toJson(model));
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
                    Log.e(TAG, "-------removeSite ----: " + new Gson().toJson(model.getResult()));
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
        Log.e(TAG, "getinvitecode paramter: " + new Gson().toJson(model));
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
                    Log.e(TAG, "-------getinvitecode ----: " + new Gson().toJson(model.getResult()));
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 进货/减货
     *
     * @param model
     * @param type
     * @param callBack
     */
    public static void stock(StockRequest model, int type, final ApiCallBack callBack) {
        String url = "";
        if (type == 2) {
            url = "/site/outstock";
        } else if (type == 1) {
            url = "/site/stock";
        }
        Log.e(TAG, "stock paramter: " + new Gson().toJson(model));
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
                    Log.e(TAG, "-------stock ----: " + new Gson().toJson(model.getResult()));
                    callBack.success(model.getResult());
                    ToastUtils.showLong(model.getResult());
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
        Log.e(TAG, "scanner paramter: " + new Gson().toJson(model));
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
                    Log.e(TAG, "-------scanner ----: " + new Gson().toJson(model.getResult()));
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
        Log.e(TAG, "getWorkHistory paramter: " + new Gson().toJson(model));
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
                    Log.e(TAG, "-------getWorkHistory ----: " + new Gson().toJson(model.getResult()));
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 站点存货汇报
     *
     * @param model
     * @param callBack
     */
    public static void report(ReportRequestModel model, final ApiCallBack callBack) {
        Log.e(TAG, "report paramter: " + new Gson().toJson(model));
        final Request request = getRequest(model, "/site/report");

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
                    Log.e(TAG, "-------report ----: " + new Gson().toJson(model.getResult()));
                    ToastUtils.showLong(model.getResult());
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 附近店铺
     *
     * @param longitude
     * @param latitude
     * @param range
     * @param city
     * @param callBack
     */
    public static void getNearShop(double longitude, double latitude, double range, String city, final ApiCallBack callBack) {

        GetNearShopRequestModel model = new GetNearShopRequestModel();
        model.setCity(city);
        model.setLatitude(latitude);
        model.setLongitude(longitude);
        model.setRange(range);
        Log.e(TAG, "getNearShop paramter: " + new Gson().toJson(model));
        final Request request = getRequest(model, "/shop/getNearShop");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body());

                NearShopModel model = new Gson().fromJson(response.body().string(), NearShopModel.class);

                if (model.isSuccess()) {
                    Log.e(TAG, "-------getNearShop ----: " + new Gson().toJson(model.getResult()));
                    callBack.success(model);
                } else {
                    ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 获取站点操作日志
     *
     * @param limit
     * @param offset
     * @param callBack
     */
    public static void getSiteWorkHistory(String limit, String offset, final ApiCallBack callBack) {

        SiteWorkLoggRequest model = new SiteWorkLoggRequest();
        model.setLimit(limit);
        model.setOffset(offset);
        Log.e(TAG, "getSiteWorkHistory paramter: " + new Gson().toJson(model));
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
                LogUtils.e(request.body());

                SiteWorkLoggModel model = new Gson().fromJson(response.body().string(), SiteWorkLoggModel.class);

                if (model.isSuccess()) {
                    Log.e(TAG, "-------getSiteWorkHistory ----: " + new Gson().toJson(model.getResult()));
                    callBack.success(model.getResult());
                } else {
                    Log.e(TAG, "-------getSiteWorkHistory fial ----: " + model.getMessage());
                    // ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    /**
     * 打开伞槽
     * @param uniqueCode
     * @param callBack
     */
    public static void opensancao(String uniqueCode, final ApiCallBack callBack) {

        OpenSanCaoRequest model = new OpenSanCaoRequest();
        model.setUniqueCode(uniqueCode);

        Log.e(TAG, "opensancao paramter: " + new Gson().toJson(model));
        final Request request = getRequest(model, "/site/openum");

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e.toString());
                ToastUtils.showShort("服务异常，请稍后再试！");
                callBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(request.body());

                LoginResult model = new Gson().fromJson(response.body().string(), LoginResult.class);

                if (model.isSuccess()) {
                    Log.e(TAG, "-------opensancao ----: " + new Gson().toJson(model.getResult()));
                    callBack.success(model.getResult());
                } else {
                    Log.e(TAG, "-------opensancao fial ----: " + model.getMessage());
                    // ToastUtils.showShort(model.getMessage());
                    callBack.fail();
                }

            }
        });

    }

    public static BaseTransferEntity getBaseTransferEntity(Object object) {
        String jsonString = new Gson().toJson(object);
        String encode = (Base64.encodeToString(jsonString.getBytes(), Base64.DEFAULT));
        if (encode.contains("\n")) {
            encode = encode.replace("\n", "");
        }
        String md5 = MD5Util.encrypt(encode + Constants.loginInfo.getRandomKey());
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
                .addHeader("Authorization", "Bearer " + Constants.loginInfo.getToken())
                .post(RequestBody.create(MediaType.parse("application/json"), json))
                .build();
    }

}
