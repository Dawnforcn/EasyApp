package com.harreke.easyapp.app;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.harreke.easyapp.common.interf.IDestroyable;

/**
 由huoqisheng于2016/7/15创建
 */
public class MeasureContent extends CoordinatorLayout implements IDestroyable, Runnable {
    private boolean mMeasured = false;
    private OnMeasuredListener mOnMeasuredListener = null;

    public MeasureContent(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void destroy() {
        mMeasured = false;
        mOnMeasuredListener = null;
    }

    public boolean isMeasured() {
        return mMeasured;
    }

    public void measure() {
        post(this);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !mMeasured || super.onInterceptTouchEvent(ev);
    }

    @Override
    public void run() {
        mMeasured = true;
        if (mOnMeasuredListener != null) {
            mOnMeasuredListener.onMeasured();
        }
    }

    public void setOnMeasuredListener(OnMeasuredListener onMeasuredListener) {
        mOnMeasuredListener = onMeasuredListener;
    }

    public interface OnMeasuredListener {
        void onMeasured();
    }
}