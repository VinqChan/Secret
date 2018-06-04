package com.vinchan.shareumbrella.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.util.CountDownUtil;

import butterknife.BindView;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Vinchan on 2018/6/2/002.
 */

public class ScannerBorrowActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.time_gif)
    GifImageView timeGif;
    @BindView(R.id.remaing_time_tv)
    TextView remaingTimeTv;
    @BindView(R.id.borrow_num_tv)
    TextView borrowNumTv;
    private CountDownUtil countDownUtil ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scanner_borrow;
    }

    @Override
    public String setTitle() {
        return getString(R.string.scanner_borrow);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
        countDownUtil = new CountDownUtil();

        countDownUtil.setCountdownListener(new CountDownUtil.CountdownListener() {
            @Override
            public void onTick(CharSequence endPayText) {
                remaingTimeTv.setText(endPayText);
            }

            @Override
            public void onFinish() {
                remaingTimeTv.setText("00:00");
            }
        });
        countDownUtil.startCountdown(10*60*1000, 1000, CountDownUtil.TYPE_GROUP_ON_FAIL);
    }

    @OnClick({ R.id.own_info_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.own_info_iv:
                ActivityUtils.startActivity(PersonCenterActivity.class);
                break;
        }
    }
}
