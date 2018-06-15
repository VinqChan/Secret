package com.dangong.oksan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class AccountBalanceListAdapter extends RecyclerView.Adapter {


    private List<OrderDetail.OrderDetailItem> listData = new ArrayList<>();
    private Context mContext;


    public AccountBalanceListAdapter(List<OrderDetail.OrderDetailItem> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_withdrawl_list, parent, false);
        return new AccountBalanceHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetail.OrderDetailItem order = listData.get(position);
        ((AccountBalanceHoder) holder).orderIdTv.setText("11111");
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class AccountBalanceHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.year_month_tv)
        TextView yearMonthTv;
        @BindView(R.id.hour_tv)
        TextView hourTv;
        @BindView(R.id.action_tv)
        TextView actionTv;
        @BindView(R.id.order_id_tv)
        TextView orderIdTv;
        @BindView(R.id.account_tv)
        TextView accountTv;
        public AccountBalanceHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
