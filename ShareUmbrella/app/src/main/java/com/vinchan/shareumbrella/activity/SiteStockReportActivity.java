package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.callback.YearMonthDayPickerCallBack;
import com.vinchan.shareumbrella.util.PickerUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SiteStockReportActivity extends BaseActivity {


    @BindView(R.id.san_code_et)
    EditText sanCodeEt;
    @BindView(R.id.num_of_cs_et)
    EditText numOfCsEt;
    @BindView(R.id.num_of_sccs_et)
    EditText numOfSccsEt;
    @BindView(R.id.num_of_ds_et)
    EditText numOfDsEt;
    @BindView(R.id.num_of_scds_et)
    EditText numOfScdsEt;
    @BindView(R.id.num_of_dss_et)
    EditText numOfDssEt;
    @BindView(R.id.num_of_scdss_et)
    EditText numOfScdssEt;
    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.select_date_rl)
    RelativeLayout selectDateRl;
    @BindView(R.id.matain_code_tv)
    TextView matainCodeTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_site_stock_report;
    }

    @Override
    public String setTitle() {
        return getString(R.string.site_stock_report);
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


    @OnClick(R.id.select_date_rl)
    public void onViewClicked() {
        PickerUtils.yearMonthDayPicker(this, new YearMonthDayPickerCallBack() {
            @Override
            public void yearMonthDay(String year, String month, String day) {
                dateTv.setText(year+"-"+month+"-"+day);
            }
        });
    }
}
