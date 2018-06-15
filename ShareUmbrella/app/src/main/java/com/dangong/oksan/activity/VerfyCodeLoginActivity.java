package com.dangong.oksan.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.api.ApiUtils;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.util.CountTimer;

import butterknife.BindView;
import butterknife.OnClick;

public class VerfyCodeLoginActivity extends BaseActivity implements CountTimer.OnBacllkCountTimer {


    @BindView(R.id.login_phone_num_tv)
    EditText loginPhoneNumTv;
    @BindView(R.id.login_clear_btn)
    ImageButton loginClearBtn;
    @BindView(R.id.verfy_code_et)
    EditText verfyCodeEt;
    @BindView(R.id.get_verfy_code_tv)
    TextView getVerfyCodeTv;
    @BindView(R.id.phone_login_btn)
    Button phoneLoginBtn;
    @BindView(R.id.login_edit_layout)
    LinearLayout loginEditLayout;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;
    private CountTimer countTimer;
    @Override
    public int getLayoutId() {
        return R.layout.activity_code_login;
    }

    @Override
    public String setTitle() {
        return getString(R.string.code_login);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void init() {
        super.init();
        countTimer = new CountTimer(getVerfyCodeTv);// 短信发送倒计时
        countTimer.setBackgroundColor(false);
        countTimer.setBacllkCountTimer(this);
    }

    private void login() {

        final String phoneNum = loginPhoneNumTv.getText().toString().trim();
        final String code = verfyCodeEt.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请输入手机号码！");
            return;
        }
        if (!RegexUtils.isMobileExact(phoneNum)) {
            ToastUtils.showShort("请输入正确的手机号码！");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort("请输入验证码！");
            return;
        }
        startLoading();
        ApiUtils.codelogin(phoneNum, code, new ApiCallBack() {
            @Override
            public void success(Object response) {
                stopLoading();
                ToastUtils.showShort("登录成功！");
                ActivityUtils.startActivity(MainActivity.class);
            }

            @Override
            public void fail() {
                stopLoading();
            }
        });
    }

    @Override
    protected void onDestroy(

    ) {
        super.onDestroy();
    }
    private void getRegisterCode(String phoneNum) {
        ApiUtils.logincode(phoneNum, new ApiCallBack() {
            @Override
            public void success(Object response) {
                ToastUtils.showShort("验证码已发送！");
                countTimer.start();
            }

            @Override
            public void fail() {

            }
        });
    }

    @OnClick({R.id.get_verfy_code_tv, R.id.phone_login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verfy_code_tv:
                String phoneNum = loginPhoneNumTv.getText().toString().trim();
                if (StringUtils.isEmpty(phoneNum)) {
                    ToastUtils.showShort("请输入手机号码！");
                    return;
                }
                getRegisterCode(phoneNum);
                break;
            case R.id.phone_login_btn:
                login();
                break;
        }
    }
    @Override
    public void startTimerTvColor() {
        getVerfyCodeTv.setEnabled(false);
        getVerfyCodeTv.setTextColor(getResources().getColor(R.color.gray_color));
    }

    @Override
    public void endTimerTvColor() {
        getVerfyCodeTv.setEnabled(true);
        getVerfyCodeTv.setTextColor(getResources().getColor(R.color.main_color));
    }
}
