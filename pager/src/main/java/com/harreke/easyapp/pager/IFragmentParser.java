package com.harreke.easyapp.pager;

import android.support.v4.app.Fragment;

/**
 * 由Harreke于2016/1/5创建
 */
public interface IFragmentParser {
    Fragment createFragment(int position);

    int getFragmentCount();

    String getFragmentTag(int position);

    String getFragmentTitle(int position);

    float getFragmentWidthScale(int position);
}