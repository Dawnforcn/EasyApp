package com.harreke.easyapp.controllerlayout;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.harreke.easyapp.animator.viewanimator.ViewAnimator;

/**
 Created by Harreke on 2015/9/10.

 控制控件接口

 可以挂载于控制布局{@link ControllerLayout}内，用来处理一些播放器的操作

 播放器每个小功能都可以用一个单独的控制控件来处理

 控制控件实现了统一管理接口，可以方便的进行批量管控
 */
public interface IControllerWidget {
    ViewAnimator getAnimator();

    @NonNull
    AppearanceAnimation getAppearanceAnimation();

    @NonNull
    AutoResponse getAutoResponse();

    /**
     获得该控件的父级布局视图

     注：如果该控件还未挂载至控制布局，则返回空值

     @return 该控件的父级布局视图
     */
    @Nullable
    ControllerLayout getContainer();

    @NonNull
    FullScreenResponse getFullScreenResponse();

    @NonNull
    LockResponse getLockResponse();

    /**
     获得该控件的视图

     @return 该控件的视图
     */
    @NonNull
    View getView();

    /**
     隐藏该控件

     @param animate 是否播放隐藏动画
     */
    void hide(boolean animate);

    /**
     判断该控件是否可用

     @return 该控件是否可用
     */
    boolean isEnabled();

    /**
     判断是否正在显示

     注：如果该控件设置为非自动响应显示{@link AutoResponse#Hide}或{@link AutoResponse#None}时，则会忽略判断

     @return 是否正在显示
     */
    boolean isShowing();

    /**
     当挂载至控制布局时触发
     */
    void onAttached();

    /**
     当移出控制布局时触发
     */
    void onDetached();

    /**
     当控件测量完毕后触发

     需要在此函数中对该控件布局进行相关初始化操作
     */
    void onPrepared();

    void onShowStatusChanged(boolean isShowing);

    /**
     设置该控件是否可用

     可以用来判断控件是否响应点击或触摸等事件

     @param enabled 该控件是否可用
     */
    void setEnabled(boolean enabled);

    /**
     显示该控件

     @param animate 是否播放显示动画
     */
    void show(boolean animate);
}