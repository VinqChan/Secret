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

public class WorkHistoryListAdapter extends RecyclerView.Adapter {



    private List<OrderDetail.OrderDetailItem> listData = new ArrayList<>();
    private Context mContext;


    public WorkHistoryListAdapter(List<OrderDetail.OrderDetailItem> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_work_history, parent, false);
        return new WorkHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetail.OrderDetailItem order = listData.get(position);
        ((WorkHistoryHolder) holder).dateTv.setText("11111");
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class WorkHistoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date_tv)
        TextView dateTv;
        @BindView(R.id.code_tv)
        TextView codeTv;
        @BindView(R.id.add_site_code)
        TextView addSiteCode;
        @BindView(R.id.site_name_code)
        TextView siteNameCode;

        public WorkHistoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
