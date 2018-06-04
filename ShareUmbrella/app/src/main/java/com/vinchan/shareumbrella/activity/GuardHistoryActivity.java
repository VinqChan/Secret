package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.adapter.GuardHistoryListAdapter;
import com.vinchan.shareumbrella.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuardHistoryActivity extends BaseActivity {


    @BindView(R.id.guard_history_recyclerview)
    RecyclerView guardHistoryRecyclerview;
    private GuardHistoryListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guard_history;
    }

    @Override
    public String setTitle() {
        return getString(R.string.guard_history);
    }

    @Override
    public void init() {
        super.init();
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
        mAdapter = new GuardHistoryListAdapter(list, GuardHistoryActivity.this);
        guardHistoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        guardHistoryRecyclerview.setAdapter(mAdapter);
    }


    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
