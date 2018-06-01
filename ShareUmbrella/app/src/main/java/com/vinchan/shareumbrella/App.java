package com.vinchan.shareumbrella;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

/**
 * Created by Jian on 2018/5/31.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        Utils.init(this);
        ToastUtils.setMsgColor(getColor(R.color.black_color));
    }
}
