package com.harreke.easyapp.common.toaster;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.harreke.easyapp.common.R;

import java.lang.ref.WeakReference;

/**
 由huoqisheng于2016/8/29创建
 */
public class ToastHolder implements Runnable {
    private WeakReference<Activity> mActivityRef;
    private boolean mAttached = false;
    private WindowManager.LayoutParams mToastParams;
    private ProgressBar toast_icon;
    private View toast_root;
    private TextView toast_text;

    ToastHolder(@NonNull Activity activity) {
        mActivityRef = new WeakReference<>(activity);
        toast_root = LayoutInflater.from(activity).inflate(R.layout.toaster, null, false);
        toast_icon = (ProgressBar) toast_root.findViewById(R.id.toast_icon);
        toast_text = (TextView) toast_root.findViewById(R.id.toast_text);
        mToastParams = new WindowManager.LayoutParams();
        mToastParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mToastParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mToastParams.gravity = Gravity.CENTER;
        mToastParams.y = (int) (activity.getResources().getDisplayMetrics().density * 64);
        mToastParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mToastParams.format = PixelFormat.TRANSLUCENT;
        mToastParams.windowAnimations = android.R.style.Animation_Toast;
        mToastParams.type = WindowManager.LayoutParams.TYPE_TOAST;
    }

    public void destroy() {
        mAttached = false;
        if (mActivityRef != null) {
            mActivityRef.clear();
            mActivityRef = null;
        }
        toast_text = null;
        toast_root = null;
    }

    private Activity getActivity() {
        return mActivityRef == null ? null : mActivityRef.get();
    }

    void hide() {
        Activity activity = getActivity();
        if (activity != null && toast_root != null && mAttached) {
            mAttached = false;
            activity.getWindowManager().removeView(toast_root);
        }
    }

    public void run() {
        hide();
    }

    void show(int textId, boolean indeterminate) {
        Activity activity = getActivity();
        if (activity != null && toast_root != null && !mAttached) {
            mAttached = true;
            activity.getWindowManager().addView(toast_root, mToastParams);
        }
        if (toast_icon != null) {
            if (indeterminate) {
                toast_icon.setVisibility(View.VISIBLE);
            } else {
                toast_icon.setVisibility(View.GONE);
            }
        }
        if (toast_text != null) {
            toast_text.setText(textId);
        }
    }

    void show(String text, boolean indeterminate) {
        Activity activity = getActivity();
        if (activity != null && toast_root != null && !mAttached) {
            mAttached = true;
            activity.getWindowManager().addView(toast_root, mToastParams);
        }
        if (toast_icon != null) {
            if (indeterminate) {
                toast_icon.setVisibility(View.VISIBLE);
            } else {
                toast_icon.setVisibility(View.GONE);
            }
        }
        if (toast_text != null) {
            toast_text.setText(text);
        }
    }
}