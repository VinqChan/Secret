package com.dangong.oksan.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.model.SiteModel;
import com.dangong.oksan.api.ApiUtils;
import com.dangong.oksan.model.StockRequest;
import com.dangong.oksan.view.dialog.CommonDialog;
import com.lljjcoder.Constant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 站点进货汇报/站点减货汇报
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
    private int mLongNum=0;
    private int mShortNum=0;
    private int mDssNum=0;
    CommonDialog mDialog =null;
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
    public void initData() {
        super.initData();
        startLoading();
        ApiUtils.checkgoods(Constants.SITEID, new ApiCallBack() {
            @Override
            public void success(final Object response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                        SiteModel model = (SiteModel)response;
                        lastCdsNumTv.setText((Integer.valueOf(model.getResult().getLongUmbrella())+Integer.valueOf(model.getResult().getShorUmbrella()))+"");
                        lastDszNumTv.setText(model.getResult().getAlpenstock());
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
                 mLongNum = Integer.valueOf(csEditGoodsNumTv.getText().toString().trim());
                 mShortNum = Integer.valueOf(dsEditGoodsNumTv.getText().toString().trim());
                 mDssNum = Integer.valueOf(dssEditGoodsNumTv.getText().toString().trim());
                 if(type ==TYPE_CUT){
                     mDialog = new CommonDialog(this, CommonDialog.EnumDialogType.CUT);
                 }else {
                     mDialog = new CommonDialog(this, CommonDialog.EnumDialogType.ADD);
                 }

                mDialog.setLongNum(longNum+"");
                mDialog.setShortNum(shortNum+"");
                mDialog.setMountainNum(dssNum+"");
                mDialog.setnumOfBqmcds(lastCdsNumTv.getText().toString()+"");
                mDialog.setnumOfBqmdds(lastDszNumTv.getText().toString()+"");
                mDialog.show();
                mDialog.setListener(new CommonDialog.Listener() {
                    @Override
                    public void sure() {
                        StockRequest stockRequest = new StockRequest();
                        stockRequest.setLongUmbrella(mLongNum);
                        stockRequest.setShorUmbrella(mShortNum);
                        stockRequest.setAlpenstock(mDssNum);
                        stockRequest.setPhone(Constants.loginInfo.getPhone());
                        stockRequest.setSiteId(Constants.SITEID);
                        ApiUtils.stock(stockRequest,type, new ApiCallBack() {
                         @Override
                         public void success(Object response) {
                                finish();
                         }

                         @Override
                         public void fail() {

                         }
                     });
                    }

                    @Override
                    public void cancle() {

                    }
                });
                break;
        }
    }

    public void handleNum(TextView view ,int num,int type){
        if(type==TYPE_ADD){
            num++;
            view.setText(num+"");
        }else {
            if(num ==0){
                num = 0;
            }else {
                num--;
            }
            view.setText(num+"");
        }
    }

}
