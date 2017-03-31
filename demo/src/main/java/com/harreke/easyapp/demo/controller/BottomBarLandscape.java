package com.harreke.easyapp.demo.controller;

import android.support.annotation.NonNull;
import android.view.View;

import com.harreke.easyapp.controllerlayout.ControllerLayout;
import com.harreke.easyapp.controllerlayout.ControllerWidget;
import com.harreke.easyapp.demo.R;
import com.harreke.easyapp.helper.ViewSwitchHelper;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.widget.softinputview.SoftInputEditText;

/**
 由 Harreke 于 2016/1/5 创建
 */
public abstract class BottomBarLandscape extends ControllerWidget implements SoftInputEditText.OnToggleListener {
    @InjectView
    SoftInputEditText bottom_bar_input;
    @InjectView
    View bottom_bar_pause;
    @InjectView
    View bottom_bar_play;
    ViewSwitchHelper mPlaySwitchHelper;

    public BottomBarLandscape(@NonNull ControllerLayout controllerLayout) {
        super(controllerLayout, R.layout.controller_bottom_bar_landscape);

        bottom_bar_input.setOnToggleListener(this);
        mPlaySwitchHelper = new ViewSwitchHelper(bottom_bar_pause, bottom_bar_play);
    }

    @InjectClick
    protected abstract void bottom_bar_fullscreen_exit();

    @InjectClick
    protected abstract void bottom_bar_pause();

    @InjectClick
    protected abstract void bottom_bar_play();

    @InjectClick
    protected abstract void bottom_bar_send();

    public void hideSoftInput() {
        bottom_bar_input.hideSoftInput();
    }

    public boolean isSoftInputShowing() {
        return bottom_bar_input.isSoftInputShowing();
    }

    public void showPause() {
        mPlaySwitchHelper.switchToView(bottom_bar_pause);
    }

    public void showPlay() {
        mPlaySwitchHelper.switchToView(bottom_bar_play);
    }
}