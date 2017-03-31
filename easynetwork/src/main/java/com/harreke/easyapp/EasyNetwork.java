package com.harreke.easyapp;

import android.app.Application;
import android.support.annotation.NonNull;

import com.harreke.easyapp.easynetwork.fresco.FrescoImageExecutorCreator;
import com.harreke.easyapp.easynetwork.okhttp.OkHttpBinaryExecutorCreator;
import com.harreke.easyapp.easynetwork.okhttp.OkHttpStringExecutorCreator;
import com.harreke.easyapp.network.creator.IExecutorCreator;
import com.harreke.easyapp.network.executor.BinaryExecutor;
import com.harreke.easyapp.network.executor.ImageExecutor;
import com.harreke.easyapp.network.executor.StringExecutor;

/**
 由 huoqisheng 于 2017/3/30 创建
 */
public class EasyNetwork {
    private static IExecutorCreator<BinaryExecutor> mBinaryExecutorCreator;
    private static IExecutorCreator<ImageExecutor> mImageExecutorCreator;
    private static IExecutorCreator<StringExecutor> mStringExecutorCreator;

    public static BinaryExecutor createBinaryExecutor() {
        return mBinaryExecutorCreator.create();
    }

    public static ImageExecutor createImageExecutor() {
        return mImageExecutorCreator.create();
    }

    public static StringExecutor createStringExecutor() {
        return mStringExecutorCreator.create();
    }

    public static void init(@NonNull Application application) {
        mImageExecutorCreator = new FrescoImageExecutorCreator(application);
        mStringExecutorCreator = new OkHttpStringExecutorCreator(application);
        mBinaryExecutorCreator = new OkHttpBinaryExecutorCreator(application);
    }

    public static void setBinaryExecutorCreator(@NonNull IExecutorCreator<BinaryExecutor> binaryExecutorCreator) {
        mBinaryExecutorCreator = binaryExecutorCreator;
    }

    public static void setImageExecutorCreator(@NonNull IExecutorCreator<ImageExecutor> imageExecutorCreator) {
        mImageExecutorCreator = imageExecutorCreator;
    }

    public static void setStringExecutorCreator(@NonNull IExecutorCreator<StringExecutor> stringExecutorCreator) {
        mStringExecutorCreator = stringExecutorCreator;
    }
}