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
 * Created by Vinchan on 2017/10/12.
 */

public class WallteManagerAdapter extends RecyclerView.Adapter {

    private List<OrderDetail.OrderDetailItem> listData = new ArrayList<>();
    private Context mContext;
    private static final int ITEM_TYPE_HEADER = 1;
    private int defaultSelection = -1;

    public WallteManagerAdapter(List<OrderDetail.OrderDetailItem> listData, Context context) {
        this.listData = listData;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_manager_wallet_list_header, parent, false);
            HeaderViewHoder viewHolder = new HeaderViewHoder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_manager_wallet, parent, false);
            return new WalletViewHolder(view);
        }

    }

    public void setData(List<OrderDetail.OrderDetailItem> data) {
        this.listData = data;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetail.OrderDetailItem order = listData.get(position);

        if (holder instanceof HeaderViewHoder) {
            HeaderViewHoder footHolder = (HeaderViewHoder) holder;
        } else {
            WalletViewHolder itemHolder = (WalletViewHolder) holder;
            //itemHolder.dateTv.setText("5555");
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    /**
     * @param position 设置高亮状态的item
     */
    public void setSelectPosition(int position) {
        if (position < 0) {
            position = 0;
        } else if (position > listData.size()) {
            position = listData.size();
        }
        if (!(position < 0 || position > listData.size())) {
            defaultSelection = position;
            notifyDataSetChanged();
        }
    }

    class WalletViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date_tv)
        public TextView dateTv;
        @BindView(R.id.num_tv)
        public TextView numTv;
        @BindView(R.id.income_tv)
        TextView incomeTv;
        @BindView(R.id.count_income_tv)
        TextView countIncomeTv;

        public WalletViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    /*底部Item*/
    public class HeaderViewHoder extends RecyclerView.ViewHolder {


        public HeaderViewHoder(View itemView) {
            super(itemView);

        }
    }
}
