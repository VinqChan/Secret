package com.dangong.oksan.constants;

import com.dangong.oksan.model.LoginResult;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class Constants {
    public static final String SERVICE_BASE_URL = "http://oksan.vip/guns-rest";
    public static final String ROLEID_MANAGER = "17";//管理人员
    public static final String ROLEID_STAFF = "18";//维护人员
    public static final String ROLEID_GUIDE = "19";//导游
    public static final String CHECKSTATE_YES = "1";//审核通过
    public static final String CHECKSTATE_NO = "2";//审核未通过
    public static final String CHECKSTATE_WAIT= "0";//待审核
    public static final int TYPE_OUT_STOCK = 0;
    public static final int TYPE_IN_STOCK = 1;
    public static final int TYPE_REPORT = 2;
    public static  String SITEID = "0001";
    public static  String SNCODE = "0001";
    public static  double LATITUDE = 0;
    public static  double LONGITUDE = 0;


    public static LoginResult.Result loginInfo = new LoginResult().new Result();
}
