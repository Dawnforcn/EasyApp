package com.harreke.easyapp.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/04/10

 Fragment框架接口
 */
public interface IFragment extends IFragmentData {
    /**
     初始化Fragment传参数据
     */
    void argument(Bundle bundle);

    /**
     获得Fragment框架中加载的Fragment

     @param tag Fragment标签

     @return Fragment
     */
    Fragment getChildFragment(String tag);
}
