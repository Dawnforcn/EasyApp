package com.harreke.easyapp.controllerlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.harreke.easyapp.helper.GestureHelper;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

/**
 Created by Harreke on 2015/9/10.

 控制布局

 控制布局是一个容器，内部可以挂载多个控制控件{@link IControllerWidget}，每个控件可以单独实现一种简单的播放器操作

 控制布局本身可以实现手势操作的监听，通过统一接口管理所有控件
 */
public class ControllerLayout extends FrameLayout implements IControllerLayout {
    private long mAutoHideDuration = 0L;
    private boolean mFullScreen = false;
    private boolean mGestureEnabled = true;
    private GestureHelper mGestureHelper = null;
    private boolean mLocked = false;
    private HashMap<String, IControllerWidget> mWidgetMap = new HashMap<>();
    private Runnable mAutoHideRunnable = new Runnable() {
        @Override
        public void run() {
            Logger.e("auto hide");
            hide(true);
        }
    };

    public ControllerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     {@inheritDoc}
     */
    public void attachWidget(@NonNull String type, @NonNull IControllerWidget controllerWidget) {
        View view;

        if (isWidgetAttached(type)) {
            detachWidget(type);
        }
        mWidgetMap.put(type, controllerWidget);
        view = controllerWidget.getView();
        addView(view);
        controllerWidget.onAttached();
    }

    public void cancelAutoHide() {
        removeCallbacks(mAutoHideRunnable);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void destroy() {
        removeCallbacks(mAutoHideRunnable);
        mWidgetMap.clear();
        removeAllViews();
    }

    public void detachWidget(@NonNull String type) {
        IControllerWidget controllerWidget = mWidgetMap.get(type);

        if (controllerWidget != null) {
            controllerWidget.onDetached();
            removeView(controllerWidget.getView());
        }
        mWidgetMap.remove(type);
    }

    public IControllerWidget getWidget(@NonNull String type) {
        return mWidgetMap.get(type);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void hide(boolean animate) {
        for (IControllerWidget controllerWidget : mWidgetMap.values()) {
            if (controllerWidget != null) {
                ResponseUtil.checkHideLocked(isLocked(), isFullScreen(), controllerWidget, animate);
            }
        }
    }

    public void hide(@NonNull String tag, boolean animate) {
        hide(tag, animate, false);
    }

    public void hide(@NonNull IControllerWidget controllerWidget, boolean animate) {
        ResponseUtil.checkHideLocked(isLocked(), isFullScreen(), controllerWidget, animate);
    }

    public void hide(@NonNull IControllerWidget controllerWidget, boolean animate, boolean forceHide) {
        if (forceHide) {
            controllerWidget.hide(animate);
        } else {
            hide(controllerWidget, animate);
        }
    }

    public void hide(@NonNull String tag, boolean animate, boolean forceHide) {
        IControllerWidget controllerWidget = mWidgetMap.get(tag);
        if (controllerWidget != null) {
            hide(controllerWidget, animate, forceHide);
        }
    }

    public void hideExcept(@NonNull String tag, boolean animate) {
        IControllerWidget target = mWidgetMap.get(tag);

        if (target == null) {
            hide(animate);
        } else {
            hideExcept(target, animate);
        }
    }

    public void hideExcept(@NonNull IControllerWidget target, boolean animate) {
        for (IControllerWidget controllerWidget : mWidgetMap.values()) {
            if (controllerWidget != null && target != controllerWidget) {
                ResponseUtil.checkHideLocked(isLocked(), isFullScreen(), controllerWidget, animate);
            }
        }
    }

    public boolean isFullScreen() {
        return mFullScreen;
    }

    public boolean isGestureEnabled() {
        return mGestureEnabled;
    }

    public boolean isLocked() {
        return mLocked;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public boolean isShowing() {
        for (IControllerWidget controllerWidget : mWidgetMap.values()) {
            if (controllerWidget != null) {
                if (ResponseUtil.isShowingLocked(isLocked(), isFullScreen(), controllerWidget, animate())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isVisible() {
        return getVisibility() == VISIBLE;
    }

    public boolean isWidgetAttached(@NonNull String type) {
        return mWidgetMap.containsKey(type);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (mGestureEnabled && mGestureHelper != null) {
            return mGestureHelper.onTouch(event);
        } else {
            return super.onTouchEvent(event);
        }
    }

    public void refresh(boolean animate) {
        hide(animate);
        show(animate);
    }

    public void scheduleAutoHide() {
        cancelAutoHide();
        if (mAutoHideDuration > 0) {
            postDelayed(mAutoHideRunnable, mAutoHideDuration);
        }
    }

    public void setAutoHideDuration(long autoHideDuration) {
        mAutoHideDuration = autoHideDuration;
    }

    public void setFullScreen(boolean fullScreen) {
        if (mFullScreen != fullScreen) {
            hide(false);
            mFullScreen = fullScreen;
        }
    }

    /**
     设置是否开启手势监听功能

     注：必须为该布局设置一个手势监听器

     @param gestureEnabled 是否开启手势监听功能
     */
    public void setGestureEnabled(boolean gestureEnabled) {
        mGestureEnabled = gestureEnabled;
    }

    public void setLocked(boolean locked) {
        hide(true);
        mLocked = locked;
        show(true);
    }

    /**
     设置手势监听器

     @param onGestureListener 手势监听器
     */
    public void setOnGestureListener(GestureHelper.OnGestureListener onGestureListener) {
        mGestureHelper = new GestureHelper(onGestureListener);
        mGestureHelper.setPointerEnabled(true);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void show(boolean animate) {
        for (IControllerWidget controllerWidget : mWidgetMap.values()) {
            if (controllerWidget != null) {
                ResponseUtil.checkShowLocked(isLocked(), isFullScreen(), controllerWidget, animate);
            }
        }
        scheduleAutoHide();
    }

    public void show(@NonNull String tag, boolean animate) {
        show(tag, animate, false);
    }

    public void show(@NonNull IControllerWidget controllerWidget, boolean animate) {
        ResponseUtil.checkShowLocked(isLocked(), isFullScreen(), controllerWidget, animate);
    }

    public void show(@NonNull IControllerWidget controllerWidget, boolean animate, boolean forceShow) {
        if (forceShow) {
            controllerWidget.show(animate);
        } else {
            show(controllerWidget, animate);
        }
    }

    public void show(@NonNull String tag, boolean animate, boolean forceShow) {
        IControllerWidget controllerWidget = mWidgetMap.get(tag);
        if (controllerWidget != null) {
            show(controllerWidget, animate, forceShow);
        }
    }

    public void showExcept(@NonNull String tag, boolean animate) {
        IControllerWidget target = mWidgetMap.get(tag);
        if (target == null) {
            show(animate);
        } else {
            showExcept(target, animate);
        }
    }

    public void showExcept(@NonNull IControllerWidget target, boolean animate) {
        for (IControllerWidget controllerWidget : mWidgetMap.values()) {
            if (controllerWidget != null && target != controllerWidget) {
                ResponseUtil.checkShowLocked(isLocked(), isFullScreen(), controllerWidget, animate);
            }
        }
        scheduleAutoHide();
    }

    @Override
    public void toggle(boolean animate) {
        if (isShowing()) {
            hide(animate);
        } else {
            show(animate);
        }
    }
}