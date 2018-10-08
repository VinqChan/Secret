package com.dangong.oksan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.constants.Constants;
import com.dangong.oksan.model.ResponseModel;
import com.dangong.oksan.util.OKHttpUICallback;
import com.dangong.oksan.util.OkHttpManger;
import com.dangong.oksan.util.UpLoadUtils;
import com.dangong.oksan.util.listener.ProgressListener;
import com.dangong.oksan.util.listener.impl.UIProgressListener;
import com.dangong.oksan.view.pictureTaker.PictureTakeDialog;
import com.dangong.oksan.view.pictureTaker.PictureTaker;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
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
                upload();
//                HashMap<String, String> map = new HashMap<>();
//                map.put("phone", Constants.loginInfo.getPhone());
//                final File[] files = new File[2];
//                final String[] keys = new String[2];
//                final OkHttpManger.Param[] params = new OkHttpManger.Param[1];
//                params[0] = new OkHttpManger.Param("phone", Constants.loginInfo.getPhone());
//
//                keys[0] = "file";
//                keys[1] = "file";
//                files[0] = new File(path1);
//                files[1] = new File(path2);
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            OkHttpManger.getInstance().uploadAsync("", files, keys, new OKHttpUICallback.ProgressCallback() {
//                                @Override
//                                public void onSuccess(Call call, final Response response, String path) {
//                                    loadingBar.setVisibility(View.GONE);
//                                    ToastUtils.showLong("上传成功！");
//                                    finish();
//                                }
//
//                                @Override
//                                public void onProgress(long byteReadOrWrite, long contentLength, boolean done) {
//                                    Log.e(TAG, "onProgress: " + byteReadOrWrite + " " + contentLength + "  " + done);
//                                }
//
//                                @Override
//                                public void onError(Call call, IOException e) {
//                                    Log.e(TAG, "onError: " + e.getMessage());
//                                    ToastUtils.showLong("上传失败！");
//                                    loadingBar.setVisibility(View.GONE);
//                                }
//                            }, params);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

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
    //多文件上传（带进度）
    private void upload() {
        //这个是非ui线程回调，不可直接操作UI
        final ProgressListener progressListener = new ProgressListener() {
            @Override
            public void onProgress(long bytesWrite, long contentLength, boolean done) {
                Log.i("TAG", "bytesWrite:" + bytesWrite);
                Log.i("TAG", "contentLength" + contentLength);
                Log.i("TAG", (100 * bytesWrite) / contentLength + " % done ");
                Log.i("TAG", "done:" + done);
                Log.i("TAG", "================================");
            }
        };


        //这个是ui线程回调，可直接操作UI
        UIProgressListener uiProgressRequestListener = new UIProgressListener() {
            @Override
            public void onUIProgress(long bytesWrite, long contentLength, boolean done) {
                Log.i("TAG", "bytesWrite:" + bytesWrite);
                Log.i("TAG", "contentLength" + contentLength);
                Log.i("TAG", (100 * bytesWrite) / contentLength + " % done ");
                Log.i("TAG", "done:" + done);
                Log.i("TAG", "================================");
                //ui层回调,设置当前上传的进度值
                int progress = (int) ((100 * bytesWrite) / contentLength);

                Log.e(TAG, "onUIProgress: "+"上传进度值：" + progress + "%");
            }

            //上传开始
            @Override
            public void onUIStart(long bytesWrite, long contentLength, boolean done) {
                super.onUIStart(bytesWrite, contentLength, done);
                Toast.makeText(getApplicationContext(),"开始上传",Toast.LENGTH_SHORT).show();
            }

            //上传结束
            @Override
            public void onUIFinish(long bytesWrite, long contentLength, boolean done) {
                super.onUIFinish(bytesWrite, contentLength, done);
                //uploadProgress.setVisibility(View.GONE); //设置进度条不可见
                loadingBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"上传成功",Toast.LENGTH_SHORT).show();
                finish();

            }
        };

        //Constants.SERVICE_BASE_URL + "/batch/upload"
        //开始Post请求,上传文件
        UpLoadUtils.doPostRequest(Constants.SERVICE_BASE_URL + "/batch/upload", initUploadFile(), uiProgressRequestListener, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.i("TAG", "error------> "+e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RealNameCertifiActivity.this, "上传失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG", "success---->"+response.body().string());
            }
        });

    }
    //初始化上传文件的数据
    private List<String> initUploadFile(){
        List<String> fileNames = new ArrayList<>();
        fileNames.add(path1);
        fileNames.add(path2);

        return fileNames;
    }
}
