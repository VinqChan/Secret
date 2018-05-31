package com.vinchan.shareumbrella;

import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

/**
 * Created by Jian on 2018/5/31.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        ToastUtils.setMsgColor(getColor(R.color.black_color));
    }
}
