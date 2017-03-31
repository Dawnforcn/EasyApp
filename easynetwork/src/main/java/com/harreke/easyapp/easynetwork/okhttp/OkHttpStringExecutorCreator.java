package com.harreke.easyapp.easynetwork.okhttp;

import android.app.Application;
import android.support.annotation.NonNull;

import com.harreke.easyapp.network.creator.IExecutorCreator;
import com.harreke.easyapp.network.executor.StringExecutor;

/**
 由huoqisheng于2016/9/20创建
 */

public class OkHttpStringExecutorCreator implements IExecutorCreator<StringExecutor> {
    public OkHttpStringExecutorCreator(@NonNull Application application) {
    }

    @Override
    public StringExecutor create() {
        return new OkHttpStringExecutor();
    }
}