package com.harreke.easyapp.app.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * 由 Harreke（harreke@live.cn） 创建于 2015/04/10
 *
 * Activity框架接口
 */
public interface IActivity extends IActivityData {
    /**
     * 初始化Activity传参数据
     */
    void argument(Intent intent);

    /**
     * 退出Activity
     */
    void exit();

    /**
     * 获得Activity框架中加载的Fragment
     *
     * @param tag
     *         Fragment标签
     *
     * @return Fragment
     */
    Fragment getManagerFragment(String tag);

    /**
     * 获得Activity框架中加载的Fragment数量
     *
     * @return Fragment数量
     */
    int getManagerFragmentCount();
}