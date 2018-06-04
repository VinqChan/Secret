package com.vinchan.shareumbrella.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.adapter.AccountBalanceListAdapter;
import com.vinchan.shareumbrella.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;

public class AccountBalanceActivity extends BaseActivity {


    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.balance_tv)
    TextView balanceTv;
    @BindView(R.id.withdrawal_btn)
    Button withdrawalBtn;
    @BindView(R.id.select_date_rl)
    LinearLayout selectDateRl;
    @BindView(R.id.account_tv)
    TextView accountTv;
    @BindView(R.id.withdrawal_history_recyclerview)
    RecyclerView withdrawalHistoryRecyclerview;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;
    @BindView(R.id.date_tv)
    TextView dateTv;
    private AccountBalanceListAdapter mAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_account_balance;
    }

    @Override
    public String setTitle() {
        return getString(R.string.account_balance);
    }

    @Override
    public void init() {
        super.init();
        ButterKnife.bind(this);
        withdrawalRuleTv.setVisibility(View.VISIBLE);
        getData();
    }

    private void getData() {
        List<OrderDetail.OrderDetailItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderDetail.OrderDetailItem item = new OrderDetail().new OrderDetailItem();
            item.setDeviceId("1");
            item.setDeviceName("1");
            item.setDeviceTypeId("1");
            item.setInspectProjectId("1");
            item.setProjectCycle("1");
            list.add(item);
        }
        mAdapter = new AccountBalanceListAdapter(list,AccountBalanceActivity.this);
        withdrawalHistoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        withdrawalHistoryRecyclerview.setAdapter(mAdapter);
    }


    @OnClick({ R.id.withdrawal_btn, R.id.select_date_rl, R.id.withdrawal_rule_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.withdrawal_btn:
                break;
            case R.id.select_date_rl:
                selectDate();
                break;
            case R.id.withdrawal_rule_tv:
                break;
        }
    }

    private void selectDate() {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        picker.setDividerColor(getResources().getColor(R.color.picker_line_color));
        picker.setTextColor(getResources().getColor(R.color.gray_color));
        picker.setRangeStart(2018, 01, 01);
        picker.setTopLineColor(getResources().getColor(R.color.gray_color));
        picker.setRangeEnd(2100, 11, 11);
        picker.setSelectedItem(2018, 6);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                dateTv.setText(year+"年"+month+"月");
            }
        });
        picker.show();
        picker.getCancelButton().setTextSize(16);
        picker.getSubmitButton().setTextSize(16);
        picker.getCancelButton().setTextColor(getResources().getColor(R.color.gray_color));
        picker.getSubmitButton().setTextColor(getResources().getColor(R.color.main_color));
    }

}
