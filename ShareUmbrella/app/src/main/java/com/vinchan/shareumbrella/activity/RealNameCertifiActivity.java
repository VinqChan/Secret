package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;
import com.vinchan.shareumbrella.view.pictureTaker.PictureTakeDialog;
import com.vinchan.shareumbrella.view.pictureTaker.PictureTaker;

import butterknife.BindView;
import butterknife.OnClick;

public class RealNameCertifiActivity extends BaseActivity {


    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.upload_card)
    ImageView uploadCard;
    @BindView(R.id.upload_handle_card)
    ImageView uploadHandleCard;
    @BindView(R.id.submit_btn)
    Button submitBtn;
    private PictureTaker pictureTaker;//图片选择器
    private PictureTakeDialog pictureTakeDialog;//图片选择弹窗
    private boolean isUploadHandle = false;//是否上传手持


    @Override
    public String setTitle() {
        return getString(R.string.main_title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_real_name;
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
        pictureTaker.setEnableCrop(false);
        pictureTaker.setOnTakePictureListener(new PictureTaker.OnTakePictureListener() {
            @Override
            public void onPictureTaked(Bitmap bitmap) {
                if (bitmap != null) {
                    if(isUploadHandle){
                        uploadHandleCard.setImageBitmap(bitmap);
                    }else {
                        uploadCard.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.title_back_iv, R.id.upload_card, R.id.upload_handle_card, R.id.submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.upload_card:
                isUploadHandle = false;
                showPicturePckDialog();
                break;
            case R.id.upload_handle_card:
                isUploadHandle = true;
                showPicturePckDialog();
                break;
            case R.id.submit_btn:
                ActivityUtils.startActivity(MainNoRealNameActivity.class);
                break;
        }
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
}
