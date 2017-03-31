package com.harreke.easyapp.easynetwork.fresco;

import android.app.Application;
import android.support.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.harreke.easyapp.network.creator.IExecutorCreator;
import com.harreke.easyapp.network.executor.ImageExecutor;

/**
 由huoqisheng于2016/9/20创建
 */

public class FrescoImageExecutorCreator implements IExecutorCreator<ImageExecutor> {
    public FrescoImageExecutorCreator(@NonNull Application application) {
        Fresco.initialize(application);
    }

    @Override
    public ImageExecutor create() {
        return new FrescoImageExecutor();
    }
}