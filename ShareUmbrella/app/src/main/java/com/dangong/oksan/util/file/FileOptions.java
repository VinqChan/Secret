package com.dangong.oksan.util.file;

/**
 * 文件参数
 * 用于文件读写
 * Created by zhengjb on 2017/1/6.
 */

public class FileOptions {
    /** 应用内缓存文件 */
    public static final int FILE_TYPE_LOCAL_CACHE = 1;
    /** 外部缓存文件 */
    public static final int FILE_TYPE_EXT_CACHE = 2;

    /** 应用内正常文件 */
    public static final int FILE_TYPE_LOCAL_NORMAL = 3;
    /** 外部正常文件 */
    public static final int FILE_TYPE_EXT_NORMAL = 4;

    /***  应用内临时文件 */
    public static final int FILE_TYPE_LOCAL_TEMP = 5;
    /** 外部临时文件 */
    public static final int FILE_TYPE_EXT_TEMP = 6;

    /** 应用内图片文件 */
    public static final int FILE_TYPE_LOCAL_IMAGE = 7;
    /** 外部图片文件 */
    public static final int FILE_TYPE_EXT_IMAGE = 8;

    /** 网络文件 */
    public static final int FILE_TYPE_NET = 9;

    private int fileType;
    private boolean useReserve = true;
    private String fileName;
    private boolean createIfNoExist = false;

    /**
     * @return 不存在的时候是否创建
     * */
    public boolean isCreateIfNoExist() {
        return createIfNoExist;
    }

    /**
     * @param createIfNoExist 不存在的时候是否创建
     * */
    public void setCreateIfNoExist(boolean createIfNoExist) {
        this.createIfNoExist = createIfNoExist;
    }

    /**

     *  @return  文件类型 {@link #FILE_TYPE_LOCAL_CACHE},{@link #FILE_TYPE_EXT_CACHE},{@link #FILE_TYPE_LOCAL_NORMAL}
     *  ,{@link #FILE_TYPE_EXT_NORMAL},{@link #FILE_TYPE_LOCAL_TEMP},{@link #FILE_TYPE_EXT_TEMP},{@link #FILE_TYPE_LOCAL_IMAGE},{@link #FILE_TYPE_EXT_IMAGE},{@link #FILE_TYPE_NET}
     * */
    public int getFileType() {
        return fileType;
    }

    /**
     *  @param fileType 文件类型{@link #FILE_TYPE_LOCAL_CACHE},{@link #FILE_TYPE_EXT_CACHE},{@link #FILE_TYPE_LOCAL_NORMAL}
     *  ,{@link #FILE_TYPE_EXT_NORMAL},{@link #FILE_TYPE_LOCAL_TEMP},{@link #FILE_TYPE_EXT_TEMP},{@link #FILE_TYPE_LOCAL_IMAGE},{@link #FILE_TYPE_EXT_IMAGE},{@link #FILE_TYPE_NET}
     * */
    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    /**
     * @return 是否使用备用存储（应用内的文件的备用存储是外部文件，外部文件的备用存储是应用内文件）
     * */
    public boolean isUseReserve() {
        return useReserve;
    }

    /**
     * @param useReserve 是否使用备用存储（应用内的文件的备用存储是外部文件，外部文件的备用存储是应用内文件）
     * */
    public void setUseReserve(boolean useReserve) {
        this.useReserve = useReserve;
    }

    /**
     * @return 文件名称
     * */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     * */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
