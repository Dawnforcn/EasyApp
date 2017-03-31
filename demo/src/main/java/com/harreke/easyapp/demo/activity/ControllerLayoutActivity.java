package com.harreke.easyapp.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.controllerlayout.ControllerLayout;
import com.harreke.easyapp.demo.controller.BottomBarLandscape;
import com.harreke.easyapp.demo.controller.BottomBarPortrait;
import com.harreke.easyapp.demo.controller.LockLandScape;
import com.harreke.easyapp.demo.controller.SettingExpandLandScape;
import com.harreke.easyapp.demo.controller.TopBarLandScape;
import com.harreke.easyapp.demo.controller.TopBarPortrait;
import com.harreke.easyapp.demo.controller.UnlockLandScape;
import com.harreke.easyapp.helper.GestureHelper;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.util.IntentUtil;
import com.harreke.easyapp.util.ViewUtil;

/**
 Created by Harreke on 2015/12/23.
 */
@InjectLayout
@InjectToolbar
public class ControllerLayoutActivity extends ActivityFramework {
    @InjectView
    ControllerLayout controller;
    @InjectView
    ImageView room_player_image;
    @InjectView
    View room_player_root;
    private BottomBarLandscape mBottomBarLandscape;
    private BottomBarPortrait mBottomBarPortrait;
    private GestureHelper.OnGestureListener mControllerGestureListener;
    private LockLandScape mLockLandScape;
    private boolean mRequestExitFullScreen = false;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        private int mLastHeight = 0;

        @Override
        public void onGlobalLayout() {
            Rect rect;
            int rootInvisibleHeight;

            rect = new Rect();
            controller.getWindowVisibleDisplayFrame(rect);
            rootInvisibleHeight = controller.getRootView().getHeight() - rect.bottom;

            if (mBottomBarLandscape != null) {
                mBottomBarLandscape.fixPaddingBottom(rootInvisibleHeight);
                if (mBottomBarLandscape.isShowing()) {
                    mBottomBarLandscape.show(false);
                }
            }
            if (rootInvisibleHeight == 0 && mRequestExitFullScreen) {
                mRequestExitFullScreen = false;
                exitFullScreen();
            }
        }
    };
    private float mScale = 1f;
    private SettingExpandLandScape mSettingExpandLandScape;
    private TopBarLandScape mTopBarLandScape;
    private TopBarPortrait mTopBarPortrait;
    private UnlockLandScape mUnlockLandScape;

    public static Intent create(@NonNull Context context) {
        return IntentUtil.create(context, ControllerLayoutActivity.class);
    }

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        registerDestroyable(controller);

        controller.setAutoHideDuration(6000L);
        mTopBarLandScape = new TopBarLandScapeImpl(controller);
        mBottomBarLandscape = new BottomBarLandscapeImpl(controller);
        mTopBarPortrait = new TopBarPortraitImpl(controller);
        mBottomBarPortrait = new BottomBarPortraitImpl(controller);
        mLockLandScape = new LockLandScapeImpl(controller);
        mUnlockLandScape = new UnlockLandScapeImpl(controller);
        mSettingExpandLandScape = new SettingExpandLandScapeImpl(controller);
        controller.attachWidget("top_bar", mTopBarLandScape);
        controller.attachWidget("bottom_bar", mBottomBarLandscape);
        controller.attachWidget("top_simple_bar", mTopBarPortrait);
        controller.attachWidget("bottom_simple_bar", mBottomBarPortrait);
        controller.attachWidget("middle_lock", mLockLandScape);
        controller.attachWidget("middle_unlock", mUnlockLandScape);
        controller.attachWidget("middle_setting_expand", mSettingExpandLandScape);

        mControllerGestureListener = new GestureHelper.OnGestureListener() {
            @Override
            public boolean onDown(float downX, float downY) {
                mScale = 1f;

                return true;
            }

            @Override
            public boolean onPointerDown() {
                return true;
            }

            @Override
            public boolean onPointerUp() {
                return true;
            }

            @Override
            public boolean onScale(float scale, float scaleX, float scaleY, long duration) {
                mScale = scale;

                return true;
            }

            @Override
            public boolean onScroll(float scrollX, float scrollY, long duration) {
                return false;
            }

            @Override
            public void onTap(float x, float y, int tapCount) {
                if (mBottomBarLandscape.isSoftInputShowing()) {
                    mBottomBarLandscape.hideSoftInput();
                } else {
                    controller.toggle(true);
                }
            }

            @Override
            public boolean onUp() {
                if (mScale > 1f) {
                    if (!controller.isFullScreen()) {
                        enterFullScreen();

                        return true;
                    }
                } else if (mScale < 1f) {
                    if (controller.isFullScreen() && !controller.isLocked()) {
                        if (mBottomBarLandscape.isSoftInputShowing()) {
                            mBottomBarLandscape.hideSoftInput();
                            mRequestExitFullScreen = true;
                        } else {
                            exitFullScreen();
                        }

                        return true;
                    }
                }

                return false;
            }
        };
        controller.setOnGestureListener(mControllerGestureListener);
    }

    private void enterFullScreen() {
        controller.setFullScreen(true);
        requestLandScape();
    }

    private void exitFullScreen() {
        controller.setFullScreen(false);
        requestPortrait();
    }

    @Override
    public void launch() {
    }

    @Override
    public void onBackPressed() {
        if (controller.isFullScreen()) {
            exitFullScreen();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (controller.isFullScreen()) {
            ViewUtil.setHeight(room_player_root, ViewGroup.LayoutParams.MATCH_PARENT);
            ViewUtil.requestFullScreen(this);
        } else {
            ViewUtil.setHeight(room_player_root, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewUtil.requestNonFullScreen(this);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
    }

    private void requestLandScape() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    private void requestPortrait() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private class BottomBarLandscapeImpl extends BottomBarLandscape {
        public BottomBarLandscapeImpl(@NonNull ControllerLayout controllerLayout) {
            super(controllerLayout);
        }

        @Override
        protected void bottom_bar_fullscreen_exit() {
            if (controller.isFullScreen()) {
                exitFullScreen();
            }
        }

        @Override
        protected void bottom_bar_pause() {
            controller.scheduleAutoHide();
            showPlay();
        }

        @Override
        protected void bottom_bar_play() {
            controller.scheduleAutoHide();
            showPause();
        }

        @Override
        protected void bottom_bar_send() {
            controller.scheduleAutoHide();
            showToast("点击了发送");
        }

        @Override
        public void onHideSoftInput(EditText editText) {
            ViewUtil.requestFullScreen(getActivity());
            controller.scheduleAutoHide();
            controller.showExcept(this, true);
        }

        @Override
        public void onShowSoftInput(EditText editText) {
            ViewUtil.requestNonFullScreen(getActivity());
            controller.cancelAutoHide();
            controller.hideExcept(this, true);
        }
    }

    private class BottomBarPortraitImpl extends BottomBarPortrait {
        public BottomBarPortraitImpl(@NonNull ControllerLayout controllerLayout) {
            super(controllerLayout);
        }

        @Override
        protected void bottom_simple_bar_fullscreen_enter() {
            if (!controller.isFullScreen()) {
                enterFullScreen();
            }
        }

        @Override
        protected void bottom_simple_bar_pause() {
            controller.scheduleAutoHide();
            showPlay();
        }

        @Override
        protected void bottom_simple_bar_play() {
            controller.scheduleAutoHide();
            showPause();
        }

        @Override
        public void onShowStatusChanged(boolean isShowing) {
            if (isShowing) {
                showToolbar();
            } else {
                hideToolbar();
            }
        }
    }

    private class LockLandScapeImpl extends LockLandScape {
        public LockLandScapeImpl(@NonNull ControllerLayout controllerLayout) {
            super(controllerLayout);
        }

        @Override
        protected void middle_lock() {
            controller.scheduleAutoHide();
            if (!controller.isLocked()) {
                controller.setLocked(true);
            }
        }
    }

    private class SettingExpandLandScapeImpl extends SettingExpandLandScape {
        public SettingExpandLandScapeImpl(@NonNull ControllerLayout controllerLayout) {
            super(controllerLayout);
        }
    }

    private class TopBarLandScapeImpl extends TopBarLandScape {
        public TopBarLandScapeImpl(@NonNull ControllerLayout controllerLayout) {
            super(controllerLayout);
        }

        @Override
        protected void top_bar_back() {
            onBackPressed();
        }

        @Override
        protected void top_bar_setting() {
            controller.cancelAutoHide();
            controller.hide(true);
            mSettingExpandLandScape.show(true);
        }
    }

    private class TopBarPortraitImpl extends TopBarPortrait {
        public TopBarPortraitImpl(@NonNull ControllerLayout controllerLayout) {
            super(controllerLayout);
        }

        @Override
        protected void top_simple_bar_back() {
            onBackPressed();
        }

        @Override
        protected void top_simple_bar_share() {
            controller.scheduleAutoHide();
            showToast("点击了分享");
        }
    }

    private class UnlockLandScapeImpl extends UnlockLandScape {
        public UnlockLandScapeImpl(@NonNull ControllerLayout controllerLayout) {
            super(controllerLayout);
        }

        @Override
        protected void middle_unlock() {
            controller.scheduleAutoHide();
            if (controller.isLocked()) {
                controller.setLocked(false);
            }
        }
    }
}