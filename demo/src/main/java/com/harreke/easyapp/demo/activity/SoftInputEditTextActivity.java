package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.helper.ViewSwitchHelper;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.widget.softinputview.SoftInputEditText;

/**
 由 Harreke 于 2016/1/12 创建
 */
@InjectLayout
@InjectToolbar
public class SoftInputEditTextActivity extends ActivityFramework implements SoftInputEditText.OnToggleListener {
    ViewSwitchHelper mOpenSwitch;
    @InjectView
    View soft_input_close;
    @InjectView
    SoftInputEditText soft_input_edit;
    @InjectView
    View soft_input_open;
    @InjectView
    TextView soft_input_toast;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        soft_input_edit.setOnToggleListener(this);
        mOpenSwitch = new ViewSwitchHelper(soft_input_open, soft_input_close);
    }

    @Override
    public void launch() {
    }

    @Override
    public void onHideSoftInput(EditText editText) {
        soft_input_toast.setText("输入法状态：关闭");
        mOpenSwitch.switchToView(soft_input_open);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onShowSoftInput(EditText editText) {
        soft_input_toast.setText("输入法状态：显示");
        mOpenSwitch.switchToView(soft_input_close);
    }

    @InjectClick
    void soft_input_close() {
        soft_input_edit.hideSoftInput();
    }

    @InjectClick
    void soft_input_open() {
        soft_input_edit.showSoftInput();
    }
}