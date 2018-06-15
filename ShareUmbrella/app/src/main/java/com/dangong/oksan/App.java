package com.dangong.oksan;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.dangong.oksan.R;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by Vinchan on 2018/5/31.
 */

public class App extends Application{
    public static final String WECHAT_APP_ID = "12312313212313213213";    //这个APP_ID就是注册APP的时候生成
    public static final String APP_SECRET = "12312312313212313213213";
    public IWXAPI api;      //这个对象是专门用来向微信发送数据的一个重要接口,使用强引用持有,所有的信息发送都是基于这个对象的
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        Utils.init(this);
        ToastUtils.setMsgColor(getColor(R.color.black_color));
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        registerWeChat(this);
    }
    public void registerWeChat(Context context) {   //向微信注册app
        api = WXAPIFactory.createWXAPI(context, WECHAT_APP_ID, true);
        api.registerApp(WECHAT_APP_ID);
    }
}
