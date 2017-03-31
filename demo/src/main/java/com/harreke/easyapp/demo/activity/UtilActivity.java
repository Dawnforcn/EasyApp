package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;

/**
 由Harreke于2016/1/12创建
 */
@InjectLayout
@InjectToolbar
public class UtilActivity extends ActivityFramework {
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
}