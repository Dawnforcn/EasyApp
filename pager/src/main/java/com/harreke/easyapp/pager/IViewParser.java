package com.harreke.easyapp.pager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Harreke on 2016/1/5.
 */
public interface IViewParser {
    View createView(LayoutInflater inflater, ViewGroup container, int position);

    int getViewCount();

    CharSequence getViewTitle(int position);
}