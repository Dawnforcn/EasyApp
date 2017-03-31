package com.harreke.easyapp.injection;

import android.content.Context;

/**
 * 由huoqisheng于2016/7/8创建
 */
public interface ILayoutInject<TARGET> {
    int layout(TARGET target, Context context);
}
