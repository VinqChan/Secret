package com.vinchan.shareumbrella.util.permission;

/**
 * 权限授权回调
 * Created by chenwq on 2016/9/26.
 */
public interface PermissionCallBack {
    void onSuccess(String permission);

    void onfail(String permission);

}