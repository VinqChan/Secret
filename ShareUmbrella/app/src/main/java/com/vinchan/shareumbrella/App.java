package com.vinchan.shareumbrella;

import android.app.Application;
import android.os.StrictMode;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.dangong.oksan.R;

/**
 * Created by Vinchan on 2018/5/31.
 */

public class App extends Application{
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
    }
}
