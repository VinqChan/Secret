package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerAndMaintainMainActivity extends BaseActivity {


    @BindView(R.id.logo_iv1)
    ImageView logoIv1;
    @BindView(R.id.open_san_btn)
    Button openSanBtn;
    @BindView(R.id.qshc_btn)
    Button qshcBtn;
    @BindView(R.id.lpdh_ll)
    LinearLayout lpdhLl;
    @BindView(R.id.zdglyfl_ll)
    LinearLayout zdglyflLl;
    @BindView(R.id.gzclsm_ll)
    LinearLayout gzclsmLl;
    @BindView(R.id.zdchhb_ll)
    LinearLayout zdchhbLl;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manager_and_maintain_main;
    }

    @Override
    public String setTitle() {
        return getString(R.string.site_manager);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.open_san_btn, R.id.qshc_btn, R.id.lpdh_ll, R.id.zdglyfl_ll, R.id.gzclsm_ll, R.id.zdchhb_ll, R.id.own_info_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.open_san_btn:
                break;
            case R.id.qshc_btn:
                break;
            case R.id.lpdh_ll:
                ActivityUtils.startActivity(GiftShopActivity.class);
                break;
            case R.id.zdglyfl_ll:

                break;
            case R.id.gzclsm_ll:
                ActivityUtils.startActivity(FaultHandleActivity.class);
                break;
            case R.id.zdchhb_ll:
                break;
            case R.id.own_info_iv:
                break;
        }
    }
}
