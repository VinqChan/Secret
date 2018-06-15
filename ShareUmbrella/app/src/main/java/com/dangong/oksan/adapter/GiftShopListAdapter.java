package com.dangong.oksan.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.model.OrderDetail;
import com.dangong.oksan.activity.GiftExchangeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class GiftShopListAdapter extends RecyclerView.Adapter {



    private List<OrderDetail.OrderDetailItem> listData = new ArrayList<>();
    private Context mContext;


    public GiftShopListAdapter(List<OrderDetail.OrderDetailItem> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gift_shop, parent, false);
        return new GiftShopHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetail.OrderDetailItem order = listData.get(position);
        ((GiftShopHolder) holder).expressFeeTv.setText("运费：100");
        ((GiftShopHolder) holder).orginPriceTv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        ((GiftShopHolder) holder).exchangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(GiftExchangeActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class GiftShopHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_iv)
        ImageView shopIv;
        @BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.price_tv)
        TextView priceTv;
        @BindView(R.id.orgin_price_tv)
        TextView orginPriceTv;
        @BindView(R.id.express_fee_tv)
        TextView expressFeeTv;
        @BindView(R.id.exchange_btn)
        Button exchangeBtn;

        public GiftShopHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
