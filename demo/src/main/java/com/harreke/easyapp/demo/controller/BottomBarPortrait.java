package com.harreke.easyapp.demo.controller;

import android.support.annotation.NonNull;
import android.view.View;

import com.harreke.easyapp.controllerlayout.ControllerLayout;
import com.harreke.easyapp.controllerlayout.ControllerWidget;
import com.harreke.easyapp.controllerlayout.FullScreenResponse;
import com.harreke.easyapp.demo.R;
import com.harreke.easyapp.helper.ViewSwitchHelper;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectView;


/**
 由 Harreke 于 2016/1/5 创建
 */
public abstract class BottomBarPortrait extends ControllerWidget {
    @InjectView
    View bottom_simple_bar_fullscreen_enter;
    @InjectView
    View bottom_simple_bar_pause;
    @InjectView
    View bottom_simple_bar_play;
    private ViewSwitchHelper mPlaySwitchHelper;

    public BottomBarPortrait(@NonNull ControllerLayout controllerLayout) {
        super(controllerLayout, R.layout.controller_bottom_bar_portrait);

        setFullScreenResponse(FullScreenResponse.NonFullScreen);

        mPlaySwitchHelper = new ViewSwitchHelper(bottom_simple_bar_play, bottom_simple_bar_pause);
    }

    @InjectClick
    protected abstract void bottom_simple_bar_fullscreen_enter();

    @InjectClick
    protected abstract void bottom_simple_bar_pause();

    @InjectClick
    protected abstract void bottom_simple_bar_play();

    public void showPause() {
        mPlaySwitchHelper.switchToView(bottom_simple_bar_pause);
    }

    public void showPlay() {
        mPlaySwitchHelper.switchToView(bottom_simple_bar_play);
    }
}