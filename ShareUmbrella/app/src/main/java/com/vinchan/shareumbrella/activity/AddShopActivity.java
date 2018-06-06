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
import com.vinchan.shareumbrella.activity.base.BaseActivity;

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
                break;
            case R.id.selete_address_rl:
                break;
            case R.id.selete_time_rl:
                break;
            case R.id.selete_state_rl:
                break;
            case R.id.cancle_btn:
                break;
            case R.id.add_btn:
                break;
        }
    }
}
