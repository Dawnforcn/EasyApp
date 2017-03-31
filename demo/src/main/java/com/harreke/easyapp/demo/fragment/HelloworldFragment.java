package com.harreke.easyapp.demo.fragment;

import android.os.Bundle;

import com.harreke.easyapp.app.fragment.FragmentFramework;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectLayout;

/**
 由huoqisheng于2016/7/28创建
 */
@InjectLayout
public class HelloworldFragment extends FragmentFramework {
    @Override
    public void argument(Bundle bundle) {
    }

    @InjectClick
    void button_clickme() {
        showToast("Clicked!");
    }

    @Override
    public void config() {
    }

    @Override
    public void launch() {
    }
}