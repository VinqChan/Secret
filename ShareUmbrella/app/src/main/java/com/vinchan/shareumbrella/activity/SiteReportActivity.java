package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SiteReportActivity extends BaseActivity {


    @BindView(R.id.top_view)
    RelativeLayout topView;
    @BindView(R.id.last_cds_num_tv)
    TextView lastCdsNumTv;
    @BindView(R.id.last_dsz_num_tv)
    TextView lastDszNumTv;
    @BindView(R.id.cs_goods_num_add_iv)
    ImageView csGoodsNumAddIv;
    @BindView(R.id.cs_edit_goods_num_tv)
    TextView csEditGoodsNumTv;
    @BindView(R.id.cs_goods_num_cut_iv)
    ImageView csGoodsNumCutIv;
    @BindView(R.id.ds_goods_num_add_iv)
    ImageView dsGoodsNumAddIv;
    @BindView(R.id.ds_edit_goods_num_tv)
    TextView dsEditGoodsNumTv;
    @BindView(R.id.ds_goods_num_cut_iv)
    ImageView dsGoodsNumCutIv;
    @BindView(R.id.dss_goods_num_add_iv)
    ImageView dssGoodsNumAddIv;
    @BindView(R.id.dss_edit_goods_num_tv)
    TextView dssEditGoodsNumTv;
    @BindView(R.id.dss_goods_num_cut_iv)
    ImageView dssGoodsNumCutIv;
    @BindView(R.id.this_cds_num_tv)
    TextView thisCdsNumTv;
    @BindView(R.id.this_dsz_num_tv)
    TextView thisDszNumTv;
    @BindView(R.id.submit_btn)
    Button submitBtn;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;
    @BindView(R.id.bcz_title_tv)
    TextView bczTitleTv;
    @BindView(R.id.bdz_title_tv)
    TextView bdzTitleTv;
    @BindView(R.id.bdsz_title_tv)
    TextView bdszTitleTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_site_report;
    }

    @Override
    public String setTitle() {
        return getString(R.string.site_report);
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

    @OnClick({R.id.cs_goods_num_add_iv, R.id.cs_goods_num_cut_iv, R.id.ds_goods_num_add_iv, R.id.ds_goods_num_cut_iv, R.id.dss_goods_num_add_iv, R.id.dss_goods_num_cut_iv, R.id.submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cs_goods_num_add_iv:
                break;
            case R.id.cs_goods_num_cut_iv:
                break;
            case R.id.ds_goods_num_add_iv:
                break;
            case R.id.ds_goods_num_cut_iv:
                break;
            case R.id.dss_goods_num_add_iv:
                break;
            case R.id.dss_goods_num_cut_iv:
                break;
            case R.id.submit_btn:
                break;
        }
    }
}
