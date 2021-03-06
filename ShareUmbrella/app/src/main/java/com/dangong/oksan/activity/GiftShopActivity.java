package com.dangong.oksan.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.adapter.GiftShopListAdapter;
import com.dangong.oksan.model.OrderDetail;
import com.dangong.oksan.view.PullRereshRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GiftShopActivity extends BaseActivity {


    @BindView(R.id.gift_shop_recyclerview)
    PullRereshRecycleView giftShopRecyclerview;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    private GiftShopListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gift_shop;
    }

    @Override
    public String setTitle() {
        return getString(R.string.gift_shop);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
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
        mAdapter = new GiftShopListAdapter(list, GiftShopActivity.this);
        giftShopRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        giftShopRecyclerview.setAdapter(mAdapter);
    }

    @OnClick(R.id.own_info_iv)
    public void onViewClicked() {

    }
}
