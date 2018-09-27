package com.dangong.oksan.util;

import android.app.Activity;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.activity.RealNameCertifiActivity;
import com.dangong.oksan.view.dialog.ConfirmOrCancleDialog;

/**
 * Created by Vinchan on 2018/9/27.
 */

public class DialogUtil {
    public static void showRealNameAuthDialog(Activity activity){
        ConfirmOrCancleDialog dialog = new ConfirmOrCancleDialog(activity);
        dialog.setCancleBtnVisible(false);
        dialog.setSureBtnText("去认证");
        dialog.setTipContent("请先进行实名认证！");
        dialog.setListener(new ConfirmOrCancleDialog.Listener() {
            @Override
            public void sure() {
                ActivityUtils.startActivity(RealNameCertifiActivity.class);
            }

            @Override
            public void cancle() {

            }
        });
        dialog.show();
    }

}
