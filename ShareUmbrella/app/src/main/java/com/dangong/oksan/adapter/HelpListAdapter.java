package com.dangong.oksan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class HelpListAdapter extends RecyclerView.Adapter {



    private List<OrderDetail.OrderDetailItem> listData = new ArrayList<>();
    private Context mContext;


    public HelpListAdapter(List<OrderDetail.OrderDetailItem> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_help, parent, false);
        return new AccountBalanceHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetail.OrderDetailItem order = listData.get(position);
        // ((AccountBalanceHoder) holder).orderIdTv.setText("11111");
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    class AccountBalanceHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.question_tv)
        TextView questionTv;
        @BindView(R.id.question_rl)
        RelativeLayout questionRl;
        @BindView(R.id.line_view)
        View lineView;
        @BindView(R.id.answer)
        TextView answer;
        @BindView(R.id.arrow_iv)
        ImageView arrowIv;
        public AccountBalanceHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.question_rl)
        public void onViewClicked() {
            boolean isShow = lineView.getVisibility() == View.VISIBLE ? true : false;
            if (lineView.getVisibility() == View.VISIBLE) {
                lineView.setVisibility(View.GONE);
                answer.setVisibility(View.GONE);
                arrowIv.setImageResource(R.drawable.ic_bzzx_nor_xl);
            } else {
                lineView.setVisibility(View.VISIBLE);
                answer.setVisibility(View.VISIBLE);
                arrowIv.setImageResource(R.drawable.ic_bzzx_sel_xl);
            }

        }

    }
}
