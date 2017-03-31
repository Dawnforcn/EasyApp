package com.harreke.easyapp;

import android.app.Application;
import android.support.annotation.NonNull;

import com.harreke.easyapp.util.MetricUtil;
import com.harreke.easyapp.util.PackageUtil;
import com.harreke.easyapp.util.PreferenceUtil;

/**
 由 huoqisheng 于 2017/3/30 创建
 */
public class Util {
    public static void init(@NonNull Application application) {
        PreferenceUtil.init(application);
        MetricUtil.init(application);
        PackageUtil.init(application);
    }
}