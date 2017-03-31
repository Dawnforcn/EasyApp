package com.harreke.easyapp.easynetwork.okhttp;

import android.app.Application;
import android.support.annotation.NonNull;

import com.harreke.easyapp.network.creator.IExecutorCreator;
import com.harreke.easyapp.network.executor.BinaryExecutor;

/**
 由huoqisheng于2016/9/20创建
 */

public class OkHttpBinaryExecutorCreator implements IExecutorCreator<BinaryExecutor> {
    public OkHttpBinaryExecutorCreator(@NonNull Application application) {
    }

    @Override
    public BinaryExecutor create() {
        return new OkHttpBinaryExecutor();
    }
}