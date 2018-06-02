package com.vinchan.shareumbrella.util.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;


import com.vinchan.shareumbrella.activity.base.BaseActivity;

import java.util.List;

/**
 * 应用权限检查
 * Created by Vinchan
 */
public class PermissionCheck {
    private Activity activity;
    private static OnPermissionCheckListener onPermissionCheckListener;

    public PermissionCheck(Activity activity){
        this.activity = activity;

        //TODO 当BaseActivity不再使用的时候删除
        if(activity instanceof BaseActivity){
            ((BaseActivity) activity).setPermissionResultListener(new BaseActivity.PermissionResultListener() {
                @Override
                public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                    PermissionCheck.this.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            });
        }
    }

    public OnPermissionCheckListener getOnPermissionCheckListener() {
        return onPermissionCheckListener;
    }

    public void setOnPermissionCheckListener(OnPermissionCheckListener onPermissionCheckListener) {
        this.onPermissionCheckListener = onPermissionCheckListener;
    }

    /**
     * 检查应用是否被赋予某项权限
     * */
    public boolean hasPermission(String permission){
       return ContextCompat.checkSelfPermission(activity,
                permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检查多个权限
     * @param perms
     * @return
     */
    public  boolean hasPermissions( String... perms) {

        for (String perm : perms) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(activity, perm) ==
                    PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }

        return true;
    }
    /**
     * 检查权限
     * */
    public void requestPermissions(String[] permissions){
            ActivityCompat.requestPermissions(activity,
                    permissions, 555);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(onPermissionCheckListener != null && requestCode == 555){
            if(grantResults != null && grantResults.length > 0){
                for (int i = 0; i < grantResults.length; i++) {
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                        onPermissionCheckListener.onSuccess(permissions[i]);
                    }else{
                        if(somePermissionPermanentlyDenied(activity,permissions[i])){//用户选择拒绝且勾选“不再提醒”
                            showSettingDialog(permissions[i]);
                        }else{
                            onPermissionCheckListener.onFail(permissions[i], grantResults[i]);
                        }
                    }
                }
            }
        }
    }

    public interface OnPermissionCheckListener{
        void onSuccess(String permission);
        void onFail(String permission, int grantResult);
    }



    /**
     * 进入系统设置
     *
     * @return
     */
    public void showSettingDialog(String permission) {

        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setMessage(getPermissionsTip(permission))
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                        intent.setData(uri);
                        activity.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }

    /**
     * 相关权限提示
     * @param perm
     * @return
     */
    public String getPermissionsTip(String perm) {
        String permissionTip = "";
        switch (perm) {
            case Manifest.permission.CALL_PHONE:
                permissionTip = "拨打电话失败";
                break;
            case Manifest.permission.CAMERA:
                permissionTip = "摄像头启动失败";
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
                permissionTip = "定位权限缺失";
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                permissionTip = "存储权限缺失";
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                permissionTip = "存储权限缺失";
                break;
            case Manifest.permission.READ_CONTACTS:
                permissionTip = "没有读取联系人权限将无法获取到联系人";
                break;
        }
        permissionTip = permissionTip + "。\n请点击\"设置\"-\"权限\"-打开所需权限。";

        return permissionTip;
    }

    /**
     * 检查未授权权限
     * @param object
     * @param deniedPermission
     * @return
     */
    public static boolean somePermissionPermanentlyDenied(Object object,
                                                          String deniedPermission) {
        if (permissionPermanentlyDenied(object, deniedPermission)) {
            return true;
        }

        return false;
    }

    /**
     * 检查权限是否被拒绝
     * @param object
     * @param deniedPermission
     * @return
     */
    public static boolean permissionPermanentlyDenied(Object object, String deniedPermission) {
        return !shouldShowRequestPermissionRationale(object, deniedPermission);
    }

    /**
     * 进入系统设置
     * @param object
     * @param rationale
     * @param positiveButton
     * @param negativeButton
     * @param negativeButtonOnClickListener
     * @param deniedPerms
     * @return
     */
    public static boolean checkDeniedPermissionsNeverAskAgain(final Object object,
                                                              String rationale,
                                                              @StringRes int positiveButton,
                                                              @StringRes int negativeButton,
                                                              @Nullable DialogInterface.OnClickListener negativeButtonOnClickListener,
                                                              List<String> deniedPerms) {
        boolean shouldShowRationale;
        for (String perm : deniedPerms) {
            shouldShowRationale = shouldShowRequestPermissionRationale(object, perm);
            if (!shouldShowRationale) {
                final Activity activity = (Activity) (object);
                if (null == activity) {
                    return true;
                }

                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setMessage(rationale)
                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                intent.setData(uri);
                                activity.startActivity(intent);
                            }
                        })
                        .setNegativeButton(negativeButton, negativeButtonOnClickListener)
                        .create();
                dialog.show();

                return true;
            }
        }

        return false;
    }

    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(Object object, String perm) {
        if (object instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) object, perm);
        } else if (object instanceof Fragment) {
            return ((Fragment) object).shouldShowRequestPermissionRationale(perm);
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object).shouldShowRequestPermissionRationale(perm);
        } else {
            return false;
        }
    }
}
