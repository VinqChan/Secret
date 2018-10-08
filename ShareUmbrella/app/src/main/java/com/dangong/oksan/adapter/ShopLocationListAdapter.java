package com.dangong.oksan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.model.NearShopModel;
import com.dangong.oksan.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class ShopLocationListAdapter extends RecyclerView.Adapter {



    private List<NearShopModel.ResultBean> listData = new ArrayList<>();
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    public ShopLocationListAdapter(List<NearShopModel.ResultBean> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_location, parent, false);
        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final NearShopModel.ResultBean mode = listData.get(position);
         ((LocationHolder) holder).addressTv.setText(mode.getAddress());
        View itemView = ((LinearLayout) holder.itemView).getChildAt(0);
        itemView.setBackgroundResource(R.drawable.bg_linerlayout);
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    class LocationHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.address_tv)
        TextView addressTv;

        public LocationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
