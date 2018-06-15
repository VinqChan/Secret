package com.dangong.oksan.view.image;

import android.media.ExifInterface;

import java.io.IOException;

/**
 * 图片详细信息处理工具
 * Created by zhengjb on 2017/1/5.
 */

public class ImageDetailHandler {
    /** 读取照片exif信息中的旋转角度
     * @param path 照片路径
     * @return 旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
