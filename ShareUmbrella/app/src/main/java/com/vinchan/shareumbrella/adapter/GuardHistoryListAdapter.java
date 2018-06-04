package com.vinchan.shareumbrella.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class GuardHistoryListAdapter extends RecyclerView.Adapter {


    private List<OrderDetail.OrderDetailItem> listData = new ArrayList<>();
    private Context mContext;


    public GuardHistoryListAdapter(List<OrderDetail.OrderDetailItem> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guard_history, parent, false);
        return new GuardHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetail.OrderDetailItem order = listData.get(position);
        ((GuardHistoryHolder) holder).orderIdTv.setText("11111");
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class GuardHistoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date_tv)
        TextView dateTv;
        @BindView(R.id.status_tv)
        TextView statusTv;
        @BindView(R.id.order_id_tv)
        TextView orderIdTv;
        @BindView(R.id.hour_tv)
        TextView hourTv;
        @BindView(R.id.fencheng_tv)
        TextView fenchengTv;
        @BindView(R.id.borrow_address_tv)
        TextView borrowAddressTv;
        @BindView(R.id.return_address_tv)
        TextView returnAddressTv;
        public GuardHistoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
