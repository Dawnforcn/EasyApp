package com.harreke.easyapp.demo.singleton;

import com.harreke.easyapp.app.application.ApplicationFramework;
import com.orhanobut.logger.Logger;

/**
 由Harreke于2016/1/6创建
 */
public class Demo extends ApplicationFramework {
    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init().hideThreadInfo().methodCount(1);
    }
}