package com.dangong.oksan.util;

/**
 * Created by Jian on 2018/9/22.
 */

import android.os.Handler;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by Seeker on 2016/7/27.
 */
public final class ProgressBody {
    /**
     * 包装响应体，用于处理提示上传进度
     * <p>
     * Created by Seeker on 2016/6/29.
     */
    public static final class ProgressRequestBody extends RequestBody { //实际待包装的请求体
        private final RequestBody requestBody; //上传进度回调接口
        private OKHttpUICallback.ProgressCallback mListener; //包装完成的BufferedSink
        private BufferedSink bufferedSink; //传递下载进度到主线程
        private Handler mHandler;

        public ProgressRequestBody(RequestBody requestBody, OKHttpUICallback.ProgressCallback listener, Handler handler) {
            this.requestBody = requestBody;
            this.mListener = listener;
            this.mHandler = handler;
        }

        @Override
        public long contentLength() throws IOException {
            return requestBody.contentLength();
        }

        @Override
        public MediaType contentType() {
            return requestBody.contentType();
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            if (bufferedSink == null) { //开始包装
                bufferedSink = Okio.buffer(sink(sink));
            } //写入
            requestBody.writeTo(bufferedSink);
            bufferedSink.flush();
        }

        /**
         * 写入，回调进度接口
         */

        private Sink sink(Sink sink) {
            return new ForwardingSink(sink) { //当前写入字节数
                long byteWriteed = 0L; //总得字节数
                long contentBytes = 0L;

                @Override
                public void write(Buffer source, long byteCount) throws IOException {
                    super.write(source, byteCount);
                    if (mHandler != null && mListener != null) {
                        if (contentBytes == 0L) {
                            contentBytes = contentLength();
                        }
                        byteWriteed += byteCount;
                        mListener.onProgress(byteWriteed, contentBytes, byteWriteed == contentBytes);
                    }
                }
            };
        }
    }
}
