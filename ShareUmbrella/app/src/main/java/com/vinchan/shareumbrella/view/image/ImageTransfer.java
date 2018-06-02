package com.vinchan.shareumbrella.view.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * 图片转换工具
 * Created by zhengjb on 2017/1/5.
 */

public class ImageTransfer {
    /**
     * bitmap转为字符串
     *
     * @param sour    将要转成String的bitmap
     * @param quality bitmap质量
     * @return 生成的字符串
     */
    public static String bitmap2String(Bitmap sour, int quality) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        sour.compress(Bitmap.CompressFormat.JPEG, quality, out);
        try {
            out.flush();
            out.close();
            return Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bitmap2String(Bitmap sour) {
        return bitmap2String(sour, 100);
    }

    /**
     * 将Drawable转化为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * 将bitmap转化为drawable
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        Drawable drawable;
        drawable = new BitmapDrawable(bitmap);
        return drawable;
    }

    /**
     * Bytes转化为Bimap
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
}
