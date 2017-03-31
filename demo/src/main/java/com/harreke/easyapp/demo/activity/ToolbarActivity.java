package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectMenu;
import com.harreke.easyapp.injection.annotation.InjectToolbar;

/**
 由 Harreke 于 2016/1/11 创建
 */
@InjectLayout
@InjectMenu
@InjectToolbar
public class ToolbarActivity extends ActivityFramework {
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
        showToast("点击了菜单：" + item.getTitle());

        return false;
    }
}