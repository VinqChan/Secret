package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.util.CountTimer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements CountTimer.OnBacllkCountTimer {

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
    //申明对象
    CityPickerView mPicker=new CityPickerView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPicker.init(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        titleTv.setText(getString(R.string.register));
        initCityPicker();
    }

    private void initCityPicker() {
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setDefaultProvinceName("福建");
        mPicker.setConfig(cityConfig);
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
               StringBuilder stringBuilder = new StringBuilder();
                //省份
                if (province != null) {
                    stringBuilder.append(province);
                }

                //城市
                if (city != null) {
                    stringBuilder.append(city);
                }

                //地区
                if (district != null) {
                    stringBuilder.append(district);
                }
                adressEt.setText(stringBuilder.toString());
            }

            @Override
            public void onCancel() {
              //  ToastUtils.showLongToast(this, "已取消");
            }
        });

        //显示
      //  mPicker.showCityPicker( );
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
                CountTimer countTimer = new CountTimer(getVerfyCodeTv);// 短信发送倒计时
                countTimer.setBackgroundColor(false);
                countTimer.setBacllkCountTimer(RegisterActivity.this);
                countTimer.start();
                break;
            case R.id.selete_role_rl:
                break;
            case R.id.selete_address_rl:
                mPicker.showCityPicker( );
                break;
            case R.id.register_btn:
                startActivity(new Intent(RegisterActivity.this,RealNameCertifiActivity.class));
                break;
            case R.id.service_tv:
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
