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
import butterknife.OnClick;

/**
 * 维护人员站点管理/管理员站点管理
 */
public class ManagerAndMaintainMainActivity extends BaseActivity {

    public static final int TYPE_MATAIN = 1;//维护人员
    public static final int TYPE_MANAGER = 2;//管理人员
    public static final String TYPE_KEY = "main_type";

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
    @BindView(R.id.manager_rl)
    LinearLayout managerRl;
    @BindView(R.id.maintain_rl)
    LinearLayout maintainRl;
    @BindView(R.id.add_shop_ll)
    LinearLayout addShopLl;
    @BindView(R.id.cut_shop_ll)
    LinearLayout cutShopLl;
    @BindView(R.id.chhb_ll)
    LinearLayout chhbLl;
    private int type = TYPE_MATAIN;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manager_and_maintain_main;
    }

    @Override
    public String setTitle() {
        return "";

    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void initView() {
        super.initView();
        type = getIntent().getIntExtra(TYPE_KEY, TYPE_MATAIN);

        if (type == TYPE_MANAGER) {
            managerRl.setVisibility(View.VISIBLE);
            maintainRl.setVisibility(View.GONE);
            setTitle(getString(R.string.site_manager));
        } else {
            managerRl.setVisibility(View.GONE);
            maintainRl.setVisibility(View.VISIBLE);
            setTitle(getString(R.string.maintain_site_manager));
        }
    }


    @OnClick({R.id.add_shop_ll, R.id.cut_shop_ll, R.id.chhb_ll, R.id.open_san_btn, R.id.qshc_btn, R.id.lpdh_ll, R.id.zdglyfl_ll, R.id.gzclsm_ll, R.id.zdchhb_ll})
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
                ActivityUtils.startActivity(SiteStockReportActivity.class);
                break;
            case R.id.add_shop_ll:
                Bundle bundleAdd = new Bundle();
                bundleAdd.putInt(SiteReportActivity.TYPE_KEY,SiteReportActivity.TYPE_ADD);
                ActivityUtils.startActivity(bundleAdd,SiteReportActivity.class);
                break;
            case R.id.cut_shop_ll:
                Bundle bundle = new Bundle();
                bundle.putInt(SiteReportActivity.TYPE_KEY,SiteReportActivity.TYPE_CUT);
                ActivityUtils.startActivity(bundle,SiteReportActivity.class);
                break;
            case R.id.chhb_ll:
                ActivityUtils.startActivity(SiteStockReportActivity.class);
                break;
        }
    }


}
