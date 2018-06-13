package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.adapter.FaultHandleAdapter;
import com.vinchan.shareumbrella.model.OrderDetail;
import com.vinchan.shareumbrella.view.PullRereshRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaultHandleActivity extends BaseActivity {


    @BindView(R.id.fault_handle_recyclerview)
    PullRereshRecycleView faultHandleRecyclerview;
    private FaultHandleAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fault_handle;
    }

    @Override
    public String setTitle() {
        return getString(R.string.fault_handle);
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
        mAdapter = new FaultHandleAdapter(list, FaultHandleActivity.this);
        faultHandleRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        faultHandleRecyclerview.setAdapter(mAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
