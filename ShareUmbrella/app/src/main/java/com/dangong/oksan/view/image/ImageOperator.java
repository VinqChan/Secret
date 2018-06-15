package com.dangong.oksan.view.image;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.dangong.oksan.util.DimensUtil;

import java.io.File;


/**
 * 图片处理工具
 * 旋转、圆角处理、倒影、裁剪、毛玻璃等
 * Created by zhengjb on 2017/1/5.
 */

public class ImageOperator {
    private static final String TAG = "ImageOperator";

    /**
     * 将图片旋转一定角度
     *
     * @param img    旋转的图片
     * @param degree 旋转的角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }

    /**
     * 获得圆角图片的方法
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 获得带倒影的图片方法
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);

        return bitmapWithReflection;
    }


    /**
     * 设置毛玻璃效果
     * <p>
     * 详细说明
     * <p>
     */
    @SuppressLint("NewApi")
    public static Bitmap fastblur(Bitmap sentBitmap, int radius) {
        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
//        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int temp = 256 * divsum;
        int dv[] = new int[temp];
        for (i = 0; i < temp; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

//        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        return (bitmap);
    }


    /**
     * 把sour文件中的图片旋转rotation，并把结果保存到res指定的文件中
     *
     * @param context   上下文
     * @param sour      指定原始bitmap所在文件
     * @param res       保存结果bitmap文件
     * @param rotation  旋转的角度
     * @param fitScreen true将会对图片进行适当压缩
     * @return 保存结果bitmap的文件
     */
    public static File rorationBitmap(Context context, File sour, File res, float rotation, boolean fitScreen) {
        Bitmap bitmap = ImageIO.loadBitmapFromFile(context, sour, fitScreen);
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return ImageIO.saveBitmap(res, bitmap);
    }

    public static Bitmap rorationBitmap(Bitmap sourBit, float rotation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation);
        return Bitmap.createBitmap(sourBit, 0, 0, sourBit.getWidth(), sourBit.getHeight(), matrix, true);
    }

    /**
     * 把sour文件指定的图片按bounds指定矩形裁剪，结果保存找到res指定的文件中
     *
     * @param context   上下文
     * @param sour      原始bitmap所在的文件
     * @param res       保存结果bitmap的文件
     * @param bounds    裁剪的边界
     * @param fitScreen true将会对图片进行适当的压缩
     * @return 保存结果bitmap的文件
     */
    public static File cropBitmap(Context context, File sour, File res, Rect bounds, boolean fitScreen) {
        Bitmap src = ImageIO.loadBitmapFromFile(context, sour, fitScreen);
        Bitmap resBitmap = cropBitmap(src, bounds);
        return ImageIO.saveBitmap(res, resBitmap);
    }

    public static Bitmap cropBitmap(Bitmap sourBit, Rect bounds) {
        if (sourBit == null) {
            return null;
        }
        int startW = bounds.left;
        int startH = bounds.top;
        int width, height;
        if (startH < 0) {
            startH = 0;
        }
        if (startW < 0) {
            startW = 0;
        }
        if (startH >= sourBit.getHeight() || startW >= sourBit.getWidth()) {
            Log.d(TAG, "======剪切的范围不在允许的范围内======");
            return null;
        }
        width = bounds.right - startW;
        height = bounds.bottom - startH;
        if (width > sourBit.getWidth() || width <= 0) {
            width = sourBit.getWidth() - startW;
        }
        if (height > sourBit.getHeight() || height <= 0) {
            height = sourBit.getHeight() - startH;
        }
        return Bitmap.createBitmap(sourBit, startW, startH, width, height);
    }

    /**
     * 拍照后裁剪
     *
     * @param data   原始图片
     * @param output 裁剪后图片
     */
    public static void startActionCrop(Activity context, Uri data, Uri output, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", output);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 3);// 裁剪框比例
        intent.putExtra("aspectY", 2);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("outputX", DimensUtil.getDisplayWidth(context));// 输出图片大小
        intent.putExtra("outputY", DimensUtil.getDisplayHeight(context) / 3);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 创建View的图像
     *
     * @param view  要创建图像的View
     * @param ratio view的比例 width/height
     * @return 要创建的图像
     */
    public static Bitmap createViewBitmap(View view, double ratio) {
        int i = view.getWidth();
        int j = (int) (i * ratio);
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
        Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
        Canvas localCanvas = new Canvas(localBitmap);
        view.draw(localCanvas);
        return localBitmap;
    }
}
