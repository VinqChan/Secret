package com.dangong.oksan.util.file;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 用于管理文件存储路径
 * Created by wuzr on 2016/9/8.
 */
public class StorageFileManager {
    private static final String TAG = "StorageFileManager";
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String BASE_FILE = "MonCity";


    /**
     * 根据文件参数查找文件，不存在则创建
     * */
    public static File findFile(Context context, FileOptions fileOptions){
        if(fileOptions == null){
            return null;
        }

        File rootFile = getRootFileDir(context, fileOptions);
        if(rootFile == null){
            return null;
        }

        return findFile(rootFile, fileOptions.getFileName());
    }

    /**
     * 根据文件参数查找文件，不存在返回空
     * */
    public static File findFileIfExist(Context context, FileOptions fileOptions){
        if(fileOptions == null){
            return null;
        }

        File rootFile = getRootFileDir(context, fileOptions);
        if(rootFile == null){
            return null;
        }

        return findFileIfExist(rootFile, fileOptions.getFileName());
    }

    /**
     * 创建文件
     * */
    public static void makeFile(Context context, FileOptions fileOptions){
        File rootFile = getRootFileDir(context, fileOptions);
        if(rootFile != null && !TextUtils.isEmpty(fileOptions.getFileName())){
            File dir = new File(rootFile, fileOptions.getFileName());
            if(!dir.exists()){
                dir.mkdirs();
            }
        }
    }

    /**
     * 根据文件参数获取文件根目录
     * @param context
     * @param fileOptions 文件参数
     * */
    public static File getRootFileDir(Context context, FileOptions fileOptions) {
        File rootFile = null;

        int fileType = fileOptions.getFileType();
        if(fileType == FileOptions.FILE_TYPE_EXT_CACHE){
            rootFile = getExternalCacheDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getLocalCacheDir(context);
            }
        }else if(fileType == FileOptions.FILE_TYPE_LOCAL_CACHE){
            rootFile = getLocalCacheDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getExternalCacheDir(context);
            }
        }else if(fileType == FileOptions.FILE_TYPE_EXT_NORMAL){
            rootFile = getExternalNormalDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getLocalNormalDir(context);
            }
        }else if(fileType == FileOptions.FILE_TYPE_LOCAL_NORMAL){
            rootFile = getLocalNormalDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getExternalNormalDir(context);
            }
        }else if(fileType == FileOptions.FILE_TYPE_EXT_TEMP){
            rootFile = getExternalTempDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getLocalTempDir(context);
            }
        }else if(fileType == FileOptions.FILE_TYPE_LOCAL_TEMP){
            rootFile = getLocalTempDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getExternalTempDir(context);
            }
        }else if(fileType == FileOptions.FILE_TYPE_EXT_IMAGE){
            rootFile = getExternalImageDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getLocalImageDir(context);
            }
        }else if(fileType == FileOptions.FILE_TYPE_LOCAL_IMAGE){
            rootFile = getLocalImageDir(context);
            if(rootFile == null && fileOptions.isUseReserve()){
                rootFile = getExternalImageDir(context);
            }
        }

        if(rootFile != null){
            rootFile.mkdirs();
        }

            return rootFile;
    }


    /**
     * 在指定的根目录中(ownerDir,externalDir)中查找fileDesc描述的文件
     *
     * @param dir 根目录
     * @param fileDesc       描述要查找的文件
     * @return 找到的文件
     */
    public static File findFileIfExist(File dir, String fileDesc) {
        if (dir == null || fileDesc == null || fileDesc.equals("")) {
            return null;
        }

        File file;

        if ((dir != null && fileDesc.startsWith(dir.getAbsolutePath()))) {
            file = new File(fileDesc);
            if (file.exists()) {
                return file;
            } else {
                return null;
            }
        }

        //递归查找
        file = findFileIfExistInternal(dir, fileDesc);
        return file;
    }

    /**
     * 在根目录中获得名称为fileName文件，如果文件存在则返回此文件，不存在则先创建名称为fileName的文件在将其返回
     *
     * @param dir 根目录
     * @param fileName       将要获得的文件的名称
     * @return 获得的文件
     */
    public static File findFile(File dir, String fileName) {
        if(dir == null){
            return null;
        }

        if (fileName == null || fileName.equals("")) {
            fileName = generateFileName();
        }

        File file = new File(dir, fileName);
        if (file.exists()) {
            return file;
        }
        try {
            if (!file.createNewFile()) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    /**
     * 获取应用缓存目录
     *
     * @param context application context
     * @return 缓存路径
     */
    public static File getLocalCacheDir(Context context) {
        return context.getCacheDir();
    }

    public static File getExternalCacheDir(Context context) {
        if (!isExternalStorageAvailable(context)) {
            return null;
        }
        File appCacheDir = new File(getExternalNormalDir(context), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                Log.w(TAG, "无法创建外部存储缓存目录");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "无法在" + appCacheDir.getAbsolutePath() + "中创建文件");
            }
        }
        return appCacheDir;
    }

    /**
     * 取得应用的内部存储根目录
     */
    public static File getLocalNormalDir(Context context) {
        return context.getFilesDir().getParentFile();
    }

    /**
     * 取得应用在外部存储器的根目录
     */
    public static File getExternalNormalDir(Context context) {
        File rootDir = null;
        if (isExternalStorageAvailable(context)) {
            rootDir = new File(Environment.getExternalStorageDirectory(), BASE_FILE);
            rootDir = new File(rootDir, context.getPackageName());
        }
        return rootDir;
    }

    /**
     * 获取外部存储地址
     * */
    public static String getAvailableStorage(Context context){
        LogUtils.d("storage", "state:" + Environment.getExternalStorageState());
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File sdCardFile = new File(Environment
                    .getExternalStorageDirectory().getAbsolutePath());
            return sdCardFile.getAbsolutePath() + File.separator + context.getPackageName();
        }else{
            String path = null;

            File sdCardFile = null;

            path = getPath("/storage/emulated");
            if(path == null){
                path = getPath("/mnt/sdcard");
            }

            if(path == null){
                path = getPath("/sdcard");
            }

            if (path != null) {
                sdCardFile = new File(path);
                return sdCardFile.getAbsolutePath() + File.separator + BASE_FILE + File.separator + context.getPackageName();
            }

            return null;
        }

    }

    public static String getPath(String directory){
        String path = null;
        String[] devMountList = new File(directory).list();

        if (devMountList != null) {
            for (String devMount : devMountList) {
                File file = new File(directory + "/" + devMount);

                LogUtils.d("storage", "address:" + devMount);

                if (file.isDirectory() && file.canWrite()) {
                    path = file.getAbsolutePath();

                    String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss")
                            .format(new Date());
                    File testWritable = new File(path, "test_" + timeStamp);

                    if (testWritable.mkdirs()) {
                        testWritable.delete();
                    } else {
                        path = null;
                    }
                }
            }
        }

        return path;
    }

    /**
     * 获得内部临时文件目录
     */
    public static File getLocalTempDir(Context context) {
        File rootDir = getLocalNormalDir(context);
        if (rootDir == null) {
            return null;
        }
        if (!rootDir.exists()) {
            try {
                if (!rootDir.mkdirs()) {
                    Log.w(TAG, "创建文件" + rootDir.getAbsolutePath() + "失败");
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        File tempDir = new File(rootDir, "temp");
        if (!tempDir.exists()) {
            if (!tempDir.mkdirs()) {
                return null;
            }
        }
        return tempDir;
    }

    /**
     * 获取外部存储上的临时文件目录
     */
    public static File getExternalTempDir(Context context) {
        File rootDir = getExternalNormalDir(context);
        if (rootDir == null) {
            return null;
        }
        if (!rootDir.exists()) {
            if (!rootDir.mkdirs()) {
                return null;
            }
        }
        File tempDir = new File(rootDir, "temp");
        if (!tempDir.exists()) {
            if (!tempDir.mkdirs()) {
                return null;
            }
        }
        return tempDir;
    }

    /**
     * 获取外部存储图片文件目录
     */
    public static File getExternalImageDir(Context context) {
        File rootDir = getExternalNormalDir(context);
        if (rootDir == null) {
            return null;
        }
        if (!rootDir.exists()) {
            if (!rootDir.mkdirs()) {
                return null;
            }
        }
        File imageDir = new File(rootDir, "image");
        if (!imageDir.exists()) {
            if (!imageDir.mkdirs()) {
                return null;
            }
        }
        return imageDir;
    }

    /**
     * 获取内部存储图片文件目录
     */
    public static File getLocalImageDir(Context context) {
        File rootDir = getLocalNormalDir(context);
        if (rootDir == null) {
            return null;
        }
        if (!rootDir.exists()) {
            if (!rootDir.mkdirs()) {
                return null;
            }
        }
        File imageDir = new File(rootDir, "image");
        if (!imageDir.exists()) {
            if (!imageDir.mkdirs()) {
                return null;
            }
        }
        return imageDir;
    }

    /**
     * 获取文件外部存储目录
     */
    public static File getExternalFileDir(Context context) {
        File rootDir = getExternalNormalDir(context);
        if (rootDir == null) {
            return null;
        }
        if (!rootDir.exists()) {
            if (!rootDir.mkdirs()) {
                return null;
            }
        }
        File fileDir = new File(rootDir, "file");
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return null;
            }
        }
        return fileDir;
    }

    /**
     * 获取文件内部目录
     */
    public static File getLocalFileDir(Context context) {
        File rootDir = getLocalNormalDir(context);
        if (rootDir == null) {
            return null;
        }
        File fileDir = new File(rootDir, "file");
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return null;
            }
        }
        return fileDir;
    }

    public static boolean isExternalStorageAvailable(Context context) {
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (Exception e) {
            externalStorageState = "";
        }

        return externalStorageState.equals(Environment.MEDIA_MOUNTED) && hasExternalStoragePermission(context);
    }

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    private static File findFileIfExistInternal(File root, String fileDesc) {
        if (root.getAbsolutePath().endsWith(fileDesc)) {
            return root;
        }
        if (!root.isDirectory()) {
            return null;
        }
        File res = null;
        for (File f : root.listFiles()) {
            res = findFileIfExistInternal(f, fileDesc);
            if (res != null) {
                return res;
            }
        }
        return res;
    }

    /**
     * 生成文件名称，当前时间的毫秒数
     *
     * @return 文件名称
     */
    private static String generateFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:ss", Locale.getDefault());
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * 根据目录检查文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean fileIsExists(String path)
    {
        try
        {
            File f = new File(path);
            if (!f.exists())
            {
                return false;
            }
        }
        catch (Exception e)
        {

            return false;
        }
        return true;
    }
}
