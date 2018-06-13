package com.vinchan.shareumbrella.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.adapter.RankingListAdapter;
import com.vinchan.shareumbrella.model.OrderDetail;
import com.vinchan.shareumbrella.view.PullRereshRecycleView;
import com.vinchan.shareumbrella.view.roundImage.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RankingListActivity extends BaseActivity {


    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.wcdl_tv)
    TextView wcdlTv;
    @BindView(R.id.second_header_iv)
    RoundedImageView secondHeaderIv;
    @BindView(R.id.second_phone_tv)
    TextView secondPhoneTv;
    @BindView(R.id.second_tv)
    TextView secondTv;
    @BindView(R.id.first_header_iv)
    RoundedImageView firstHeaderIv;
    @BindView(R.id.first_phone_tv)
    TextView firstPhoneTv;
    @BindView(R.id.first_tv)
    TextView firstTv;
    @BindView(R.id.third_header_iv)
    RoundedImageView thirdHeaderIv;
    @BindView(R.id.third_phone_tv)
    TextView thirdPhoneTv;
    @BindView(R.id.third_tv)
    TextView thirdTv;
    @BindView(R.id.raking_recyclerview)
    PullRereshRecycleView rakingRecyclerview;

    private RankingListAdapter mAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_ranking_list;
    }

    @Override
    public String setTitle() {
        return getString(R.string.ranking);
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
        mAdapter = new RankingListAdapter(list,RankingListActivity.this);
        rakingRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        rakingRecyclerview.setAdapter(mAdapter);
    }


    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
