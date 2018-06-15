package com.dangong.oksan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterMaintainActivity extends BaseActivity {


    @BindView(R.id.add_shop_btn)
    Button addShopBtn;
    @BindView(R.id.site_manager_btn)
    Button siteManagerBtn;
    @BindView(R.id.remove_btn)
    Button removeBtn;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_maintain;
    }

    @Override
    public String setTitle() {
        return getString(R.string.maintain_project);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_shop_btn, R.id.site_manager_btn, R.id.remove_btn, R.id.own_info_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_shop_btn:
                break;
            case R.id.site_manager_btn:
                break;
            case R.id.remove_btn:
                break;
            case R.id.own_info_iv:
                break;
        }
    }

     }
