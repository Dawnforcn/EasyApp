package com.harreke.easyapp.controllerlayout;

import android.support.annotation.NonNull;

import com.harreke.easyapp.common.interf.IDestroyable;

/**
 * Created by Harreke on 2015/9/10.
 *
 * 控制布局接口
 *
 * 控制布局是一个容器，内部可以挂载多个控制控件{@link IControllerWidget}，每个控件可以单独实现一种简单的播放器操作
 *
 * 控制布局本身可以实现手势操作的监听，通过统一接口管理所有控件
 */
public interface IControllerLayout extends IDestroyable {
    /**
     * 将一个新的控制控件{@link IControllerWidget}挂载于该控制布局内
     *
     * @param type
     *         控件种类
     * @param controllerWidget
     *         控件
     */
    void attachWidget(@NonNull String type, @NonNull IControllerWidget controllerWidget);

    /**
     * 隐藏挂载于该控制布局的所有控件
     *
     * 注：只会隐藏自动隐藏的控件
     *
     * @param animate
     *         是否播放隐藏动画
     */
    void hide(boolean animate);

    boolean isLocked();

    /**
     * 判断布局内是否正在显示任意一个控件
     *
     * 注：只会检测标记为自动响应的控件
     *
     * @return 是否正在显示任意一个控件
     */
    boolean isShowing();

    /**
     * 显示挂载于该控制布局的所有控件
     *
     * 注：只会显示自动显示的控件
     *
     * @param animate
     *         是否播放显示动画
     */
    void show(boolean animate);

    void toggle(boolean animate);
}