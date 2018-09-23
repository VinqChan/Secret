package com.dangong.oksan.util;

/**
 * Created by Jian on 2018/9/22.
 */

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * <p>
 * 回调主线程的接口
 */
public class OKHttpUICallback { /**
 * 异步回调接口
 */
    /**
     * 带有进度的上传、下载回调接口
     */
    public interface ProgressCallback {
        void onSuccess(Call call, Response response, String path);

        void onProgress(long byteReadOrWrite, long contentLength, boolean done);

        void onError(Call call, IOException e);
    }
}



