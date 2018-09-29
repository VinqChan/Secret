package com.dangong.oksan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.view.dialog.ConfirmOrCancleDialog;
import com.dangong.oksan.view.pictureTaker.PictureTakeDialog;
import com.dangong.oksan.view.pictureTaker.PictureTaker;
import com.dangong.oksan.view.roundImage.RoundedImageView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

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
    @BindView(R.id.modify_password_tv)
    TextView modifyPasswordTv;
    @BindView(R.id.modify_password_rl)
    RelativeLayout modifyPasswordRl;
    @BindView(R.id.logout_btn)
    Button logoutBtn;
    @BindView(R.id.role_tv)
    TextView roleTv;
    private PictureTaker pictureTaker;//图片选择器
    private PictureTakeDialog pictureTakeDialog;//图片选择弹窗
    CityPickerView mPicker = new CityPickerView();
    private String mProvince = "";
    private String mCity = "";
    private boolean isAddressSelect = false;

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
        initPictureTaker();
        mPicker.init(this);
        initCityPicker();
        if (Constants.loginInfo.getRoleId().equals(Constants.ROLEID_GUIDE)) {//导游
            roleTv.setText("导游工号");
        } else if (Constants.loginInfo.getRoleId().equals(Constants.ROLEID_MANAGER)) {//管理人员
            roleTv.setText("站点管理员工号");
        } else if (Constants.loginInfo.getRoleId().equals(Constants.ROLEID_STAFF)) {//维护人员
            roleTv.setText("维护人员工号");
        }
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
                if (isAddressSelect) {
                    addressTv.setText(stringBuilder.toString());
                } else {
                    workplaceTv.setText(stringBuilder.toString());
                }

            }

            @Override
            public void onCancel() {
                //  ToastUtils.showLongToast(this, "已取消");
            }
        });

        //显示
        //  mPicker.showCityPicker( );
    }

    public void initPictureTaker() {

        pictureTaker = new PictureTaker(this, "/oksan");
        pictureTaker.setEnableCrop(true);
        pictureTaker.setOnTakePictureListener(new PictureTaker.OnTakePictureListener() {
            @Override
            public void onPictureTaked(Bitmap bitmap, String url) {
                if (bitmap != null) {
                    headerIv.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pictureTaker.onActivityResult(data, requestCode);
    }

    /**
     * 修改头像的弹窗
     */
    public void showPicturePckDialog() {
        if (pictureTakeDialog == null) {
            pictureTakeDialog = new PictureTakeDialog(this, pictureTaker);
        }
        pictureTakeDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.selete_image_rl, R.id.selete_phone_rl, R.id.selete_certificates_rl, R.id.selete_address_rl, R.id.selete_workplace_rl, R.id.modify_password_rl, R.id.logout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selete_image_rl:
                showPicturePckDialog();
                break;
            case R.id.selete_phone_rl:
                ActivityUtils.startActivity(ModifyPhoneActivity.class);
                break;
            case R.id.selete_certificates_rl:
                Bundle bundle2 = new Bundle();
                bundle2.putInt(RealNameCertifiActivity.TYPE_KEY, RealNameCertifiActivity.TYPE_MODIFY);
                ActivityUtils.startActivity(bundle2, RealNameCertifiActivity.class);
                break;
            case R.id.selete_address_rl:
                isAddressSelect = true;
                mPicker.showCityPicker();
                break;
            case R.id.selete_workplace_rl:
                isAddressSelect = false;
                mPicker.showCityPicker();
                break;
            case R.id.modify_password_rl:
                Bundle bundle = new Bundle();
                bundle.putInt(ForgetPasswordActivity.KEY_PAGER_TYPE, ForgetPasswordActivity.VALUES_PAGER_MODIFY);
                ActivityUtils.startActivity(bundle, ForgetPasswordActivity.class);
                break;
            case R.id.logout_btn:
                ConfirmOrCancleDialog dialog = new ConfirmOrCancleDialog(SettingActivity.this);
                dialog.show();
                break;
        }
    }
}
