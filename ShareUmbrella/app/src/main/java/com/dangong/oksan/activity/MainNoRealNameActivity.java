package com.dangong.oksan.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;

import butterknife.BindView;
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
    public int getLayoutId() {
        return R.layout.activity_main_no_real_name;
    }

    @Override
    public String setTitle() {
        return getString(R.string.main_title);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.supplementaryInfo_btn, R.id.scanner_btn, R.id.invitation_rl, R.id.guide_rl,R.id.wechat_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.supplementaryInfo_btn:
                ActivityUtils.startActivity(RealNameCertifiActivity.class);
                break;
            case R.id.scanner_btn:
                ActivityUtils.startActivity(ScannerActivity.class);
                break;
            case R.id.invitation_rl:
                ActivityUtils.startActivity(InvatationRewardActivity.class);
                break;
            case R.id.guide_rl:
                ActivityUtils.startActivity(GuideWelfareActivity.class);
                break;
            case R.id.wechat_rl:
                PhoneUtils.call(getString(R.string.service_phone));
                break;

        }
    }

}
