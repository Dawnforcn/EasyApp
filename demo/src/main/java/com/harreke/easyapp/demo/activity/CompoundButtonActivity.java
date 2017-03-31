package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.injection.annotation.InjectButtonsCheck;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;

/**
 由Harreke于2016/1/12创建
 */
@InjectLayout
@InjectToolbar
public class CompoundButtonActivity extends ActivityFramework {
    @Override
    public void argument(Intent intent) {
    }

    @InjectButtonsCheck({"compound_button1", "compound_button2", "compound_button3", "compound_button4", "compound_button5", "compound_button6"})
    void buttons(CompoundButton compoundButton, int index) {
        showToast("选中了按钮：" + compoundButton.getText());
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