package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.vinchan.shareumbrella.callback.BussinessTimePickerCallBack;
import com.vinchan.shareumbrella.callback.CustomerPickerCallBack;
import com.vinchan.shareumbrella.constants.Constants;
import com.vinchan.shareumbrella.model.ShopModel;
import com.vinchan.shareumbrella.util.PickerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShopActivity extends BaseActivity {


    @BindView(R.id.shop_name_et)
    EditText shopNameEt;
    @BindView(R.id.shop_type_et)
    TextView shopTypeEt;
    @BindView(R.id.selete_shop_type_rl)
    RelativeLayout seleteShopTypeRl;
    @BindView(R.id.address_et)
    TextView addressEt;
    @BindView(R.id.selete_address_rl)
    RelativeLayout seleteAddressRl;
    @BindView(R.id.address_detail_et)
    EditText addressDetailEt;
    @BindView(R.id.put_location_et)
    EditText putLocationEt;
    @BindView(R.id.time_et)
    TextView timeEt;
    @BindView(R.id.selete_time_rl)
    RelativeLayout seleteTimeRl;
    @BindView(R.id.charge_name_et)
    EditText chargeNameEt;
    @BindView(R.id.charge_phone_et)
    EditText chargePhoneEt;
    @BindView(R.id.manager_code_et)
    EditText managerCodeEt;
    @BindView(R.id.manager_name_et)
    EditText managerNameEt;
    @BindView(R.id.work_telephone_et)
    EditText workTelephoneEt;
    @BindView(R.id.manager_telephone_et)
    EditText managerTelephoneEt;
    @BindView(R.id.state_et)
    TextView stateEt;
    @BindView(R.id.selete_state_rl)
    RelativeLayout seleteStateRl;
    @BindView(R.id.cancle_btn)
    Button cancleBtn;
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;
    String province; //省
    String city;    //市
    String region;  //地区
    String beginTime;  //营业时间
    String endTime;    //营业截止时间
    String latitude; //纬度
    String longitude; //维度
    //申明对象
    CityPickerView mPicker = new CityPickerView();
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shop;
    }

    @Override
    public String setTitle() {
        return getString(R.string.add_shop);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
        mPicker.init(this);
        initCityPicker();
    }
    private void initCityPicker() {
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setDefaultProvinceName("福建");
        cityConfig.setConfirmTextColorStr("#fd5d1f");
        mPicker.setConfig(cityConfig);
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean mprovince, CityBean mcity, DistrictBean mdistrict) {
                StringBuilder stringBuilder = new StringBuilder();
                //省份
                if (mprovince != null) {
                    province = mprovince.getName();
                    stringBuilder.append(province);
                }

                //城市
                if (mcity != null) {
                    city = mcity.getName();
                    stringBuilder.append(city);
                }

                //地区
                if (mdistrict != null) {
                    stringBuilder.append(mdistrict);
                }
                addressEt.setText(stringBuilder.toString());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.selete_shop_type_rl, R.id.selete_address_rl, R.id.selete_time_rl, R.id.selete_state_rl, R.id.cancle_btn, R.id.add_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selete_shop_type_rl:
                String [] str1 = new String[]{ "酒店", "景区内", "餐饮铺","小区", "单位"};
                PickerUtils.customerPicker(this, str1,new CustomerPickerCallBack() {
                    @Override
                    public void selecte(String selecteItem) {
                        shopTypeEt.setText(selecteItem);
                    }
                });
                break;
            case R.id.selete_address_rl:
                mPicker.showCityPicker( );
                break;
            case R.id.selete_time_rl:
                PickerUtils.onBusinessTimePicker(this, new BussinessTimePickerCallBack() {
                    @Override
                    public void time(String beginhour, String beginminute, String endhour, String endminute) {
                      timeEt.setText(beginhour+":"+beginminute+"~"+endhour+":"+endminute);
                      beginTime = beginhour+":"+beginminute;
                      endTime = endhour+":"+endminute;
                    }
                });
                break;
            case R.id.selete_state_rl:
                String [] str = new String[]{ "景区场景", "低价场景"};
                PickerUtils.customerPicker(this, str,new CustomerPickerCallBack() {
                    @Override
                    public void selecte(String selecteItem) {
                        stateEt.setText(selecteItem);
                    }
                });
                break;
            case R.id.cancle_btn:
                break;
            case R.id.add_btn:
                addShop();
                break;
        }
    }

    private void addShop() {
        String name = shopNameEt.getText().toString().trim();
        String type = shopTypeEt.getText().toString().trim();
        String address = addressDetailEt.getText().toString().trim();
        String putPosition = putLocationEt.getText().toString().trim();
        String time = timeEt.getText().toString().trim();
        String chargeName= chargeNameEt.getText().toString().trim();  //主管姓名
        String chargePhone= chargePhoneEt.getText().toString().trim();   //主管电话
        String managerName= managerNameEt.getText().toString().trim();  //管理员姓名
        String managerPhone= managerTelephoneEt.getText().toString().trim(); //管理员电话
        String managerId= managerCodeEt.getText().toString().trim();    //管理员工号
        String unitPhone= workTelephoneEt.getText().toString().trim();    //单位电话
        String pStatus= stateEt.getText().toString().trim(); //配置状态
        type = "酒店";
        pStatus = "景区场景";
        if(StringUtils.isEmpty(name)){
            ToastUtils.showShort("请输入店铺名称！");
            return;
        }
        if(StringUtils.isEmpty(type)){
            ToastUtils.showShort("请选择店铺类型！");
            return;
        }
        if(StringUtils.isEmpty(address)){
            ToastUtils.showShort("请选择省市区！");
            return;
        }
        if(StringUtils.isEmpty(putPosition)){
            ToastUtils.showShort("请输入伞桩的具体摆放位置！");
            return;
        }
        if(StringUtils.isEmpty(time)){
            ToastUtils.showShort("请选择营业时间！");
            return;
        }
        if(StringUtils.isEmpty(chargeName)){
            ToastUtils.showShort("请输入主管名字！");
            return;
        }
        if(StringUtils.isEmpty(chargePhone)){
            ToastUtils.showShort("请输入主管手机！");
            return;
        }
        if(StringUtils.isEmpty(managerPhone)){
            ToastUtils.showShort("请输入站点管理员手机号码！");
            return;
        }
        if(StringUtils.isEmpty(managerName)){
            ToastUtils.showShort("请输入站点管理员姓名！");
            return;
        }
        if(StringUtils.isEmpty(managerId)){
            ToastUtils.showShort("请输入站点管理员工号！");
            return;
        }
        if(StringUtils.isEmpty(unitPhone)){
            ToastUtils.showShort("请输入座机号！");
            return;
        }
        if(StringUtils.isEmpty(pStatus)){
            ToastUtils.showShort("请选择配置状态！");
            return;
        }
        startLoading();
        ShopModel model = new ShopModel();
        model.setSiteId("02-TNCN1xjdhi10000w07J");
        model.setSnCode(Constants.SNCODE);
        model.setName(name);
        model.setType(0);
        model.setProvince(province);
        model.setCity(city);
        model.setRegion(region);
        model.setAddress(address);
        model.setPutPosition(putPosition);
        model.setBeginTime(beginTime);
        model.setEndTime(endTime);
        model.setChargeName(chargeName);
        model.setChargePhone(chargePhone);
        model.setManagerName(managerName);
        model.setManagerId(managerId);
        model.setManagerPhone(managerPhone);
        model.setUnitPhone(unitPhone);
        model.setpStatus(pStatus);
        model.setLatitude(latitude);
        model.setLongitude(longitude);
        ApiUtils.addShop(model, new ApiCallBack() {
            @Override
            public void success(Object response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort("新增成功！");
                        AddShopActivity.this.finish();
                        stopLoading();
                    }
                });
            }

            @Override
            public void fail() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                    }
                });
            }
        });
    }
}
