package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RadioButton;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.injection.annotation.InjectCheck;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectGroupCheck;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectTouch;

/**
 由 huoqisheng 于 2016/6/2 创建
 */
@InjectLayout
@InjectToolbar
public class InjectionActivity extends ActivityFramework {
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
    void text2() {
        showToast("点击了Click Me");
    }

    @InjectTouch
    boolean text3(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT >= 19) {
            showToast("触摸了Touch Me - " + MotionEvent.actionToString(motionEvent.getAction()));
        } else {
            showToast("触摸了Touch Me");
        }

        return true;
    }

    @InjectCheck
    void text4(boolean isChecked) {
        showToast("选中了Check Me - " + isChecked);
    }

    @InjectGroupCheck
    void text5(RadioButton radioButton, int position) {
        showToast("选中了Radio Check Me " + position);
    }
}