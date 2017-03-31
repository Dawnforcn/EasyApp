package com.harreke.easyapp.app.application;

import android.app.Application;

import com.harreke.easyapp.EasyNetwork;
import com.harreke.easyapp.Util;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/07/30  Application框架
 */
public class ApplicationFramework extends Application {
    private static ApplicationFramework mInstance = null;

    public static ApplicationFramework getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        Util.init(this);
        EasyNetwork.init(this);
    }
}