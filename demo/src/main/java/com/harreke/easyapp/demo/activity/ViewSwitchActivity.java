package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.helper.ViewSwitchHelper;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;

/**
 由 Harreke 于 2016/1/12 创建
 */
@InjectLayout
@InjectToolbar
public class ViewSwitchActivity extends ActivityFramework {
    @InjectView
    View view_switch_button1;
    @InjectView
    View view_switch_button2;
    @InjectView
    View view_switch_button3;
    private ViewSwitchHelper mViewSwitchHelper;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        mViewSwitchHelper = new ViewSwitchHelper(view_switch_button1, view_switch_button2, view_switch_button3);
    }

    @Override
    public void launch() {
    }

    public void onButton1Click(View view) {
        mViewSwitchHelper.switchToView(view_switch_button2);
    }

    public void onButton2Click(View view) {
        mViewSwitchHelper.switchToView(view_switch_button3);
    }

    public void onButton3Click(View view) {
        mViewSwitchHelper.switchToView(view_switch_button1);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}