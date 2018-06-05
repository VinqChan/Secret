package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GiftExchangeActivity extends BaseActivity {


    @BindView(R.id.shop_iv)
    ImageView shopIv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.express_fee_tv)
    TextView expressFeeTv;
    @BindView(R.id.iv_goods_num_add)
    ImageView ivGoodsNumAdd;
    @BindView(R.id.tv_edit_goods_num)
    TextView tvEditGoodsNum;
    @BindView(R.id.iv_goods_num_cut)
    ImageView ivGoodsNumCut;
    @BindView(R.id.ll_edit_goods_num)
    LinearLayout llEditGoodsNum;
    @BindView(R.id.account_tv)
    TextView accountTv;
    @BindView(R.id.adress_et)
    EditText adressEt;
    @BindView(R.id.phone_num_tv)
    EditText phoneNumTv;
    @BindView(R.id.login_edit_layout)
    LinearLayout loginEditLayout;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.pay_btn)
    Button payBtn;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;
    @BindView(R.id.shop_title_tv)
    TextView shopTitleTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gift_exchange;
    }

    @Override
    public String setTitle() {
        return getString(R.string.gift_shop);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);

    }


    @OnClick({R.id.iv_goods_num_add, R.id.iv_goods_num_cut, R.id.pay_btn, R.id.own_info_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_goods_num_add:
                break;
            case R.id.iv_goods_num_cut:
                break;
            case R.id.pay_btn:
                break;
            case R.id.own_info_iv:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
