package com.harreke.easyapp.demo.controller;

import android.support.annotation.NonNull;

import com.harreke.easyapp.controllerlayout.ControllerLayout;
import com.harreke.easyapp.controllerlayout.ControllerWidget;
import com.harreke.easyapp.controllerlayout.LockResponse;
import com.harreke.easyapp.demo.R;
import com.harreke.easyapp.injection.annotation.InjectClick;

/**
 由 Harreke 于 2016/1/6 创建
 */
public abstract class UnlockLandScape extends ControllerWidget {
    public UnlockLandScape(@NonNull ControllerLayout controllerLayout) {
        super(controllerLayout, R.layout.controller_unlock_landscape);

        setLockResponse(LockResponse.Locked);
    }

    @InjectClick
    protected abstract void middle_unlock();
}