package com.dangong.oksan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.model.OrderDetail;
import com.dangong.oksan.view.roundImage.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/3/003.
 */

public class RankingListAdapter extends RecyclerView.Adapter {

    private List<OrderDetail.OrderDetailItem> listData = new ArrayList<>();
    private Context mContext;


    public RankingListAdapter(List<OrderDetail.OrderDetailItem> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_raking_list,  parent, false);
        return new RankingHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetail.OrderDetailItem order = listData.get(position);
        ((RankingHoder) holder).phoneNumTv.setText("11111");
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class RankingHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.sequence_num_tv)
        TextView sequenceNumTv;
        @BindView(R.id.header_view)
        RoundedImageView headerView;
        @BindView(R.id.phone_num_tv)
        TextView phoneNumTv;
        @BindView(R.id.person_num_tv)
        TextView personNumTv;
        public RankingHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
