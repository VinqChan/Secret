package com.dangong.oksan.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MaintainMainActivity extends BaseActivity {


    @BindView(R.id.start_maintain_btn)
    Button startMaintainBtn;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_maintain;
    }

    @Override
    public String setTitle() {
        return getString(R.string.maintain_manager);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.start_maintain_btn, R.id.own_info_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_maintain_btn:
                ActivityUtils.startActivity(EnterMaintainActivity.class);
                break;
            case R.id.own_info_iv:
                break;
        }
    }
}
