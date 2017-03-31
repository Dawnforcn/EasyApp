package com.harreke.easyapp.easynetwork.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.harreke.easyapp.network.IRequestCallback;
import com.harreke.easyapp.network.RequestBuilder;
import com.harreke.easyapp.network.executor.StringExecutor;
import com.harreke.easyapp.network.UploadPair;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 由huoqisheng于2016/7/4创建
 */
public class OkHttpStringExecutor extends StringExecutor {
    private static final int FAILURE = -1;
    private static final int SUCCESS = 0;
    private Call mCall;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            IRequestCallback<String> requestCallback = getRequestCallback();
            if (requestCallback == null) {
                destroy();
                return false;
            }
            if (message.what == SUCCESS) {
                requestCallback.onSuccess((String) message.obj);
            } else {
                requestCallback.onFailure();
            }
            destroy();
            return true;
        }
    });
    private Callback mCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Message message = mHandler.obtainMessage();
            message.what = FAILURE;
            message.sendToTarget();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Message message = mHandler.obtainMessage();
            message.what = SUCCESS;
            message.obj = response.body().string();
            message.sendToTarget();
            response.body().close();
        }
    };

    @Override
    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
            mCall = null;
        }
    }

    @Override
    public void execute(@NonNull Context context) {
        cancel();
        RequestBuilder requestBuilder = getRequestBuilder();
        if (requestBuilder == null) {
            return;
        }
        String requestUrl = requestBuilder.getUrl();
        if (requestUrl == null) {
            return;
        }
        Headers headers = getHeaders(requestBuilder);
        RequestBody requestBody = getRequestBody(requestBuilder);
        mCall = OkHttpClientHelper.getInstance().call(requestUrl, headers, requestBody);
        mCall.enqueue(mCallback);

    }

    private Headers getHeaders(RequestBuilder requestBuilder) {
        if (!requestBuilder.hasHeader()) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        Map<String, String> headerMap = requestBuilder.getHeader();
        for (Map.Entry<String, String> header : headerMap.entrySet()) {
            builder.add(header.getKey(), header.getValue());
        }
        return builder.build();
    }

    private RequestBody getRequestBody(RequestBuilder requestBuilder) {
        UploadPair uploadPair = getUploadPair();
        if (uploadPair == null && !requestBuilder.hasBody()) {
            return null;
        }
        if (uploadPair != null && !requestBuilder.hasBody()) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            return builder.addFormDataPart(uploadPair.name,
                    uploadPair.file.getName(),
                    RequestBody.create(MediaType.parse(uploadPair.contentType), uploadPair.file)).build();
        } else if (uploadPair != null && requestBuilder.hasBody()) {
            Map<String, String> bodyMap = requestBuilder.getBody();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (Map.Entry<String, String> body : bodyMap.entrySet()) {
                builder.addFormDataPart(body.getKey(), body.getValue());
            }
            builder.addFormDataPart(uploadPair.name, uploadPair.file.getName(), RequestBody.create(MediaType.parse(uploadPair.contentType), uploadPair.file));

            return builder.build();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            Map<String, String> bodyMap = requestBuilder.getBody();
            for (Map.Entry<String, String> body : bodyMap.entrySet()) {
                builder.add(body.getKey(), body.getValue());
            }
            return builder.build();
        }
    }

    @Override
    public boolean isExecuting() {
        return mCall != null && !mCall.isCanceled();
    }
}