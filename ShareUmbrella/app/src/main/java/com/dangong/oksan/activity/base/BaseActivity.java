package com.dangong.oksan.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.AddShopMapActivity;
import com.dangong.oksan.activity.MainActivity;
import com.dangong.oksan.activity.MaintainMainActivity;
import com.dangong.oksan.activity.MaintainPersonCenterActivity;
import com.dangong.oksan.activity.ManagerAndMaintainMainActivity;
import com.dangong.oksan.activity.ManagerPersonCenterActivity;
import com.dangong.oksan.activity.PersonCenterActivity;
import com.dangong.oksan.constants.Constants;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import crossoverone.statuslib.StatusUtil;

/***************************************
 *
 * @author lwli
 * @date 2014-8-15
 * @modify zhengjb
 * @date 2015-3-25
 * @time 上午9:21:35 类说明: 基础Activity类 对广播、加载进度显示、overridePendingTransition、网络状态变化等
 *       进行了处理
 *
 *
 **************************************/
public abstract class BaseActivity extends Activity {
    public static final String TAG = BaseActivity.class.getName();
    private static final long EXPIRES_PERMANENT;
    private static final long EXPIRES_TEMPORARY;

    static {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2100, 1 - 1, 1);
        EXPIRES_PERMANENT = calendar.getTimeInMillis();
        calendar.set(2019, 1 - 1, 30);
        EXPIRES_TEMPORARY = calendar.getTimeInMillis();
    }

    public boolean isExpired() {
        return EXPIRES_TEMPORARY < System.currentTimeMillis();
    }
    /**
     * 使用者定义并使用的广播
     */
    private BroadcastReceiver broadcastReceiver;
    /**
     * 网络状态转变的广播
     */
    private BroadcastReceiver connectionChangeReceiver;
    /**
     * 使用者定义的广播意图过滤器
     */
    private IntentFilter intentFilter = new IntentFilter();

    private boolean isFirstLoading = true;
    private boolean connetionChangeEnable = true;
    /**
     * 标志网络是否连接着
     */
    private boolean isNetConnected = true;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.loading_progress_bar)
    ProgressBar loadingBar;
    private long onCreateTime;
    @BindView(R.id.own_info_iv)
    protected
    ImageView ownInfoIv;
    /**
     * @param savedInstanceState
     * @return
     */
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        onCreateTime = new Date().getTime();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        setStatusColor();
        setSystemInvadeBlack();
        init();
        if(isExpired()){
            Log.e(TAG, "expired! " );
            finish();
        }
    }

    protected void setStatusColor() {
        StatusUtil.setUseStatusBarColor(this, Color.parseColor("#FC5B1F"));
    }

    protected void setSystemInvadeBlack() { // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtil.setSystemStatus(this, true, false);
    }


    /**
     * @return 是否第一次加载数据，如果是则会在title上显示圆形进度视图，加载完后设置该数据为false
     */
    public boolean isFirstLoading() {
        return isFirstLoading;
    }

    /**
     * @param isFirstLoading 是否是第一次进入页面加载数据，如果是显示loadingBar，否则不显示
     */
    public void setFirstLoading(boolean isFirstLoading) {
        this.isFirstLoading = isFirstLoading;
    }

    /**
     * 默认不开启注册广播，设置广播的过滤同时开启广播
     *
     * @param intentFilter 此activity中的广播过滤器
     */
    public void setIntentFilter(IntentFilter intentFilter) {
        // LogUtils.d("TAG", "setIntentFilter:" + intentFilter.getAction(0));
        this.intentFilter = intentFilter;
        registerBroadCast();
    }

    /**
     * 在activity被销毁时，注销广播
     */
    private void unRegisterBroadCast() {
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }

        if (connectionChangeReceiver != null) {
            unregisterReceiver(connectionChangeReceiver);
            connectionChangeReceiver = null;
        }
    }

    /**
     * 为activity准备的广播接收器，主要为了实现activity在需要刷新的时候刷新，也可以有其他用途
     */
    public void registerBroadCast() {
        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    onReceiveBroadCast(context, intent);
                }
            };

            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    /**
     * 当接受到广播后的处理
     *
     * @param context 接受广播的上下文
     * @param intent  该广播的意图
     */
    public void onReceiveBroadCast(Context context, Intent intent) {

    }

    /**
     * //设置字体大小不随手机设置而改变
     *
     * @return
     */

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;

    }

    /**
     * 拥有默认动作的启动activity方法
     *
     * @param intent   要启动activity的意图
     * @param isFinish 是否关闭当前activity
     */
    public void startActivity(Intent intent, boolean isFinish) {
        super.startActivity(intent);

        if (isFinish) {
            finish();
        }
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 拥有默认动作的启动activity方法
     *
     * @param intent   要启动activity的意图
     * @param reqCode  请求码
     * @param isFinish 是否关闭当前activity
     */
    public void startActivityForResult(Intent intent, int reqCode, boolean isFinish) {
        super.startActivityForResult(intent, reqCode);

        if (isFinish) {
            finish();
        }
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG, "create view used time:" + (new Date().getTime() - onCreateTime) + "ms");
        onCreateTime = new Date().getTime();


    }

    /**
     * 运行在onCreate中，实现视图和数据的初始化
     */
    public void init() {
        initView();
        initData();
        registerConnectionChange();
    }

    /**
     * @return 是否注册网络状态变化的广播，如果否请在initData之前或之中设置
     */
    public boolean isConnetionChangeEnable() {
        return connetionChangeEnable;
    }

    /**
     * @param connetionChangeEnable 是否注册网络状态变化的广播，如果否请在initData之前或之中设置
     */
    public void setConnetionChangeEnable(boolean connetionChangeEnable) {
        this.connetionChangeEnable = connetionChangeEnable;
    }

    /**
     * 注册网络状态变更的广播
     */
    private void registerConnectionChange() {
        LogUtils.i(TAG, "registerConnectionChange");
        if (connetionChangeEnable && connectionChangeReceiver == null) {
            connectionChangeReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    LogUtils.i(TAG, "on connection change");
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                    if (connectivityManager != null) {
                        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                        NetworkInfo mobileNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                        // wifi、移动网络都没有连接
                        boolean hasNet = (wifiNetInfo != null && wifiNetInfo.isConnected()) || (mobileNetInfo != null && mobileNetInfo.isConnected());

                        if (!hasNet) {
                            isNetConnected = false;
                            onNetBreakUp();
                        } else {
                            if (!isNetConnected) {// 重新连接的状态
                                isNetConnected = true;
                                onNetReConnected();
                            }
                        }
                    }
                }
            };

            IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(connectionChangeReceiver, intentFilter);
        }
    }

    /**
     * 当网络中断时的处理
     */
    public void onNetBreakUp() {
        LogUtils.i(TAG, "net break up");
    }

    /**
     * 当网络重新连接上时的处理
     */
    public void onNetReConnected() {
        LogUtils.i(TAG, "net re-connected");
    }

    /**
     * 显示title的加载视图
     */
    public void startLoading() {
        if (loadingBar != null && loadingBar.getVisibility() == View.GONE) {
            loadingBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏title的加载视图
     */
    public void stopLoading() {
        if (loadingBar != null && loadingBar.getVisibility() == View.VISIBLE) {
            loadingBar.setVisibility(View.GONE);
        }
    }

    /**
     * 使用默认动作关闭
     */
    public void finishAnimation() {
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /**
     * 初始化视图
     */
    public void initView() {
        titleTv.setText(setTitle());
        titleBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ownInfoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constants.loginInfo.getRoleId() .equals(Constants.ROLEID_GUIDE)){//导游
                    ActivityUtils.startActivity(PersonCenterActivity.class);
                }else  if(Constants.loginInfo.getRoleId() .equals(Constants.ROLEID_MANAGER)){//管理人员
                    ActivityUtils.startActivity(ManagerPersonCenterActivity.class);
                }else if(Constants.loginInfo.getRoleId() .equals(Constants.ROLEID_STAFF)){//维护人员
                    ActivityUtils.startActivity(MaintainPersonCenterActivity.class);
                }
            }
        });
    }

    public void setTitle(String title){
        titleTv.setText(title);
    }


    /**
     * 加载数据，初次加载显示title的loading
     */
    public void initData() {
        if (isFirstLoading) {
            //startLoading();
            isFirstLoading = false;
        }
    }

    ;

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    public void onBackPressed() {
        finishAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBroadCast();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    PermissionResultListener permissionResultListener;

    public interface PermissionResultListener {
        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    }

    public void setPermissionResultListener(PermissionResultListener permissionResultListener) {
        this.permissionResultListener = permissionResultListener;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissionResultListener != null) {
            permissionResultListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public abstract int getLayoutId();

    public abstract String setTitle();

}
