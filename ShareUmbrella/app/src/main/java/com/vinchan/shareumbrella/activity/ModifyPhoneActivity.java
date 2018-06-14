package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.api.ApiUtils;
import com.vinchan.shareumbrella.callback.ApiCallBack;
import com.vinchan.shareumbrella.util.CountTimer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyPhoneActivity extends BaseActivity implements CountTimer.OnBacllkCountTimer {


    @BindView(R.id.phone_num_et)
    EditText phoneNumEt;
    @BindView(R.id.verfy_code_et)
    EditText verfyCodeEt;
    @BindView(R.id.get_verfy_code_tv)
    TextView getVerfyCodeTv;
    @BindView(R.id.modify_btn)
    Button modifyBtn;
    private CountTimer countTimer;
    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_phone;
    }

    @Override
    public String setTitle() {
        return getString(R.string.modify_phone);
    }

    @Override
    public void init() {
        super.init();

        countTimer = new CountTimer(getVerfyCodeTv);// 短信发送倒计时
        countTimer.setBackgroundColor(false);
        countTimer.setBacllkCountTimer(ModifyPhoneActivity.this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTimer != null) {
            countTimer.cancel();
        }
    }


    private void modify() {
        startLoading();
        String phoneNum = phoneNumEt.getText().toString().trim();
        String code = verfyCodeEt.getText().toString().trim();


        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请输入新手机号码！");
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

    }



    private void getRegisterCode(String phoneNum) {
        ApiUtils.modifycode(phoneNum, new ApiCallBack() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get_verfy_code_tv, R.id.modify_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verfy_code_tv:
                String phoneNum = phoneNumEt.getText().toString().trim();
                if (StringUtils.isEmpty(phoneNum)) {
                    ToastUtils.showShort("请输入手机号码！");
                    return;
                }
                getRegisterCode(phoneNum);
                break;
            case R.id.modify_btn:
                modify();
                break;
        }
    }
}
