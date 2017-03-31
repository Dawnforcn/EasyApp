package com.harreke.easyapp.app.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 由 启圣 于 2015/6/5 创建
 */
public interface IFragmentData {
    /**
     接受并处理来自Activity的消息

     @param name 消息名称
     @param data 消息内容
     */
    void onReceiveDataFromActivity(@NonNull String name, @Nullable Object data);

    /**
     对Activity发送数据

     @param name 消息名称
     @param data 消息内容
     */
    void sendDataToActivity(@NonNull String name, @Nullable Object data);
}