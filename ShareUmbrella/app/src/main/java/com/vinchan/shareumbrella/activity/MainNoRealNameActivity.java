package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vinchan.shareumbrella.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainNoRealNameActivity extends BaseActivity {


    @BindView(R.id.supplementaryInfo_btn)
    LinearLayout supplementaryInfoBtn;
    @BindView(R.id.scanner_btn)
    LinearLayout scannerBtn;
    @BindView(R.id.invitation_rl)
    RelativeLayout invitationRl;
    @BindView(R.id.guide_rl)
    RelativeLayout guideRl;
    @BindView(R.id.phone_num_tv)
    TextView phoneNumTv;
    @BindView(R.id.work_time_tv)
    TextView workTimeTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.wechat_rl)
    RelativeLayout wechatRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_no_real_name);
        ButterKnife.bind(this);
        titleTv.setText(getString(R.string.main_title));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.supplementaryInfo_btn, R.id.scanner_btn, R.id.invitation_rl, R.id.guide_rl, R.id.title_back_iv,R.id.wechat_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.supplementaryInfo_btn:
                break;
            case R.id.scanner_btn:
                break;
            case R.id.invitation_rl:
                break;
            case R.id.guide_rl:
                break;
            case R.id.wechat_rl:
                break;
            case R.id.title_back_iv:
                finish();
                break;
        }
    }

}
