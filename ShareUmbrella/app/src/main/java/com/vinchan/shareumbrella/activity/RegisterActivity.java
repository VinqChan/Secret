package com.vinchan.shareumbrella.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.api.ApiUtils;
import com.vinchan.shareumbrella.callback.ApiCallBack;
import com.vinchan.shareumbrella.util.CountTimer;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

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
    CityPickerView mPicker = new CityPickerView();
    private String mProvince = "";
    private String mCity = "";
    private CountTimer countTimer;


    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public String setTitle() {
        return getString(R.string.register);
    }

    @Override
    public void init() {
        super.init();
        mPicker.init(this);
        initCityPicker();
        countTimer = new CountTimer(getVerfyCodeTv);// 短信发送倒计时
        countTimer.setBackgroundColor(false);
        countTimer.setBacllkCountTimer(RegisterActivity.this);
    }

    private void initCityPicker() {
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setDefaultProvinceName("福建");
        cityConfig.setConfirmTextColorStr("#fd5d1f");
        mPicker.setConfig(cityConfig);
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder stringBuilder = new StringBuilder();
                //省份
                if (province != null) {
                    mProvince = province.getName();
                    stringBuilder.append(province);
                }

                //城市
                if (city != null) {
                    mCity = city.getName();
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
        if(countTimer!=null){
            countTimer.cancel();
        }
    }

    @OnClick({ R.id.get_verfy_code_tv, R.id.selete_role_rl, R.id.selete_address_rl, R.id.register_btn, R.id.service_tv})
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
            case R.id.selete_role_rl:
                seleteRoleRl();
                break;
            case R.id.selete_address_rl:
                mPicker.showCityPicker();
                break;
            case R.id.register_btn:
                register();
                break;
            case R.id.service_tv:
                break;
        }
    }
    private void register(){
        String phoneNum = phoneNumEt.getText().toString().trim();
        String code = verfyCodeEt.getText().toString().trim();
        String password = inputPwdEt.getText().toString().trim();
        String ensurePwd = inputSurePwdEt.getText().toString().trim();
        String invitationCode = invitationCodeEt.getText().toString().trim();
        String role = roleEt.getText().toString().trim();
        String address = adressEt.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请输入手机号码！");
            return;
        }
        if(!RegexUtils.isMobileExact(phoneNum)){
            ToastUtils.showShort("请输入正确的手机号码！");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort("请输入验证码！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        if (password.length()<6 || password.length()>16) {
            ToastUtils.showShort("请输入6-16位数字、字母！");
            return;
        }
        if (TextUtils.isEmpty(ensurePwd)) {
            ToastUtils.showShort("请输入确认密码！");
            return;
        }
        if (ensurePwd.length()<6 || ensurePwd.length()>16) {
            ToastUtils.showShort("请输入6-16位数字、字母！");
            return;
        }
        if(!ensurePwd.equals(password)){
            ToastUtils.showShort("两次输入密码不一致！");
            return;
        }
        if (TextUtils.isEmpty(role)) {
            ToastUtils.showShort("请选择您的角色！");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            ToastUtils.showShort("请选择常住区域！");
            return;
        }

        ApiUtils.register(phoneNum,code,password,invitationCode,"1",mProvince,mCity, new ApiCallBack() {
            @Override
            public void success(Object response) {
                ToastUtils.showShort("注册成功！");
                finish();
            }

            @Override
            public void fail() {
                ToastUtils.showShort("注册失败！");
            }
        });
    }

    private void seleteRoleRl() {

        OptionPicker picker = new OptionPicker(this, new String[]{
                "维护人员", "站点管理员", "导购"
        });
        picker.setCanceledOnTouchOutside(true);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setDividerColor(getResources().getColor(R.color.picker_line_color));
        picker.setTextColor(getResources().getColor(R.color.gray_color));
        picker.setTopLineColor(getResources().getColor(R.color.gray_color));
        picker.setSelectedIndex(1);
        picker.setCycleDisable(true);
        picker.setTextSize(16);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                roleEt.setText(item);
            }
        });
        picker.show();
        picker.getCancelButton().setTextSize(16);
        picker.getSubmitButton().setTextSize(16);
        picker.getCancelButton().setTextColor(getResources().getColor(R.color.gray_color));
        picker.getSubmitButton().setTextColor(getResources().getColor(R.color.main_color));
    }

    private void getRegisterCode(String phoneNum) {
        ApiUtils.getRegisterCode(phoneNum, new ApiCallBack() {
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
}
