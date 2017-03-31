package com.harreke.easyapp.demo.controller;

import android.support.annotation.NonNull;

import com.harreke.easyapp.controllerlayout.AutoResponse;
import com.harreke.easyapp.controllerlayout.ControllerLayout;
import com.harreke.easyapp.controllerlayout.ControllerWidget;
import com.harreke.easyapp.demo.R;

/**
 由Harreke于2016/1/7创建
 */
public class SettingExpandLandScape extends ControllerWidget {
    public SettingExpandLandScape(@NonNull ControllerLayout controllerLayout) {
        super(controllerLayout, R.layout.controller_setting_expand_lanscape);

        setAutoResponse(AutoResponse.Hide);
    }
}