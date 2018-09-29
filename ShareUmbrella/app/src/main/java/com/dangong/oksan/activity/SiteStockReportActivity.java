package com.dangong.oksan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.api.ApiUtils;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.callback.YearMonthDayPickerCallBack;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.model.ReportRequestModel;
import com.dangong.oksan.util.PickerUtils;
import com.lljjcoder.style.citylist.Toast.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.submit_btn)
    Button submitBtn;

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
        matainCodeTv.setText(Constants.loginInfo.getWorkNo());
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


    @OnClick({R.id.select_date_rl, R.id.submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_date_rl:
                PickerUtils.yearMonthDayPicker(this, new YearMonthDayPickerCallBack() {
                    @Override
                    public void yearMonthDay(String year, String month, String day) {
                        dateTv.setText(year + "-" + month + "-" + day);
                    }
                });
                break;
            case R.id.submit_btn:
                submit();

                break;
        }
    }

    private void submit() {
        String sanCode = sanCodeEt.getText().toString().trim();
        String numOfCs = numOfCsEt.getText().toString().trim();
        String numOfSccs = numOfSccsEt.getText().toString().trim();
        String numOfDs = numOfDsEt.getText().toString().trim();
        String numOfScds = numOfScdsEt.getText().toString().trim();
        String numOfDss = numOfDssEt.getText().toString().trim();
        String numOfScdss = numOfScdssEt.getText().toString().trim();
        String date = dateTv.getText().toString().trim();
        String matainCode = matainCodeTv.getText().toString().trim();
        if(numOfCs.equals("")){
            ToastUtils.showLongToast(SiteStockReportActivity.this,"请输入伞仓长伞数量！");
            return;
        }
        if(numOfSccs.equals("")){
            ToastUtils.showLongToast(SiteStockReportActivity.this,"请输入伞槽长伞数量！");
            return;
        }
        if(numOfDs.equals("")){
            ToastUtils.showLongToast(SiteStockReportActivity.this,"请输入伞仓短伞数量！");
            return;
        }
        if(numOfScds.equals("")){
            ToastUtils.showLongToast(SiteStockReportActivity.this,"请输入伞槽短伞数量！");
            return;
        }
        if(numOfDss.equals("")){
            ToastUtils.showLongToast(SiteStockReportActivity.this,"请输入伞仓登山仗数量！");
            return;
        }
        if(numOfScdss.equals("")){
            ToastUtils.showLongToast(SiteStockReportActivity.this,"请输入伞槽登山仗数量！");
            return;
        }
        ReportRequestModel model = new ReportRequestModel();
        model.setLongUmbrellaLibrary(Integer.valueOf(numOfCs));
        model.setShorUmbrellaLibrary(Integer.valueOf(numOfDs));
        model.setAlpenstockLibrary(Integer.valueOf(numOfDss));
        model.setLongUmbrellaTrough(Integer.valueOf(numOfSccs));
        model.setShorUmbrellaTrough(Integer.valueOf(numOfScds));
        model.setAlpenstockLTrough(Integer.valueOf(numOfScdss));
        model.setEmployeeNo(Constants.loginInfo.getWorkNo());
        model.setSiteId(Constants.SITEID);

        ApiUtils.report(model, new ApiCallBack() {
            @Override
            public void success(Object response) {
              finish();
            }

            @Override
            public void fail() {
              ToastUtils.showLongToast(SiteStockReportActivity.this,"存货汇报失败！");
            }
        });
    }
}
