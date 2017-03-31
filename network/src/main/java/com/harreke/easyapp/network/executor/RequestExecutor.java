package com.harreke.easyapp.network.executor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.harreke.easyapp.common.interf.IDestroyable;
import com.harreke.easyapp.network.IProgressCallback;
import com.harreke.easyapp.network.IRequestCallback;
import com.harreke.easyapp.network.IRequestExecutor;
import com.harreke.easyapp.network.RequestBuilder;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/04/30
 */
public abstract class RequestExecutor<RESULT> implements IRequestExecutor, IDestroyable {
    private IProgressCallback mProgressCallback;
    private RequestBuilder mRequestBuilder;
    private IRequestCallback<RESULT> mRequestCallback;

    @Override
    public void destroy() {
        cancel();
        mProgressCallback = null;
        mRequestBuilder = null;
        mRequestCallback = null;
    }

    public abstract void execute(@NonNull Context context);

    protected IRequestExecutor getExecutor() {
        return this;
    }

    protected IProgressCallback getProgressCallback() {
        return mProgressCallback;
    }

    protected RequestBuilder getRequestBuilder() {
        return mRequestBuilder;
    }

    protected IRequestCallback<RESULT> getRequestCallback() {
        return mRequestCallback;
    }

    protected String getRequestUrl() {
        RequestBuilder requestBuilder = getRequestBuilder();

        return requestBuilder != null ? requestBuilder.getUrl() : null;
    }

    public RequestExecutor progressCallback(@NonNull IProgressCallback progressCallback) {
        mProgressCallback = progressCallback;

        return this;
    }

    public RequestExecutor<RESULT> request(@NonNull RequestBuilder requestBuilder) {
        mRequestBuilder = requestBuilder;

        return this;
    }

    public RequestExecutor<RESULT> request(@NonNull String requestUrl) {
        return request(new RequestBuilder("load_" + requestUrl).setUrl(requestUrl));
    }

    public RequestExecutor<RESULT> requestCallback(@NonNull IRequestCallback<RESULT> requestCallback) {
        mRequestCallback = requestCallback;

        return this;
    }
}