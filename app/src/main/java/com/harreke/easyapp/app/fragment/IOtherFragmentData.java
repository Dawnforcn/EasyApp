package com.harreke.easyapp.app.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 由huoqisheng于2016/9/5创建
 */
public interface IOtherFragmentData {
    void onReceiveDataFromOtherFragment(@NonNull String senderTag, @NonNull String name, @Nullable Object data);

    void sendDataToOtherFragment(@NonNull String receiverTag, @NonNull String name, @Nullable Object data);
}