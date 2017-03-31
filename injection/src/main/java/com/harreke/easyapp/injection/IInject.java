package com.harreke.easyapp.injection;

import android.view.View;

/**
 * 由huoqisheng于2016/6/9创建
 */
public interface IInject<TARGET> {
    void inject(TARGET target, View view);
}