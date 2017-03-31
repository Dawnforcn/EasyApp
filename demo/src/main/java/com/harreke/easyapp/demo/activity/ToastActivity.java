package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.common.toaster.Toaster;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;

/**
 由 Harreke 于 2016/1/11 创建
 */
@InjectLayout
@InjectToolbar
public class ToastActivity extends ActivityFramework {
    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
    }

    @Override
    public void launch() {
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @InjectClick
    void toast_hide() {
        hideToast();
    }

    @InjectClick
    void toast_show() {
        showToast("这是一条临时性Toast");
    }

    @InjectClick
    void toast_show_indeterminate() {
        showToast("这是一条持续性Toast", Toaster.DURATION_INFINITE);
    }
}