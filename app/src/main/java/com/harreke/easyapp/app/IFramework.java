package com.harreke.easyapp.app;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.harreke.easyapp.common.interf.IDestroyable;
import com.harreke.easyapp.network.executor.BinaryExecutor;
import com.harreke.easyapp.network.executor.ImageExecutor;
import com.harreke.easyapp.network.executor.StringExecutor;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/07/24

 框架接口
 */
public interface IFramework extends IToaster, IIntent {
    void addContentView(View view, ViewGroup.LayoutParams params);

    /**
     在创建完毕时触发

     可以在该函数中配置视图、添加触发器等
     */
    void config();

    View findViewById(int viewId);

    /**
     获得当前Activity

     @return 当前Activity
     */
    FragmentActivity getActivity();

    /**
     获得当前Activity的Context

     @return 当前Activity的Context
     */
    Context getContext();

    /**
     获得当前Fragment

     @return 当前Fragment
     注：框架本身必须是FragmentFramework才能调用这个函数
     */
    Fragment getFragment();

    /**
     判断该框架是否为Activity

     @return 该框架是否为Activity
     */
    boolean isActivity();

    /**
     在准备好运行时触发

     该函数意为一切就绪，可以执行业务代码
     */
    void launch();

    BinaryExecutor obtainBinaryExecutor(@NonNull String tag);

    ImageExecutor obtainImageExecutor(@NonNull String tag);

    StringExecutor obtainStringExecutor(@NonNull String tag);

    /**
     当需要刷新内容时触发


     触发时间可由{@link ActivityFramework#setRefreshTime(long)}或{@link FragmentFramework#setRefreshTime(long)}设置
     */
    void onRefresh();

    void post(@NonNull Runnable runnable);

    void postDelayed(@NonNull Runnable runnable, long delay);

    void registerDestroyable(@NonNull IDestroyable destroyable);

    void registerDialog(@NonNull Dialog dialog);

    void registerRunnable(@NonNull Runnable runnable);
}