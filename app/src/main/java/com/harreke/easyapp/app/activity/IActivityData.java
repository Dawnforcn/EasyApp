package com.harreke.easyapp.app.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 由启圣于2015/6/5创建
 */
public interface IActivityData {
    /**
     接受并处理来自Fragment的消息

     @param tag 发送消息的Fragment的标签
     @param name 消息名称
     @param data 消息内容
     */
    void onReceiveDataFromFragment(@NonNull String tag, @NonNull String name, @Nullable Object data);

    /**
     对Fragment发送数据

     @param tag 目标Fragment在的标签
     @param name 消息名称
     @param data 消息内容
     */
    void sendDataToFragment(@NonNull String tag, @NonNull String name, @Nullable Object data);
}