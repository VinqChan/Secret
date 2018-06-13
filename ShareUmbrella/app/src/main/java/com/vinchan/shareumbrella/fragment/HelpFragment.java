package com.vinchan.shareumbrella.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.adapter.HelpListAdapter;
import com.vinchan.shareumbrella.model.OrderDetail;
import com.vinchan.shareumbrella.view.PullRereshRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Vinchan on 2018/6/5.
 */

public class HelpFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.help_recyclerview)
    PullRereshRecycleView helpRecyclerview;
    Unbinder unbinder;
    private HelpListAdapter mAdapter;
    private int dateType;

    public static HelpFragment getInstance(int dateType) {
        return new HelpFragment(dateType);
    }

    public HelpFragment() {
        super();
    }

    public HelpFragment(int dateType) {
        this.dateType = dateType;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragmnet_help, null);
        unbinder = ButterKnife.bind(this, view);
        getData();
        return view;
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
        mAdapter = new HelpListAdapter(list,getActivity());
        helpRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        helpRecyclerview.setAdapter(mAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
