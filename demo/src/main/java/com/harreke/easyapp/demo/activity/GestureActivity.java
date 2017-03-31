package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.helper.GestureHelper;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;

import java.util.Locale;

/**
 由 Harreke 于 2016/1/12 创建
 */
@InjectLayout
@InjectToolbar
public class GestureActivity extends ActivityFramework implements View.OnTouchListener, GestureHelper.OnGestureListener {
    @InjectView
    View gesture_area;
    @InjectView
    TextView gesture_toast;
    private GestureHelper mGestureHelper;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        mGestureHelper = new GestureHelper(this);
        mGestureHelper.setPointerEnabled(true);
        gesture_area.setOnTouchListener(this);
    }

    @Override
    public void launch() {
    }

    @Override
    public boolean onDown(float downX, float downY) {
        setText("按下");

        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onPointerDown() {
        setText("按下第二点");

        return false;
    }

    @Override
    public boolean onPointerUp() {
        setText("抬起第二点");

        return false;
    }

    @Override
    public boolean onScale(float scale, float scaleX, float scaleY, long duration) {
        setText("缩放");

        return false;
    }

    @Override
    public boolean onScroll(float scrollX, float scrollY, long duration) {
        setText("滑动");

        return false;
    }

    @Override
    public void onTap(float x, float y, int tapCount) {
        setText(String.format(Locale.getDefault(), "单击%d次", tapCount));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureHelper.onTouch(event);
    }

    @Override
    public boolean onUp() {
        setText("抬起");

        return false;
    }

    private void setText(String text) {
        gesture_toast.setText(String.format("当前手势：%s", text));
    }
}