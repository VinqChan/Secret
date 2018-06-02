package com.vinchan.shareumbrella.view.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;


/**
 * 图片缩放工具
 * Created by zhengjb on 2017/1/5.
 */

public class ImageCompressor {
    /**
     * 对图片sour进行缩放,保持宽高损失质量
     * @param sour 要缩放的图片
     * @param size 目标大小
     * @return 返回大小不超过size的bitmap
     */
    public static Bitmap compress(Bitmap sour, int size){
        //期望的大小size为0或者大于图片的大小不用缩放
        if(size <= 0||sour.getByteCount()/1024 <= size){
            return sour;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        sour.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        while (baos.size() > size){
            quality -= 10;
            sour.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));
    }

    /**
     * 计算缩放图片的宽高
     */
    public static int[] scaleImageSize(int[] img_size, int square_size) {
        if (img_size[0] <= square_size && img_size[1] <= square_size)
            return img_size;
        double ratio = square_size / (double) Math.max(img_size[0], img_size[1]);
        return new int[]{(int) (img_size[0] * ratio), (int) (img_size[1] * ratio)};
    }

    /**
     * 根据指定的宽高缩放bitmap
     * @param bitmap 需要缩放的图片
     * @param w 指定的宽度
     * @param h 指定的高度
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int w, int h) {
        Bitmap newbmp = null;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidht = ((float) w / width);
            float scaleHeight = ((float) h / height);
            matrix.postScale(scaleWidht, scaleHeight);
            newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        }
        return newbmp;
    }

    /**
     * 把sour指定的bitmap缩放scale倍，结果保存到res指定的文件中
     *
     * @param sour  保存原始bitmap的文件
     * @param res   保存结果bitmap的文件
     * @param scale 缩放的倍数
     * @return 保存结果bitmap的文件
     */
//    public static File scaleBitmap(File sour, File res, float scale) {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = (int) scale;
//        Bitmap bitmap = BitmapFactory.decodeFile(sour.getAbsolutePath(), options);
//        return saveBitmap(res, bitmap);
//    }

    /**
     * 把sour指定的bitmap缩放scale倍
     * @param sourBit  保存原始bitmap的文件
     * @param scale 缩放的倍数
     * */
    public static Bitmap scaleBitmap(Bitmap sourBit, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(sourBit, 0, 0, sourBit.getWidth(), sourBit.getHeight(), matrix, true);
    }
}
