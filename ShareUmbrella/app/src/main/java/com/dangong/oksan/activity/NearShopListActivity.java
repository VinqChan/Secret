package com.dangong.oksan.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.adapter.ShopLocationListAdapter;
import com.dangong.oksan.api.ApiUtils;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.model.NearShopModel;
import com.dangong.oksan.view.PullRereshRecycleView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NearShopListActivity extends BaseActivity {

    @BindView(R.id.work_history_recyclerview)
    PullRereshRecycleView nearShopRl;
    private ShopLocationListAdapter mAdapter;
    private List<NearShopModel.ResultBean> listData = new ArrayList<>();
    private int limit = 5;
    private int offset  = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_near_shop;
    }

    @Override
    public String setTitle() {
        return getString(R.string.near_shop);
    }

    @Override
    public void init() {
        super.init();

        getData();
    }

    private void getData() {
        ApiUtils.getNearShop(Constants.LONGITUDE, Constants.LATITUDE, 2000.0, "", new ApiCallBack() {
            @Override
            public void success(final Object response) {
                listData = ((NearShopModel)response).getResult();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new ShopLocationListAdapter(listData, NearShopListActivity.this);
                        nearShopRl.setLayoutManager(new LinearLayoutManager(NearShopListActivity.this));
                        nearShopRl.setReFreshEnabled(false);
                        nearShopRl.setLoadMoreEnabled(false);
                        nearShopRl.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new ShopLocationListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ActivityUtils.startActivity(AddShopActivity.class);
                            }
                        });
                    }
                });
            }

            @Override
            public void fail() {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
