package com.dangong.oksan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.view.pictureTaker.PictureTakeDialog;
import com.dangong.oksan.view.pictureTaker.PictureTaker;
import com.dangong.oksan.view.roundImage.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 维护人员个人中心
 */
public class MaintainPersonCenterActivity extends BaseActivity {


    @BindView(R.id.custom_header_view)
    RoundedImageView customHeaderView;
    @BindView(R.id.phone_num_tv)
    TextView phoneNumTv;
    @BindView(R.id.history_rl)
    RelativeLayout historyRl;
    @BindView(R.id.help_rl)
    RelativeLayout helpRl;
    @BindView(R.id.guide_rl)
    RelativeLayout guideRl;
    @BindView(R.id.setting_rl)
    RelativeLayout settingRl;
    @BindView(R.id.customer_service_tv)
    TextView customerServiceTv;
    @BindView(R.id.work_time_tv)
    TextView workTimeTv;
    @BindView(R.id.wechat_rl)
    RelativeLayout wechatRl;
    @BindView(R.id.loading_progress_bar)
    ProgressBar loadingProgressBar;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;
    private PictureTaker pictureTaker;//图片选择器
    private PictureTakeDialog pictureTakeDialog;//图片选择弹窗


    @Override
    public int getLayoutId() {
        return R.layout.activity_maintain_person_center;
    }

    @Override
    public String setTitle() {
        return getString(R.string.person_center);
    }

    @Override
    public void init() {
        super.init();
        initPictureTaker();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pictureTaker.onActivityResult(data, requestCode);
    }

    public void initPictureTaker() {

        pictureTaker = new PictureTaker(this, "/oksan");
        pictureTaker.setEnableCrop(true);
        pictureTaker.setOnTakePictureListener(new PictureTaker.OnTakePictureListener() {
            @Override
            public void onPictureTaked(Bitmap bitmap, String url) {
                if (bitmap != null) {
                    customHeaderView.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 修改头像的弹窗
     */
    public void showPicturePckDialog() {
        if (pictureTakeDialog == null) {
            pictureTakeDialog = new PictureTakeDialog(this, pictureTaker);
        }
        pictureTakeDialog.show();
    }

    @OnClick({R.id.custom_header_view, R.id.history_rl, R.id.help_rl, R.id.guide_rl, R.id.setting_rl, R.id.wechat_rl,R.id.withdrawal_rule_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.custom_header_view:
                showPicturePckDialog();
                break;

            case R.id.history_rl:
                ActivityUtils.startActivity(WorkHistoryActivity.class);
                break;
            case R.id.help_rl:
                ActivityUtils.startActivity(HelpActivity.class);
                break;
            case R.id.guide_rl:
                break;
            case R.id.setting_rl:
                ActivityUtils.startActivity(SettingActivity.class);
                break;
            case R.id.wechat_rl:
                PhoneUtils.call(getString(R.string.service_phone));
                break;
            case R.id.withdrawal_rule_tv:
                PhoneUtils.call(getString(R.string.service_phone));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
