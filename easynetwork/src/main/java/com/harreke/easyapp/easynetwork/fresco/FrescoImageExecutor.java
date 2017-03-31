package com.harreke.easyapp.easynetwork.fresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.harreke.easyapp.network.IRequestCallback;
import com.harreke.easyapp.network.executor.ImageExecutor;

/**
 由huoqisheng于2016/7/7创建
 */
public class FrescoImageExecutor extends ImageExecutor {
    private DataSource<CloseableReference<CloseableImage>> mDataSource;

    @Override
    public void cancel() {
        if (mDataSource != null) {
            mDataSource.close();
            mDataSource = null;
        }
    }

    private void checkAndSetImage(Bitmap bitmap) {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof DraweeView)) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void checkAndSetImageRetry() {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof DraweeView)) {
            int retryImageId = getRetryImageId();
            if (retryImageId > 0) {
                imageView.setImageResource(retryImageId);
            }
        }
    }

    @Override
    public void execute(@NonNull Context context) {
        String imageUrl = getRequestUrl();
        if (imageUrl == null) {
            return;
        }
        ImageView imageView = getImageView();
        if (imageView != null) {
            ImageRequest imageRequest = ImageRequest.fromUri(Uri.parse(imageUrl));
            if (imageView instanceof DraweeView) {
                DraweeView draweeView = (DraweeView) imageView;
                DraweeController draweeController =
                        Fresco.newDraweeControllerBuilder().setOldController(draweeView.getController()).setImageRequest(imageRequest).build();
                draweeView.setController(draweeController);
            } else {
                int loadingImageId = getLoadingImageId();
                if (loadingImageId > 0) {
                    imageView.setImageResource(loadingImageId);
                }
            }
            mDataSource = Fresco.getImagePipeline().fetchDecodedImage(imageRequest, imageView.getContext());
            mDataSource.subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                @Override
                protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    checkAndSetImageRetry();
                    IRequestCallback<Bitmap> requestCallback = getRequestCallback();
                    if (requestCallback != null) {
                        requestCallback.onFailure();
                    }
                    destroy();
                }

                @Override
                public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    if (!dataSource.isFinished()) {
                        return;
                    }
                    CloseableReference<CloseableImage> closeableImageRef = dataSource.getResult();
                    Bitmap bitmap = null;
                    if (closeableImageRef != null && closeableImageRef.get() instanceof CloseableBitmap) {
                        bitmap = ((CloseableBitmap) closeableImageRef.get()).getUnderlyingBitmap();
                    }
                    checkAndSetImage(bitmap);
                    IRequestCallback<Bitmap> requestCallback = getRequestCallback();
                    if (requestCallback != null) {
                        requestCallback.onSuccess(bitmap);
                    }
                    CloseableReference.closeSafely(closeableImageRef);
                    destroy();
                }
            }, UiThreadImmediateExecutorService.getInstance());
        }
    }

    @Override
    public boolean isExecuting() {
        return mDataSource != null && !mDataSource.isFinished();
    }
}