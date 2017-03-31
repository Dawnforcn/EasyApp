package com.harreke.easyapp.app.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 由huoqisheng于2016/9/5创建
 */
public interface IActivityOtherFragmentData {
    void onReceiveOtherFragmentData(@NonNull String senderTag, @NonNull String receiverTag, @NonNull String name, @Nullable Object data);
}
