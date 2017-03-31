package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.EditText;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.widget.circluarprogress.CircularProgressView;


/**
 由 Harreke 于 2016/1/12 创建
 */
@InjectLayout
@InjectToolbar
public class CircularProgressActivity extends ActivityFramework {
    @InjectView
    CircularProgressView progress;
    @InjectView
    EditText progress_input;

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
    void progress_set() {
        String number = progress_input.getText().toString();

        if (number.length() == 0) {
            number = "0";
        }
        progress.setProgress((float) Integer.valueOf(number) / 100f);
    }

    @InjectClick
    void progress_start() {
        progress.start();
    }

    @InjectClick
    void progress_stop() {
        progress.stop();
    }
}