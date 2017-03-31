package com.harreke.easyapp.network.executor;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.harreke.easyapp.network.IProgressCallback;
import com.harreke.easyapp.network.IRequestCallback;
import com.harreke.easyapp.network.RequestBuilder;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/04/29
 */
public abstract class ImageExecutor extends RequestExecutor<Bitmap> {
    private ImageView mImageView;
    private int mLoadingImageId = 0;
    private int mRetryImageId = 0;

    @Override
    public void destroy() {
        super.destroy();
        mImageView = null;
        mLoadingImageId = 0;
        mRetryImageId = 0;
    }

    protected ImageView getImageView() {
        return mImageView;
    }

    protected int getLoadingImageId() {
        return mLoadingImageId;
    }

    protected int getRetryImageId() {
        return mRetryImageId;
    }

    public ImageExecutor imageView(@NonNull ImageView imageView) {
        mImageView = imageView;

        return this;
    }

    public ImageExecutor loadingImageId(int loadingImageId) {
        mLoadingImageId = loadingImageId;

        return this;
    }

    @Override
    public ImageExecutor progressCallback(@NonNull IProgressCallback progressCallback) {
        return (ImageExecutor) super.progressCallback(progressCallback);
    }

    @Override
    public ImageExecutor request(@NonNull RequestBuilder requestBuilder) {
        return (ImageExecutor) super.request(requestBuilder);
    }

    @Override
    public ImageExecutor request(@NonNull String requestUrl) {
        return (ImageExecutor) super.request(requestUrl);
    }

    @Override
    public ImageExecutor requestCallback(@NonNull IRequestCallback<Bitmap> requestCallback) {
        return (ImageExecutor) super.requestCallback(requestCallback);
    }

    public ImageExecutor retryImageId(int retryImageId) {
        mRetryImageId = retryImageId;

        return this;
    }
}