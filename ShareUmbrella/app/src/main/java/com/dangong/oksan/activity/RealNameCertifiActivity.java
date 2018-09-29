package com.dangong.oksan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.util.OKHttpUICallback;
import com.dangong.oksan.util.OkHttpManger;
import com.dangong.oksan.view.pictureTaker.PictureTakeDialog;
import com.dangong.oksan.view.pictureTaker.PictureTaker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RealNameCertifiActivity extends BaseActivity {

    public static final int TYPE_REAL_NAME = 1;//实名认证
    public static final int TYPE_MODIFY = 2;//修改证件
    public static final String TYPE_KEY = "real_name_type";
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
    @BindView(R.id.loading_progress_bar)
    ProgressBar loadingBar;
    private PictureTaker pictureTaker;//图片选择器
    private PictureTakeDialog pictureTakeDialog;//图片选择弹窗
    private boolean isUploadHandle = false;//是否上传手持
    private int type = 0;
    private String path1 = "";
    private String path2 = "";


    @Override
    public String setTitle() {
        return getString(R.string.realnamecerfi);
    }

    @Override
    public int getLayoutId() {
//        EventBus.getDefault().register(this);
        return R.layout.activity_real_name;
    }

    @Override
    public void init() {
        super.init();
        initPictureTaker();
        type = getIntent().getIntExtra(TYPE_KEY, TYPE_REAL_NAME);
        if (type == TYPE_MODIFY) {
            setTitle(getString(R.string.modify_cerfi));
        } else {
            setTitle(getString(R.string.realnamecerfi));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pictureTaker.onActivityResult(data, requestCode);
    }

    public void initPictureTaker() {

        pictureTaker = new PictureTaker(this, "/oksan");
        pictureTaker.setAspectX(7);
        pictureTaker.setAspectY(5);
        pictureTaker.setOutputX(720);
        pictureTaker.setOutputX(640);
        pictureTaker.setEnableCrop(true);
        pictureTaker.setOnTakePictureListener(new PictureTaker.OnTakePictureListener() {
            @Override
            public void onPictureTaked(Bitmap bitmap, String url) {
                Log.e(TAG, "onPictureTaked: " + url);
                if (bitmap != null) {
                    if (isUploadHandle) {
                        path2 = url;
                        uploadHandleCard.setImageBitmap(bitmap);
                    } else {
                        path1 = url;
                        uploadCard.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  EventBus.getDefault().unregister(this);
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
                //ActivityUtils.startActivity(MainNoRealNameActivity.class);

                if (path1.equals("")) {
                    ToastUtils.showShort("请上传导游相关证据");
                    return;
                }
                if (path2.equals("")) {
                    ToastUtils.showShort("请上传手持导游相关证据");
                    return;
                }
                loadingBar.setVisibility(View.VISIBLE);

                HashMap<String, String> map = new HashMap<>();
                map.put("phone", Constants.loginInfo.getPhone());
                HashMap<String, String> hearder = new HashMap<>();
                hearder.put("Content-Type", "application/octet-stream");
                hearder.put("Authorization", "Bearer " + Constants.loginInfo.getToken());
                File[] files = new File[2];
                String[] keys = new String[2];
                OkHttpManger.Param[] params = new OkHttpManger.Param[1];
                params[0] = new OkHttpManger.Param("phone", Constants.loginInfo.getPhone());

                keys[0] = "file";
                keys[1] = "file";
                files[0] = new File(path1);
                files[1] = new File(path2);
                try {
                    OkHttpManger.getInstance().uploadAsync(Constants.SERVICE_BASE_URL + "/batch/upload", files, keys, new OKHttpUICallback.ProgressCallback() {
                        @Override
                        public void onSuccess(Call call, Response response, String path) {
                            Log.e(TAG, "onSuccess: " + response);
                            loadingBar.setVisibility(View.GONE);
                            ToastUtils.showLong("上传成功！");
                            finish();
                        }

                        @Override
                        public void onProgress(long byteReadOrWrite, long contentLength, boolean done) {
                            Log.e(TAG, "onProgress: " + byteReadOrWrite + " " + contentLength + "  " + done);
                        }

                        @Override
                        public void onError(Call call, IOException e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            ToastUtils.showLong("上传失败！");
                            loadingBar.setVisibility(View.GONE);
                        }
                    }, params);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
