package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;

import com.harreke.easyapp.animator.viewanimator.ViewAnimator;
import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.helper.ViewSwitchHelper;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;

/**
 由Harreke于2016/1/12创建
 */
@InjectLayout
@InjectToolbar
public class ViewAnimatorActivity extends ActivityFramework {
    @InjectView
    View button_play1;
    @InjectView
    View button_play1_reverse;
    @InjectView
    View button_play2;
    @InjectView
    View button_play2_reverse;
    @InjectView
    View button_play3;
    @InjectView
    View button_play3_reverse;
    ViewAnimator mAnimator1;
    ViewAnimator mAnimator2;
    ViewAnimator mAnimator3;
    ViewSwitchHelper mSwitch1;
    ViewSwitchHelper mSwitch2;
    ViewSwitchHelper mSwitch3;
    @InjectView
    View root;
    @InjectView
    View view_play1;
    @InjectView
    View view_play2;
    @InjectView
    View view_play3;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        mAnimator1 = ViewAnimator.animate(view_play1);
        mSwitch1 = new ViewSwitchHelper(button_play1, button_play1_reverse);
        mAnimator2 = ViewAnimator.animate(view_play2);
        mSwitch2 = new ViewSwitchHelper(button_play2, button_play2_reverse);
        mAnimator3 = ViewAnimator.animate(view_play3);
        mSwitch3 = new ViewSwitchHelper(button_play3, button_play3_reverse);


        mAnimator2.visibilityEnd(View.INVISIBLE).visibilityReverseEnd(View.VISIBLE);
        mAnimator3.backgroundColor(Color.BLACK).textColor(Color.WHITE);
    }

    @Override
    public void launch() {
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public void onPlay1Click(View view) {
        mAnimator1.height(root.getMeasuredHeight() / 2).size(root.getMeasuredWidth(), root.getMeasuredWidth()).rotation(90).start(true);
        mSwitch1.switchToView(button_play1_reverse);
    }

    public void onPlay1ReverseClick(View view) {
        mAnimator1.reverse(true);
        mSwitch1.switchToView(button_play1);
    }

    public void onPlay2Click(View view) {
        mAnimator2.start(true);
        mSwitch2.switchToView(button_play2_reverse);
    }

    public void onPlay2ReverseClick(View view) {
        mAnimator2.reverse(true);
        mSwitch2.switchToView(button_play2);
    }

    public void onPlay3Click(View view) {
        mAnimator3.start(true);
        mSwitch3.switchToView(button_play3_reverse);
    }

    public void onPlay3ReverseClick(View view) {
        mAnimator3.reverse(true);
        mSwitch3.switchToView(button_play3);
    }
}