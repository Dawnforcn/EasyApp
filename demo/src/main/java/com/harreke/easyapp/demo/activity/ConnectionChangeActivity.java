package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.util.ConnectionUtil;

/**
 由 Harreke 于 2016/1/12 创建
 */
@InjectLayout
@InjectToolbar
public class ConnectionChangeActivity extends ActivityFramework {
    @InjectView
    TextView connection_change_status;
    @InjectView
    TextView connection_change_status_mobile;
    @InjectView
    TextView connection_change_status_wifi;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
    }

    @Override
    public void launch() {
        onConnectionChange(ConnectionUtil.isConnected());
    }

    @Override
    protected void onConnectionChange(boolean connected) {
        if (connection_change_status != null) {
            connection_change_status.setText(String.format("网络状态：%s", connected ? "已连接" : "未连接"));
        }
        if (connection_change_status_mobile != null) {
            connection_change_status_mobile.setText(String.format("2G/3G/4G网络状态：%s", ConnectionUtil.mobileConnected ? "已连接" : "未连接"));
        }
        if (connection_change_status_wifi != null) {
            connection_change_status_wifi.setText(String.format("WIFI网络状态：%s", ConnectionUtil.wifiConnected ? "已连接" : "未连接"));
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}