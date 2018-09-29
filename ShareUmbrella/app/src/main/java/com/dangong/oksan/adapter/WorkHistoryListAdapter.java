package com.dangong.oksan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.model.OrderDetail;
import com.dangong.oksan.model.SiteWorkLoggModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinchan on 2018/6/3/003.
 */

public class WorkHistoryListAdapter extends RecyclerView.Adapter {



    private List<SiteWorkLoggModel.ResultBean>  listData = new ArrayList<>();
    private Context mContext;


    public WorkHistoryListAdapter(List<SiteWorkLoggModel.ResultBean>  listData, Context mContext) {
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
        final SiteWorkLoggModel.ResultBean order = listData.get(position);
         // 操作状态类型 //0新增站点 1撤销站点 2入库 3 出库 4 汇报伞仓 5 汇报伞槽
        String action ="";
        int longNum = order.getLongUmbrella().endsWith("")?0:Integer.valueOf(order.getLongUmbrella());
        int shortNum = order.getShorUmbrella().endsWith("")?0:Integer.valueOf(order.getShorUmbrella());
        int alpenstockNum = order.getAlpenstock().endsWith("")?0:Integer.valueOf(order.getAlpenstock());
        switch (order.getSiteSatus()){
            case "0":
                action="新增站点 ：";
                ((WorkHistoryHolder) holder).siteNameCode.setText(order.getName());
            break;
            case "1":
                action="撤销站点 ：";
                ((WorkHistoryHolder) holder).siteNameCode.setText(order.getName());
                break;
            case "2":
                action="入库 ：";
                ((WorkHistoryHolder) holder).siteNameCode.setText("长伞 "+longNum+"把 "+"短伞 "+shortNum+"把 "+"登山伞 "+alpenstockNum+"把 ");
                break;
            case "3":
                action="出库 ：";
                ((WorkHistoryHolder) holder).siteNameCode.setText("长伞 "+longNum+"把 "+"短伞 "+shortNum+"把 "+"登山伞 "+alpenstockNum+"把 ");                break;
            case "4":
                action="汇报伞仓 ：";
                ((WorkHistoryHolder) holder).siteNameCode.setText("长伞 "+longNum+"把 "+"短伞 "+shortNum+"把 "+"登山伞 "+alpenstockNum+"把 ");                break;
            case "5":
                action="汇报伞槽  ：";
                ((WorkHistoryHolder) holder).siteNameCode.setText("长伞 "+longNum+"把 "+"短伞 "+shortNum+"把 "+"登山伞 "+alpenstockNum+"把 ");                break;
        }
        ((WorkHistoryHolder) holder).actionTv.setText(action);
        ((WorkHistoryHolder) holder).dateTv.setText(order.getTime());


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
        @BindView(R.id.action_tv)
        TextView actionTv;

        public WorkHistoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
