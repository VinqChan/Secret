package com.dangong.oksan.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.callback.YearMonthPickerCallBack;
import com.dangong.oksan.model.OrderDetail;
import com.dangong.oksan.util.PickerUtils;
import com.dangong.oksan.adapter.AccountBalanceListAdapter;
import com.dangong.oksan.view.PullRereshRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    PullRereshRecycleView withdrawalHistoryRecyclerview;
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
        PickerUtils.yearMonthPicker(this, new YearMonthPickerCallBack() {
            @Override
            public void yearAndMonth(String year, String month) {
                dateTv.setText(year+"年"+month+"月");
            }
        });
    }

}
