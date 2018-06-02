package com.vinchan.shareumbrella.view.image;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;


import com.blankj.utilcode.util.StringUtils;
import com.vinchan.shareumbrella.util.file.FileOptions;
import com.vinchan.shareumbrella.util.file.StorageFileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * 图片存取工具
 * Created by zhengjb on 2017/1/13.
 */

public class ImageIO {

    private static final String TAG = "ImageIO";

    private static final int NO_LIMIT_W = -1;
    private static final int NO_LIMIT_H = -1;

    /**
     * 获取SD卡中最新图片路径
     */
    public static String getLatestImage(Context context) {
        String latestImage = null;
        String[] items = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, items, null, null, MediaStore.Images.Media._ID + " desc");

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                latestImage = cursor.getString(1);
                break;
            }
            cursor.close();
        }

        return latestImage;
    }

    /**
     * 从文件中获取图片，调用者可以指定一个期望的宽高以节省所需内存
     *
     * @param file         目标文件
     * @return bitmap
     */
    public static Bitmap readImageFromFile(File file, BitmapFactory.Options options, int desireWidth, int desireHeight) {
        if (options == null) {
            options = new BitmapFactory.Options();
        }

        options.inJustDecodeBounds = true;
        int acWidth, acHeight;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        acWidth = options.outWidth;
        acHeight = options.outHeight;
        boolean needScale = true;
        boolean fitWidth = false;

        //期望的高度，<=0时不做限制
        if (desireHeight <= 0) {
            fitWidth = true;
        }

        //期望的宽度,<=0时不做限制
        if (desireWidth <= 0) {
            fitWidth = false;
        }
        if (desireHeight <= 0 && desireWidth <= 0) {
            needScale = false;
        }
        if (desireHeight > 0 && desireWidth > 0) {
            fitWidth = desireWidth / desireHeight < 1;
        }
        if (needScale) {
            if (fitWidth) {
                if (acWidth <= desireWidth) {
                    needScale = false;
                }
            } else {
                if (acHeight <= desireHeight) {
                    needScale = false;
                }
            }
        }
        if (needScale) {
            int scale = 1;
            if (fitWidth) {
                while (acWidth > desireWidth) {
                    scale *= 2;
                    acWidth = acWidth / scale;
                }
            } else {
                while (acHeight > desireHeight) {
                    scale *= 2;
                    acHeight = acHeight / scale;
                }
            }
            options.inSampleSize = scale;
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    /**
     * 把图片bitmap写到file文件中
     *
     * @param bitmap 将要写入的文件
     * @param file   目标文件
     * @return true操作成功，false操作失败
     */
    public static boolean writeImage2File(Bitmap bitmap, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 保存图图片
     *
     * @param context  上下文
     * @param sour     要保存的图片
     * @param fileName 保存文件的名称
     * @throws IOException 创建文件失败时抛出“创建文件失败异常”
     */
    public static File saveImage(Context context, Bitmap sour, String fileName) throws IOException {
        File f = obtainImageFile(context, fileName);
        if (f == null) {
            throw new IOException("创建文件失败");
        }
        writeImage2File(sour, f);
        return f;
    }

    /**
     * 获得保存图片的文件
     *
     * @param context  上下文
     * @param fileName 文件名称
     * @return 返回获得的文件f
     */
    public static File obtainImageFile(Context context, String fileName) {
        FileOptions op = new FileOptions();
        op.setFileType(FileOptions.FILE_TYPE_EXT_IMAGE);
        op.setFileName(fileName);
        return StorageFileManager.findFile(context, op);
    }

    /**
     * 获得默认文件名的图片文件
     */
    public static File obtainImageFile(Context context) {
        return obtainImageFile(context, generateDefaultFileName());
    }

    /**
     * 生成默认的文件名称
     */
    public static String generateDefaultFileName() {
        Calendar c = Calendar.getInstance();
        StringBuffer fname;
        fname = new StringBuffer();
        fname.append(c.get(Calendar.YEAR)).
                append(c.get(Calendar.MONTH) + 1).
                append(c.get(Calendar.DAY_OF_MONTH)).
                append(c.get(Calendar.HOUR_OF_DAY)).
                append(c.get(Calendar.MINUTE)).
                append(c.get(Calendar.SECOND));
        return fname.toString();
    }

    /**
     * 根据uri获取图片的绝对路径
     *
     * @param context 上下文
     * @param uri     目标uri
     * @return 图片的绝对路径
     */
    public static String getImagePathFromUri(Context context, Uri uri) {
        String imagePath = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, proj, // Which columns to
                    // return
                    null, // WHERE clause; which rows to return (all rows)
                    null, // WHERE clause selection arguments (none)
                    null); // Order-by clause (ascending by name)

            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                    imagePath = cursor.getString(column_index);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return imagePath;
    }

    public static Bitmap loadImgThumbnail(Context context, String imgName) {
        Bitmap bitmap = null;
        Cursor cursor = null;
        String[] proj = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME};
        try {
            cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, MediaStore.Images.Media.DISPLAY_NAME + "='" + imgName + "'", null, null);
            if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1;
            }
        } finally {
            cursor.close();
        }
        return bitmap;
    }

    /**
     * 从sour指定的文件中加载图片
     *
     * @param context   上下文
     * @param sour      目标文件
     * @param fitScreen true则图片宽高将适应屏幕宽高，在加载大图时可以节省内存
     * @return 从sour加载到的图片
     */
    public static Bitmap loadBitmapFromFile(Context context, File sour, boolean fitScreen) {
        int dirWidth, dirHeight;
        if (fitScreen) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            dirWidth = dm.widthPixels;
            dirHeight = dm.heightPixels;
        } else {
            dirWidth = NO_LIMIT_W;
            dirHeight = NO_LIMIT_H;
        }

        return readImageFromFile(sour, null, dirWidth, dirHeight);
    }

    /**
     * 把bitmap保存到file指定的文件中
     *
     * @param file   保存bitmap的文件
     * @param bitmap 将要保存的图片
     * @return 保存bitmap的文件
     */
    public static File saveBitmap(File file, Bitmap bitmap) {
        if (file == null) {
            Log.w(TAG, "请传入有效的文件");
            return null;
        }
        writeImage2File(bitmap, file);
        return file;
    }

    public static Bitmap readBitmap(File file) {
        return readImageFromFile(file, null, -1, -1);
    }

    /**
     * 保存图片至图片库
     */
    public static void saveImageToGallery(Context context, Bitmap bmp, String filePackageName, String fileName) {
        //当fileName为null时则自动生成文件名
        if (StringUtils.isEmpty(fileName)) {
            fileName = generateDefaultFileName() + ".jpg";
        }
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), filePackageName);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //String fileName = "wxcode.jpg";
        File file = new File(appDir, fileName);
        try {
            saveBitmap(file, bmp);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            if (file != null) {
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
            }
            context.sendBroadcast(intent);//这个广播的目的就是更新图库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveImageToGallery(Context context, Bitmap sour) {
        try {
            File f = saveImage(context, sour, generateDefaultFileName());
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            if (f != null) {
                Uri uri = Uri.fromFile(f);
                intent.setData(uri);
            }
            context.sendBroadcast(intent);//这个广播的目的就是更新图库
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
