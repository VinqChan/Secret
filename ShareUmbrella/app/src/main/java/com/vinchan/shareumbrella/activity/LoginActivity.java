package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.vinchan.shareumbrella.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.login_clear_btn, R.id.phone_login_btn, R.id.verfycode_login_btn, R.id.forget_pwd_tv, R.id.register_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_clear_btn:

                break;
            case R.id.phone_login_btn:
                ToastUtils.showShort("登录");
                break;
            case R.id.verfycode_login_btn:
                break;
            case R.id.forget_pwd_tv:
                break;
            case R.id.register_tv:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
