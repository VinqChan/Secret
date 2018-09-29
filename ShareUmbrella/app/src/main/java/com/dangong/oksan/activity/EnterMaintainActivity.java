package com.dangong.oksan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dangong.oksan.activity.ScannerActivity.SCANNER_KEY;
import static com.dangong.oksan.activity.ScannerActivity.TYPE_REMOVE;

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
                ActivityUtils.startActivity(AddShopMapActivity.class);
                break;
            case R.id.site_manager_btn:
                ActivityUtils.startActivity(ManagerAndMaintainMainActivity.class);
                break;
            case R.id.remove_btn:
                Bundle bundle = new Bundle();
                bundle.putInt(SCANNER_KEY, TYPE_REMOVE);
                ActivityUtils.startActivity(bundle,ScannerActivity.class);
                break;
            case R.id.own_info_iv:
                ActivityUtils.startActivity(ManagerPersonCenterActivity.class);
                break;
        }
    }

     }
