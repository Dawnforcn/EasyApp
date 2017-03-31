package com.harreke.easyapp.network.executor;

import android.support.annotation.NonNull;

import com.harreke.easyapp.network.IProgressCallback;
import com.harreke.easyapp.network.IRequestCallback;
import com.harreke.easyapp.network.RequestBuilder;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/04/29
 */
public abstract class BinaryExecutor extends RequestExecutor<byte[]> {
    @Override
    public BinaryExecutor progressCallback(@NonNull IProgressCallback progressCallback) {
        return (BinaryExecutor) super.progressCallback(progressCallback);
    }

    @Override
    public BinaryExecutor request(@NonNull RequestBuilder requestBuilder) {
        return (BinaryExecutor) super.request(requestBuilder);
    }

    @Override
    public BinaryExecutor request(@NonNull String requestUrl) {
        return (BinaryExecutor) super.request(requestUrl);
    }

    @Override
    public BinaryExecutor requestCallback(@NonNull IRequestCallback<byte[]> requestCallback) {
        return (BinaryExecutor) super.requestCallback(requestCallback);
    }
}