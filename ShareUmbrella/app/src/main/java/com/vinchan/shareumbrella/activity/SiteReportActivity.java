package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.view.dialog.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 进货/减货站点汇报
 */
public class SiteReportActivity extends BaseActivity {

    public static final int TYPE_ADD = 1;//进货
    public static final int TYPE_CUT = 2;//减货
    public static final String TYPE_KEY = "report_type";

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
    @BindView(R.id.add_top_view)
    LinearLayout addTopView;
    @BindView(R.id.cut_top_view)
    LinearLayout cutTopView;
    private int type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_site_report;
    }

    @Override
    public String setTitle() {
        return "";
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    public void initView() {
        super.initView();
        ownInfoIv.setVisibility(View.VISIBLE);
        type = getIntent().getIntExtra(TYPE_KEY, TYPE_ADD);

        if (type == TYPE_ADD) {
            setTitle(getString(R.string.site_add_report));
            addTopView.setVisibility(View.VISIBLE);
            cutTopView.setVisibility(View.GONE);
            bczTitleTv.setText("补  长  伞");
            bdzTitleTv.setText("补  短  伞");
            bdszTitleTv.setText("补登山伞");

        } else {
            bczTitleTv.setText("减  长  伞");
            bdzTitleTv.setText("减  短  伞");
            bdszTitleTv.setText("减登山伞");
            setTitle(getString(R.string.site_cut_report));
            addTopView.setVisibility(View.GONE);
            cutTopView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.cs_goods_num_add_iv, R.id.cs_goods_num_cut_iv, R.id.ds_goods_num_add_iv, R.id.ds_goods_num_cut_iv, R.id.dss_goods_num_add_iv, R.id.dss_goods_num_cut_iv, R.id.submit_btn})
    public void onViewClicked(View view) {
        int longNum = Integer.valueOf(csEditGoodsNumTv.getText().toString().trim());
        int shortNum = Integer.valueOf(dsEditGoodsNumTv.getText().toString().trim());
        int dssNum = Integer.valueOf(dssEditGoodsNumTv.getText().toString().trim());
        switch (view.getId()) {
            case R.id.cs_goods_num_add_iv:
                handleNum(csEditGoodsNumTv,longNum,TYPE_ADD);
                break;
            case R.id.cs_goods_num_cut_iv:
                handleNum(csEditGoodsNumTv,longNum,TYPE_CUT);
                break;
            case R.id.ds_goods_num_add_iv:
                handleNum(dsEditGoodsNumTv,shortNum,TYPE_ADD);
                break;
            case R.id.ds_goods_num_cut_iv:
                handleNum(dsEditGoodsNumTv,shortNum,TYPE_CUT);
                break;
            case R.id.dss_goods_num_add_iv:
                handleNum(dssEditGoodsNumTv,dssNum,TYPE_ADD);
                break;
            case R.id.dss_goods_num_cut_iv:
                handleNum(dssEditGoodsNumTv,dssNum,TYPE_CUT);
                break;
            case R.id.submit_btn:
                CommonDialog mDialog = new CommonDialog(this, CommonDialog.EnumDialogType.CUT);
                mDialog.show();
                break;
        }
    }

    public void handleNum(TextView view ,int num,int type){
        if(type==TYPE_ADD){
            num++;
            view.setText(num+"");
        }else {
            if(num ==1){
                num = 1;
            }else {
                num--;
            }
            view.setText(num+"");
        }
    }

}
