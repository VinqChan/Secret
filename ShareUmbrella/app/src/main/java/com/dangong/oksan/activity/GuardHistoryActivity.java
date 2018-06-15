package com.dangong.oksan.activity;

import android.support.v7.widget.LinearLayoutManager;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.adapter.GuardHistoryListAdapter;
import com.dangong.oksan.model.OrderDetail;
import com.dangong.oksan.view.PullRereshRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GuardHistoryActivity extends BaseActivity {


    @BindView(R.id.guard_history_recyclerview)
    PullRereshRecycleView guardHistoryRecyclerview;
    private GuardHistoryListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide_history;
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
}

