package com.harreke.easyapp.network.executor;

import android.support.annotation.NonNull;

import com.harreke.easyapp.network.IProgressCallback;
import com.harreke.easyapp.network.IRequestCallback;
import com.harreke.easyapp.network.RequestBuilder;
import com.harreke.easyapp.network.UploadPair;

import java.io.File;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/04/29
 */
public abstract class StringExecutor extends RequestExecutor<String> {
    public static final boolean LOG = true;
    private UploadPair mUploadPair = null;

    @Override
    public void destroy() {
        super.destroy();
        mUploadPair = null;
    }

    protected UploadPair getUploadPair() {
        return mUploadPair;
    }

    @Override
    public StringExecutor progressCallback(@NonNull IProgressCallback progressCallback) {
        return (StringExecutor) super.progressCallback(progressCallback);
    }

    @Override
    public StringExecutor request(@NonNull RequestBuilder requestBuilder) {
        if (LOG) {
            requestBuilder.print();
        }
        return (StringExecutor) super.request(requestBuilder);
    }

    @Override
    public StringExecutor request(@NonNull String requestUrl) {
        return (StringExecutor) super.request(requestUrl);
    }

    @Override
    public StringExecutor requestCallback(@NonNull IRequestCallback<String> requestCallback) {
        return (StringExecutor) super.requestCallback(requestCallback);
    }

    public StringExecutor upload(@NonNull String key, @NonNull String type, @NonNull File file) {
        mUploadPair = new UploadPair(key, type, file);

        return this;
    }
}