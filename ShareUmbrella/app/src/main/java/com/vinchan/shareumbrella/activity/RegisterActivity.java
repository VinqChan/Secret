package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vinchan.shareumbrella.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.phone_num_et)
    EditText phoneNumEt;
    @BindView(R.id.verfy_code_et)
    EditText verfyCodeEt;
    @BindView(R.id.get_verfy_code_tv)
    TextView getVerfyCodeTv;
    @BindView(R.id.input_pwd_et)
    EditText inputPwdEt;
    @BindView(R.id.input_sure_pwd_et)
    EditText inputSurePwdEt;
    @BindView(R.id.invitation_code_et)
    EditText invitationCodeEt;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.role_et)
    TextView roleEt;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.selete_role_rl)
    RelativeLayout seleteRoleRl;
    @BindView(R.id.role_tv)
    ImageView roleTv;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.adress_et)
    TextView adressEt;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.selete_address_rl)
    RelativeLayout seleteAddressRl;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.service_tv)
    TextView serviceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        titleTv.setText(getString(R.string.register));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.title_back_iv, R.id.get_verfy_code_tv, R.id.selete_role_rl, R.id.selete_address_rl, R.id.register_btn,R.id.service_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.get_verfy_code_tv:
                break;
            case R.id.selete_role_rl:
                break;
            case R.id.selete_address_rl:
                break;
            case R.id.register_btn:
                startActivity(new Intent(RegisterActivity.this,RealNameCertifiActivity.class));
                break;
            case R.id.service_tv:
                break;
        }
    }
}
