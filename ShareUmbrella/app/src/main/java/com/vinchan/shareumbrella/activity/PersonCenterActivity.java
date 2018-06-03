package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.adapter.RankingListAdapter;
import com.vinchan.shareumbrella.view.pictureTaker.PictureTakeDialog;
import com.vinchan.shareumbrella.view.pictureTaker.PictureTaker;
import com.vinchan.shareumbrella.view.roundImage.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonCenterActivity extends BaseActivity {


    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    @BindView(R.id.custom_header_view)
    RoundedImageView customHeaderView;
    @BindView(R.id.phone_num_tv)
    TextView phoneNumTv;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.arrow_4)
    ImageView arrow4;
    @BindView(R.id.wallet_rl)
    RelativeLayout walletRl;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.arrow_5)
    ImageView arrow5;
    @BindView(R.id.history_rl)
    RelativeLayout historyRl;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.arrow_6)
    ImageView arrow6;
    @BindView(R.id.ranking_rl)
    RelativeLayout rankingRl;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.arrow_1)
    ImageView arrow1;
    @BindView(R.id.invitation_rl)
    RelativeLayout invitationRl;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.arrow_2)
    ImageView arrow2;
    @BindView(R.id.gift_shop_rl)
    RelativeLayout giftShopRl;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.arrow_7)
    ImageView arrow7;
    @BindView(R.id.help_rl)
    RelativeLayout helpRl;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.arrow_8)
    ImageView arrow8;
    @BindView(R.id.guide_rl)
    RelativeLayout guideRl;
    @BindView(R.id.imageView9)
    ImageView imageView9;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.arrow_9)
    ImageView arrow9;
    @BindView(R.id.setting_rl)
    RelativeLayout settingRl;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.customer_service_tv)
    TextView customerServiceTv;
    @BindView(R.id.work_time_tv)
    TextView workTimeTv;
    @BindView(R.id.arrow_3)
    ImageView arrow3;
    @BindView(R.id.wechat_rl)
    RelativeLayout wechatRl;
    private PictureTaker pictureTaker;//图片选择器
    private PictureTakeDialog pictureTakeDialog;//图片选择弹窗

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        ButterKnife.bind(this);
        titleTv.setText(getString(R.string.person_center));
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
            public void onPictureTaked(Bitmap bitmap) {
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
    @OnClick({R.id.title_back_iv, R.id.custom_header_view, R.id.wallet_rl, R.id.history_rl, R.id.ranking_rl, R.id.invitation_rl, R.id.gift_shop_rl, R.id.help_rl, R.id.guide_rl, R.id.setting_rl, R.id.wechat_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.custom_header_view:
                showPicturePckDialog();
                break;
            case R.id.wallet_rl:
                ActivityUtils.startActivity(WalletActivity.class);
                break;
            case R.id.history_rl:
                break;
            case R.id.ranking_rl:
                ActivityUtils.startActivity(RankingListActivity.class);
                break;
            case R.id.invitation_rl:
                break;
            case R.id.gift_shop_rl:
                break;
            case R.id.help_rl:
                break;
            case R.id.guide_rl:
                break;
            case R.id.setting_rl:
                break;
            case R.id.wechat_rl:
                break;
        }
    }
}
