package com.dangong.oksan.util;

/**
 * Created by Vinchan on 2018/9/22.
 */

import android.os.Handler;
import android.os.Looper;

import com.dangong.oksan.constants.Constants;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp 工具类，
 * get的同步异步请求
 * post的json字符串同步异步上传
 * post的键值对同步异步上传
 * post文件异步上传，回调结果以及进度
 * 异步下载文件，回调结果以及进度
 * <p>
 * Created by Seeker on 2016/6/24.
 */
public final class OkHttpManger {
    private static final String TAG = "OkHttpManger";
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    private Handler okHttpHandler;
    private OkHttpClient mOkHttpClient;

    private OkHttpManger() {
        this.mOkHttpClient = OkHttpUtils.getInstance().getOkHttpClient();

        this.okHttpHandler = new Handler(Looper.getMainLooper());
    }

    public static final OkHttpManger getInstance() {
        return SingleFactory.manger;
    }

    private static final class SingleFactory {
        private static final OkHttpManger manger = new OkHttpManger();
    } /////////////////////////同步异步上传头像//////////////////////////////

    interface MyCallback {
        void onSuccess(String result);

        void onFailture();
    }

    public Response postSyncJson(String url, String json) throws IOException {
        final RequestBody requestBody = RequestBody.create(JSON_TYPE, json);
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        return mOkHttpClient.newCall(request).execute();
    }

    public void postAsyncJsonn(String url, String json, MyCallback mCallback) throws IOException {
        final RequestBody requestBody = RequestBody.create(JSON_TYPE, json);
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        deliveryResult(mOkHttpClient.newCall(request), mCallback);
    }

    private void deliveryResult(final Call call, final MyCallback mCallback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                okHttpHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCallback != null) {
                            mCallback.onFailture();
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                okHttpHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCallback != null) {
                            mCallback.onSuccess(responseStr);
                        }
                    }
                });
            }
        });
    } ////////////////////////同步异步上传头像//////////////////////////////

    /**
     * 同步基于post的文件上传
     *
     * @param url     地址
     * @param file    提交的文件
     * @param fileKey 提交的文件key
     * @return Response
     */

    public Response uploadSync(String url, File file, String fileKey) throws IOException {
        return uploadSync(url, new File[]{file}, new String[]{fileKey}, new Param[0]);
    }

    /**
     * 同步基于post的文件上传
     *
     * @param url      地址
     * @param files    提交的文件数组
     * @param fileKeys 提交的文件数组key
     * @param params   提交的键值对
     * @return Response
     */
    public Response uploadSync(String url, File[] files, String[] fileKeys, Param[] params) throws IOException {
        final RequestBody requestBody = buildMultipartFormRequestBody(files, fileKeys, params);
        final Request request = new Request.Builder().url(url).post(requestBody)
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Authorization", "Bearer " + Constants.loginInfo.getToken()).build();
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 异步基于post的文件上传,回传上传进度
     *
     * @param url     地址
     * @param file    提交的文件
     * @param fileKey 提交的文件key
     */
    public void uploadAsync(String url, File file, String fileKey, OKHttpUICallback.ProgressCallback listener) throws IOException {
        uploadAsync(url, new File[]{file}, new String[]{fileKey}, listener, new Param[0]);
    }

    /**
     * 异步基于post的文件上传,回传上传进度
     *
     * @param url      地址
     * @param files    提交的文件数组
     * @param fileKeys 提交的文件数组key
     * @param params   提交的键值对
     */
    public void uploadAsync(String url, File[] files, String[] fileKeys, final OKHttpUICallback.ProgressCallback uploadListener, Param[] params) throws IOException {
        final RequestBody requestBody = buildMultipartFormRequestBody(files, fileKeys, params);
        final Request request = new Request.Builder().url(url).post(new ProgressBody.ProgressRequestBody(requestBody, uploadListener, okHttpHandler)).build();
        mOkHttpClient.newCall(request).enqueue(new OKHttpThreadCallback(okHttpHandler, uploadListener, false));
    }

    /**
     * 生成post提交时的分块request
     *
     * @param files
     * @param fileKeys
     * @param params
     * @return
     */
    private RequestBody buildMultipartFormRequestBody(File[] files, String[] fileKeys, Param[] params) {
        if (params == null) {
            params = new Param[0];
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Param param : params) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""), RequestBody.create(null, param.value));
        }
        if (files == null) {
            files = new File[0];
        }
        if (fileKeys == null) {
            fileKeys = new String[0];
        }
        if (fileKeys.length != files.length) {
            throw new ArrayStoreException("fileKeys.length != files.length");
        }
        RequestBody fileBody = null;
        int length = files.length;
        for (int i = 0; i < length; i++) {
            File file = files[i];
            String fileName = file.getName();
            fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file); //TODO 根据文件名设置contentType
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + fileKeys[i] + "\"; fileName=\"" + fileName + "\""), fileBody);
        }
        return builder.build();
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(path);
        if (type == null) {
            type = "application/octet-stream";
        }
        return type;
    }

    public static final class Param {
        private String key;
        private String value;

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}



