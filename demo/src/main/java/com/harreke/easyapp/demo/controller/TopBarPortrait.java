package com.harreke.easyapp.demo.controller;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.harreke.easyapp.controllerlayout.ControllerLayout;
import com.harreke.easyapp.controllerlayout.ControllerWidget;
import com.harreke.easyapp.controllerlayout.FullScreenResponse;
import com.harreke.easyapp.demo.R;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.util.ViewUtil;


/**
 由 Harreke 于 2016/1/5 创建
 */
public abstract class TopBarPortrait extends ControllerWidget {
    @InjectView
    View top_simple_bar_back;
    @InjectView
    View top_simple_bar_setting;
    @InjectView
    TextView top_simple_bar_title;

    public TopBarPortrait(@NonNull ControllerLayout controllerLayout) {
        super(controllerLayout, R.layout.controller_top_bar_portrait);

        setFullScreenResponse(FullScreenResponse.NonFullScreen);
    }

    @Override
    public void onAttached() {
        super.onAttached();
        ViewUtil.patchTopPadding(getView());
    }

    @InjectClick
    protected abstract void top_simple_bar_back();

    @InjectClick
    protected abstract void top_simple_bar_share();
}