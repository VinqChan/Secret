package com.dangong.oksan.util.permission;

import android.Manifest;
import android.app.Activity;

import com.blankj.utilcode.util.LogUtils;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.view.dialog.ConfirmOrTipDialog;


/**
 *
 * 权限管理中心
 * Created by chenwq on 2016/9/26.
 */
public class PermissionCenter {

    private static PermissionCenter permissionCenter = null;

    public static PermissionCenter getInstance() {
        if (permissionCenter == null) {
            permissionCenter = new PermissionCenter();
        }
        return permissionCenter;
    }

    /**
     * 是否使用错误提示
     */
    private boolean enableToastError = true;

    /**
     * @return 是否使用错误提示
     */
    public boolean isEnableToastError() {
        return enableToastError;
    }

    /**
     * @param enableToastError 是否使用错误提示
     */
    public void setEnableToastError(boolean enableToastError) {
        this.enableToastError = enableToastError;
    }

    /**
     * 检查权限
     * @param activity
     * @param perms
     * @param callBack
     */
    public void checkPermission(final Activity activity, final PermissionCallBack callBack , final String... perms) {


        if (activity instanceof BaseActivity) {
            final PermissionCheck permissionCheck = new PermissionCheck(activity);

            if (!permissionCheck.hasPermissions(perms)) {
                        permissionCheck.setOnPermissionCheckListener(new PermissionCheck.OnPermissionCheckListener() {
                            @Override
                            public void onSuccess(String permission) {

                                if (callBack != null) {
                                    callBack.onSuccess(permission);
                                }
                            }

                            @Override
                            public void onFail(String permission, int grantResult) {
                                LogUtils.d("PermissionCenter", "------permission:" + permission);
                                if (enableToastError) {
                                    requestPermissionsFail(activity, permission);
                                }
                                if (callBack != null) {
                                    callBack.onfail(permission);
                                }

                            }

                        });
                        //请求授权
                        permissionCheck.requestPermissions(perms);

            } else {
                onGrant(callBack,perms);
            }

        } else {
            PermissionCheck permissionCheck = new PermissionCheck(activity);
            if (!permissionCheck.hasPermissions(perms)) {
                permissionCheck.requestPermissions(perms);
                return;
            } else {
                onGrant(callBack,perms);
            }
        }

    }

    /**
     * 授权成功处理
     * @param callBack
     * @param perms
     */
    public void onGrant(PermissionCallBack callBack,String... perms){
        if (callBack != null) {
            for(String perm:perms){
                callBack.onSuccess(perm);
            }
        }
    }
    /**
     * 授权失败处理
     * @param callBack
     * @param perms
     */
    public void onRefuse(PermissionCallBack callBack,String... perms){
        if (callBack != null) {
            for(String perm:perms){
                callBack.onfail(perm);
            }
        }
    }


    /**
     * 拒绝授权提示
     *
     * @param perm
     */
    public void requestPermissionsFail(Activity activity, String perm) {

            switch (perm) {
                case Manifest.permission.CALL_PHONE:
                    showTipDialog(activity,"拨打电话失败，请尝试在手机应用权限管理中打开权限");
                    break;
                case Manifest.permission.CAMERA:
                    showTipDialog(activity,"摄像头启动失败，请尝试在手机应用权限管理中打开权限");
                    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    showTipDialog(activity,"授予定位权限，有助于提供更准确的商家位置信息");
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                    showTipDialog(activity,"存储权限缺失，可能导致应用升级和图片保存等的异常");
                    break;
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    showTipDialog(activity,"存储权限缺失，可能导致应用升级和图片保存等的异常");
                    break;
                case Manifest.permission.READ_CONTACTS:
                    showTipDialog(activity,"没有读取联系人权限将无法获取到联系人，请尝试在手机应用权限管理中打开权限");
                    break;
            }

    }

    /**
     * 提示对话框
     * @param activity
     * @param tip
     */
    public void showTipDialog(Activity activity, String tip){
        ConfirmOrTipDialog confirmOrTipDialog = new ConfirmOrTipDialog(activity, ConfirmOrTipDialog.EnumDialogType.TIP);
        confirmOrTipDialog.setTipContent(tip);
        confirmOrTipDialog.show();
    }

}
