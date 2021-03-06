/*
 * 系统: LaiDianYi
 * 文件名: PictureTaker.java
 * 版权: U1CITY Corporation 2015
 * 描述:
 * 创建人: zhengjb
 * 创建时间: 2015-9-9 下午3:13:49
 */
package com.dangong.oksan.view.pictureTaker;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.util.permission.PermissionCenter;
import com.dangong.oksan.util.file.StorageFileManager;
import com.dangong.oksan.util.permission.PermissionCallBack;
import com.dangong.oksan.view.image.ImageCompressor;
import com.dangong.oksan.view.image.ImageDetailHandler;
import com.dangong.oksan.view.image.ImageIO;
import com.dangong.oksan.view.image.ImageOperator;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 图片采集者
 * 获取拍照、相册中的图片，同时可以进行裁剪处理(也可以不裁剪)
 * 
 * @author zhengjb
 */
public class PictureTaker
{
    /** 拍照 */
    public static final int REQUEST_CODE_CAMERA = 9999;
    /** 从相册获取 */
    public static final int REQUEST_CODE_GALLERY = 9998;
    /** 裁剪后的图片 */
    public static final int REQUEST_CODE_CROP = 9997;

    private final String tag = PictureTaker.class.getName();

    private BaseActivity activity;

    private String tempDir = "";

    private Uri origUri;
    private Uri cropUri;
    private Uri scaledUri;

    private boolean isStorageAvailable = true;
    
    private boolean enableCrop = false;

    private OnTakePictureListener onTakePictureListener;
    
    private int maxWidth = 480;

    private int aspectX = 1;
    private int aspectY = 1;
    private int outputX = 480;
    private int outputY = 480;

    private boolean enableScale = true;
    private boolean enableScaleUpIfNeed = true;

    /** @return  压缩处理后的采集的图片的地址 */
    public Uri getScaledUri() {
        return scaledUri;
    }

    /** @return  原始的采集的图片的地址 */
    public Uri getOrigUri() {
        return origUri;
    }

    /**
     * @return 裁剪后的采集的图片的地址
     * */
    public Uri getCropUri() {
        return cropUri;
    }

    /**
     * 图片采集者
     * 获取拍照、相册中的图片，同时可以进行裁剪等处理
     * 
     * @param tempDir 临时的文件存放地址
     * @param activity
     * */
    public PictureTaker(BaseActivity activity, String tempDir)
    {
        if (activity == null || tempDir == null)
        {
            throw new NullPointerException("PictureTaker 的参数不能为空");
        }

        this.activity = activity;
        this.tempDir = tempDir;
        initTempDir();

    }

    public int getAspectX()
    {
        return aspectX;
    }

    /** 设置裁剪框宽占比 */
    public void setAspectX(int aspectX)
    {
        this.aspectX = aspectX;
    }

    /** 设置裁剪框长占比 */
    public int getAspectY()
    {
        return aspectY;
    }

    public void setAspectY(int aspectY)
    {
        this.aspectY = aspectY;
    }


    public int getOutputX()
    {
        return outputX;
    }

    /** 设置裁剪后图片宽度 */
    public void setOutputX(int outputX)
    {
        this.outputX = outputX;
    }

    public int getOutputY()
    {
        return outputY;
    }

    /** 设置裁剪后图片长度 */
    public void setOutputY(int outputY)
    {
        this.outputY = outputY;
    }

    public boolean isEnableCrop()
    {
        return enableCrop;
    }

    public void setEnableCrop(boolean enableCrop)
    {
        this.enableCrop = enableCrop;
    }

    /**
     * 是否有可用的图片存储空间，有为true，否则为false
     * */
    public boolean isStorageAvailable()
    {
        return isStorageAvailable;
    }

    /**
     * 初始化图片存放地址
     * 
     * @author zhengjb
     */
    private void initTempDir()
    {
        isStorageAvailable = true;

        File photoDir;

        String url = StorageFileManager.getAvailableStorage(activity);
        if (url != null)
        {
            photoDir = new File(url + File.separator + tempDir);
            if (!photoDir.exists())
            {
                photoDir.mkdirs();
            }

            LogUtils.d(tag, "PictureTaker, url:" + url);
        }
        else
        {
            LogUtils.w(tag, "PictureTaker, 外部存储不可用");
            isStorageAvailable = false;
            return;
        }
        
        String cropFileName = "crop_" + new Date().getTime() + ".jpg";
        File cropFile = new File(photoDir, cropFileName);
        enableFile(cropFile);
        cropUri = Uri.fromFile(cropFile);

        String origFileName = "original_" + new Date().getTime() + ".jpg";
        File origFile = new File(photoDir, origFileName);
        enableFile(origFile);
        origUri = Uri.fromFile(origFile);

        String scaledFileName = "scaled_" + new Date().getTime() + ".jpg";
        File scaledFile = new File(photoDir, scaledFileName);
        enableFile(scaledFile);
        scaledUri = Uri.fromFile(scaledFile);
    }

    private void enableFile(File file){
        try {
            file.createNewFile();
            file.setWritable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaxWidth()
    {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth)
    {
        this.maxWidth = maxWidth;
    }

    public OnTakePictureListener getOnTakePictureListener()
    {
        return onTakePictureListener;
    }

    public void setOnTakePictureListener(OnTakePictureListener onTakePictureListener)
    {
        this.onTakePictureListener = onTakePictureListener;
    }

    /** 超过最大宽度时进行收缩 */
    private Bitmap getScaleImage(String path){
        int degree = ImageDetailHandler.readPictureDegree(path);

        LogUtils.d(tag, "PictureTaker, scaleImagePath:" + path + " -- degree:" + degree);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        File file = new File(path);
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        //图片压缩
        LogUtils.d(tag, "outWidth:" + options.outWidth + " -- outHeight:" + options.outHeight);
        
        if(options.outWidth > maxWidth){
            options.inSampleSize = options.outWidth / maxWidth;
            int height = options.outHeight * maxWidth / options.outWidth;
            options.outHeight = height;
            options.outWidth = maxWidth;

            LogUtils.d(tag, "outWidth:" + options.outWidth + " -- outHeight:" + options.outHeight);
        }
        
        options.inJustDecodeBounds = false;

        options.inPurgeable = true;
        options.inInputShareable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        //如果图片有旋转要更正过来
        if(degree > 0){
            bitmap = ImageOperator.rotateBitmap(bitmap, degree);
        }

        String scaledPath = scaledUri.getPath().replace("file://", "");
        File scaledFile = new File(scaledPath);
        ImageIO.writeImage2File(bitmap, scaledFile);

        return bitmap;
    }

    /**
     * 拍照
     * */
    public void takePhoto()
    {
        PermissionCenter.getInstance().checkPermission(activity,permissionCallback,Manifest.permission.CAMERA);
    }

    PermissionCallBack permissionCallback = new PermissionCallBack() {
        @Override
        public void onSuccess(String permission) {
            initTempDir();
            dealTakePhoto();
        }

        @Override
        public void onfail(String permission) {
            if (onTakePictureListener != null) {
                onTakePictureListener.onPictureTaked(null,"");
            }
        }
    };

    private void dealTakePhoto(){
        if(!isStorageAvailable){
            ToastUtils.showShort( "外部存储不可用,无法拍照");
            return;
        }

        if(origUri != null){
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            camera.putExtra(MediaStore.EXTRA_OUTPUT, origUri);
            activity.startActivityForResult(camera, REQUEST_CODE_CAMERA, false);
        }
    }
    
    /**
     * 从相册获取图片
     * */
    public void takeGallery(){
        PermissionCenter.getInstance().checkPermission(activity, new PermissionCallBack() {
            @Override
            public void onSuccess(String permission) {
                initTempDir();
                dealTakeGallery();
            }

            @Override
            public void onfail(String permission) {
                if(onTakePictureListener != null){
                    onTakePictureListener.onPictureTaked(null,"");
                }
            }
        },Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    private void dealTakeGallery(){
        if(!isStorageAvailable){
            ToastUtils.showShort("外部存储不可用,无法选择图片");
            return;
        }

        Intent picture = new Intent();
        picture.setType("image/*");
        picture.setAction(Intent.ACTION_PICK);
        activity.startActivityForResult(picture, REQUEST_CODE_GALLERY, false);
    }
    
    /**
     * 获取所需的图片
     * */
    private Bitmap onBitmapResult(Intent data){
        Bitmap bitmap = null;
        if (data != null)
        {
            Bundle bundle = data.getExtras();
//            LogUtils.d(tag, "bundle:" + bundle);
            // 获取相机返回的数据，并转换为图片格式
            if (bundle != null)
            {
                bitmap = (Bitmap) bundle.get("data");

                if (bitmap == null && cropUri != null)
                {
                    bitmap =  getScaleImage(cropUri.getPath().replace("file://", ""));
                }
            }
        }
        
        return bitmap;
    }

    public void onActivityResult(Intent data, int requestCode){
        LogUtils.d(tag, "requestCode:" + requestCode);

        if(requestCode == REQUEST_CODE_CAMERA){
            if(enableCrop){
                getCrop(origUri);
            }else{
                if(onTakePictureListener != null){
//                    LogUtils.d(tag, "" + origUri.getPath());
                    onTakePictureListener.onPictureTaked(getScaleImage(origUri.getPath().replace("file://", "")),scaledUri.getPath());
                }
            }
        }else if(requestCode == REQUEST_CODE_GALLERY){
            if(data != null){
                if(enableCrop){
                    getCrop(data.getData());
                }else{
//                    LogUtils.d(tag, "" + data.getData().getPath());
                    if(onTakePictureListener != null){
                        String path;

                        //有的手机返回的是content://，有的手机返回的是file://
                     if(data.getData() != null && data.getData().toString().contains("file://")){
                            path = data.getData().toString();
                        }else{
                            path = ImageIO.getImagePathFromUri(activity, data.getData());
                        }

                        if(!StringUtils.isEmpty(path)){
                            onTakePictureListener.onPictureTaked(getScaleImage(path.replace("file://", "")),path);
                        }else{
                            onTakePictureListener.onPictureTaked(null,"");
                        }
                    }
                }
            }else{
                onTakePictureListener.onPictureTaked(null,"");
            }
        }else if(requestCode == REQUEST_CODE_CROP){
            if(onTakePictureListener != null){
                onTakePictureListener.onPictureTaked(onBitmapResult(data),cropUri.getPath());
            }
        }
    }
    
    
    /** 裁剪 */
    private void getCrop(Uri uri) {
        if(uri == null){
            return;
        }

//        LogUtils.d(tag, "uri:" + uri);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("output", cropUri);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);// 裁剪框比例
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("scale", enableScale);
        intent.putExtra("scaleUpIfNeeded", enableScaleUpIfNeed);
        intent.putExtra("outputX", outputX);// 输出图片大小
        intent.putExtra("outputY", outputY);
        activity.startActivityForResult(intent, REQUEST_CODE_CROP, false);
    }
    
    public interface OnTakePictureListener{
        void onPictureTaked(Bitmap bitmap,String url);
    }
    
}
