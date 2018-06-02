package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.adapter.WallteAdapter;
import com.vinchan.shareumbrella.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletActivity extends BaseActivity {


    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.income_tv)
    TextView incomeTv;
    @BindView(R.id.yue_tv)
    TextView yueTv;
    @BindView(R.id.withdrawal_tv)
    TextView withdrawalTv;
    @BindView(R.id.wallet_recyclerview)
    RecyclerView walletRecyclerview;
    WallteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        titleTv.setText(getString(R.string.my_wallet));

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

        walletRecyclerview.setLayoutManager( new LinearLayoutManager(this));
        adapter = new WallteAdapter(list, WalletActivity.this);
        walletRecyclerview.setAdapter(adapter);
    }


    @OnClick({R.id.title_back_iv, R.id.withdrawal_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.withdrawal_tv:
                break;
        }
    }
}
