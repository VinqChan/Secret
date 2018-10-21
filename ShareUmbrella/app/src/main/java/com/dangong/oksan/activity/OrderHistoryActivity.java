package com.dangong.oksan.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.adapter.WorkHistoryListAdapter;
import com.dangong.oksan.api.ApiUtils;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.model.SiteWorkLoggModel;
import com.dangong.oksan.view.PullRereshRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryActivity extends BaseActivity {


    @BindView(R.id.work_history_recyclerview)
    PullRereshRecycleView workHistoryRecyclerview;
    private WorkHistoryListAdapter mAdapter;
    private List<SiteWorkLoggModel.ResultBean> listData = new ArrayList<>();
    private int limit = 5;
    private int offset  = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_history;
    }

    @Override
    public String setTitle() {
        return getString(R.string.order_history);
    }

    @Override
    public void init() {
        super.init();

        getData();
    }

    private void getData() {
        ApiUtils.getSiteWorkHistory(limit+"", offset+"", new ApiCallBack() {
            @Override
            public void success(final Object response) {
                listData = (List<SiteWorkLoggModel.ResultBean>) response;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new WorkHistoryListAdapter(listData, OrderHistoryActivity.this);
                        workHistoryRecyclerview.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));
                        workHistoryRecyclerview.setReFreshEnabled(false);
                        workHistoryRecyclerview.setLoadMoreEnabled(true);
                        // 监听刷新事件
                        workHistoryRecyclerview.setRefreshAndLoadMoreListener(new PullRereshRecycleView.OnRefreshAndLoadMoreListener() {
                            @Override
                            public void onRefresh() {

                            }

                            @Override
                            public void onLoadMore() {
                                offset+=limit;
                                Log.e(TAG, "------onLoadMore: "+offset );
                                ApiUtils.getSiteWorkHistory(limit+"", offset+"", new ApiCallBack() {

                                    @Override
                                    public void success( Object response) {
                                        final   List<SiteWorkLoggModel.ResultBean> list = (List<SiteWorkLoggModel.ResultBean>) response;
                                        if(list.size()==0){
                                            workHistoryRecyclerview.setloadMoreComplete();
                                            return;
                                        }

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                listData.addAll(list);
                                                mAdapter.notifyDataSetChanged();
                                                workHistoryRecyclerview.setloadMoreComplete();
                                            }
                                        });
                                    }

                                    @Override
                                    public void fail() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                workHistoryRecyclerview.setloadMoreComplete();
                                                workHistoryRecyclerview.setNoMoreData(true);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        workHistoryRecyclerview.setAdapter(mAdapter);
                    }
                });
            }

            @Override
            public void fail() {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
