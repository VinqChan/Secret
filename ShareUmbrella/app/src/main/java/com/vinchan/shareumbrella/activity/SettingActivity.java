package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.view.roundImage.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.header_iv)
    RoundedImageView headerIv;
    @BindView(R.id.selete_image_rl)
    RelativeLayout seleteImageRl;
    @BindView(R.id.guard_code_tv)
    TextView guardCodeTv;
    @BindView(R.id.guard_code_rl)
    RelativeLayout guardCodeRl;
    @BindView(R.id.phone_num_tv)
    TextView phoneNumTv;
    @BindView(R.id.selete_phone_rl)
    RelativeLayout seletePhoneRl;
    @BindView(R.id.certificates_tv)
    TextView certificatesTv;
    @BindView(R.id.selete_certificates_rl)
    RelativeLayout seleteCertificatesRl;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.selete_address_rl)
    RelativeLayout seleteAddressRl;
    @BindView(R.id.workplace_tv)
    TextView workplaceTv;
    @BindView(R.id.selete_workplace_rl)
    RelativeLayout seleteWorkplaceRl;
    @BindView(R.id.modify_phone_tv)
    TextView modifyPhoneTv;
    @BindView(R.id.modify_phone_rl)
    RelativeLayout modifyPhoneRl;
    @BindView(R.id.modify_password_tv)
    TextView modifyPasswordTv;
    @BindView(R.id.modify_password_rl)
    RelativeLayout modifyPasswordRl;
    @BindView(R.id.logout_btn)
    Button logoutBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public String setTitle() {
        return getString(R.string.setting);
    }

    @Override
    public void init() {
        super.init();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.selete_image_rl, R.id.selete_phone_rl, R.id.selete_certificates_rl, R.id.selete_address_rl, R.id.selete_workplace_rl, R.id.modify_phone_rl, R.id.modify_password_rl, R.id.logout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selete_image_rl:
                break;
            case R.id.selete_phone_rl:
                break;
            case R.id.selete_certificates_rl:
                break;
            case R.id.selete_address_rl:
                break;
            case R.id.selete_workplace_rl:
                break;
            case R.id.modify_phone_rl:
                break;
            case R.id.modify_password_rl:
                break;
            case R.id.logout_btn:
                break;
        }
    }
}
