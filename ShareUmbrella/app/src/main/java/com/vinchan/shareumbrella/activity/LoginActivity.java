package com.vinchan.shareumbrella.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.api.ApiUtils;
import com.vinchan.shareumbrella.callback.ApiCallBack;
import com.vinchan.shareumbrella.util.permission.PermissionCallBack;
import com.vinchan.shareumbrella.util.permission.PermissionCenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_phone_num_tv)
    EditText loginPhoneNumTv;
    @BindView(R.id.login_clear_btn)
    ImageButton loginClearBtn;
    @BindView(R.id.login_pwd_tv)
    EditText loginPwdTv;
    @BindView(R.id.phone_login_btn)
    Button phoneLoginBtn;
    @BindView(R.id.verfycode_login_btn)
    Button verfycodeLoginBtn;
    @BindView(R.id.login_edit_layout)
    LinearLayout loginEditLayout;
    @BindView(R.id.forget_pwd_tv)
    TextView forgetPwdTv;
    @BindView(R.id.register_tv)
    TextView registerTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public String setTitle() {
        return getString(R.string.login);
    }

    @Override
    public void init() {
        super.init();

        PermissionCenter.getInstance().checkPermission(this, new PermissionCallBack() {

            @Override
            public void onSuccess(String permission) {

            }

            @Override
            public void onfail(String permission) {

            }
        }, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    @OnClick({R.id.login_clear_btn, R.id.phone_login_btn, R.id.verfycode_login_btn, R.id.forget_pwd_tv, R.id.register_tv, R.id.title_back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_clear_btn:

                break;
            case R.id.phone_login_btn:
               // login();
                ActivityUtils.startActivity(MainActivity.class);
                break;
            case R.id.verfycode_login_btn:
                break;
            case R.id.forget_pwd_tv:

                break;
            case R.id.register_tv:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void login() {
        startLoading();
         String phoneNum = loginPhoneNumTv.getText().toString().trim();
         String password = loginPwdTv.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请输入手机号码！");
            return;
        }
        if(!RegexUtils.isMobileExact(phoneNum)){
            ToastUtils.showShort("请输入正确的手机号码！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        ApiUtils.pswlogin(phoneNum, password, new ApiCallBack() {
            @Override
            public void success(Object response) {
                stopLoading();
                ToastUtils.showShort("登录成功！");
            }

            @Override
            public void fail() {

            }
        });
    }

    @Override
    protected void onDestroy(

    ) {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
