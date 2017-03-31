package com.harreke.easyapp.controllerlayout;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.harreke.easyapp.animator.viewanimator.ViewAnimator;
import com.harreke.easyapp.injection.Injection;

/**
 由Harreke于2015/9/10创建
 */
public abstract class ControllerWidget implements IControllerWidget {
    private ViewAnimator mAnimator;
    private AppearanceAnimation mAppearanceAnimation = AppearanceAnimation.Slide;
    /**
     是否自动响应显示/隐藏

     注：自动响应显示/隐藏的意思为接受并执行ControllerLayout的全局命令{@link ControllerLayout#show(boolean)}或{@link ControllerLayout#hide(boolean)}

     若允许自动相应，ControllerLayout广播{@link ControllerLayout#hide(boolean)}命令时，该控件会自动执行显示/隐藏操作
     若禁止自动响应，则则必须由代码调用该控件的{@link #show(boolean)}或{@link #hide(boolean)}来手动显示/隐藏
     */
    private AutoResponse mAutoResponse = AutoResponse.Show_Hide;
    private ControllerLayout mContainer = null;
    private boolean mEnabled = true;
    /**
     是否在全屏模式下显示
     */
    private FullScreenResponse mFullScreenResponse = FullScreenResponse.FullScreen;
    private int mGravity;
    /**
     是否响应屏幕锁定
     */
    private LockResponse mLockResponse = LockResponse.Unlock;
    private Rect mMarginRect = new Rect();
    private Rect mPaddingFixRect = new Rect(0, 0, 0, 0);
    private boolean mShowing = true;
    /**
     控制控件的视图
     */
    private View mView;
    private Runnable mMeasureRunnable = new Runnable() {
        @Override
        public void run() {
            onPrepared();
        }
    };

    public ControllerWidget(@NonNull ControllerLayout controllerLayout, int layoutId) {
        FrameLayout.LayoutParams params;

        mView = LayoutInflater.from(controllerLayout.getContext()).inflate(layoutId, controllerLayout, false);
        Injection.inject(this, mView);
        params = (FrameLayout.LayoutParams) mView.getLayoutParams();
        mGravity = params.gravity;
        mMarginRect.set(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin);
        mView.setVisibility(View.INVISIBLE);
        mAnimator = ViewAnimator.animate(mView);
    }

    public final void fixPadding(int left, int top, int right, int bottom) {
        mPaddingFixRect.set(left, top, right, bottom);
    }

    public final void fixPaddingBottom(int bottom) {
        mPaddingFixRect.bottom = bottom;
    }

    public final void fixPaddingLeft(int left) {
        mPaddingFixRect.left = left;
    }

    public final void fixPaddingRight(int right) {
        mPaddingFixRect.right = right;
    }

    public final void fixPaddingTop(int top) {
        mPaddingFixRect.top = top;
    }

    @Override
    public final ViewAnimator getAnimator() {
        return mAnimator;
    }

    @NonNull
    @Override
    public final AppearanceAnimation getAppearanceAnimation() {
        return mAppearanceAnimation;
    }

    @NonNull
    @Override
    public final AutoResponse getAutoResponse() {
        return mAutoResponse;
    }

    @Nullable
    @Override
    public ControllerLayout getContainer() {
        return mContainer;
    }

    public final Context getContext() {
        return mView.getContext();
    }

    @NonNull
    @Override
    public final FullScreenResponse getFullScreenResponse() {
        return mFullScreenResponse;
    }

    @NonNull
    @Override
    public final LockResponse getLockResponse() {
        return mLockResponse;
    }

    @NonNull
    @Override
    public View getView() {
        return mView;
    }

    @Override
    public void hide(boolean animate) {
        if (mEnabled) {
            if (mContainer != null) {
                mShowing = false;
                onShowStatusChanged(false);
                setAnimator();
                mAnimator.reverse(animate);
            }
        }
    }

    @Override
    public final boolean isEnabled() {
        return mEnabled;
    }

    @Override
    public final boolean isShowing() {
        return mShowing;
    }

    @Override
    public void onAttached() {
        mContainer = (ControllerLayout) mView.getParent();
        mView.removeCallbacks(mMeasureRunnable);
        mView.post(mMeasureRunnable);
    }

    @Override
    public void onDetached() {
        mContainer = null;
        mView.removeCallbacks(mMeasureRunnable);
    }

    @Override
    public void onPrepared() {
        hide(false);
    }

    @Override
    public void onShowStatusChanged(boolean isShowing) {
    }

    private void setAnimator() {
        int viewWidth = mView.getMeasuredWidth();
        int viewHeight = mView.getMeasuredHeight();
        int containerWidth = mContainer.getMeasuredWidth();
        int containerHeight = mContainer.getMeasuredHeight();
        int horizontalGravity = mGravity & Gravity.HORIZONTAL_GRAVITY_MASK;
        int verticalGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;

        mAnimator.clear();
        switch (mAppearanceAnimation) {
            case Alpha:
                mAnimator.alphaStart(0f).alphaEnd(1f);
                break;
            case Slide:
                if (horizontalGravity == Gravity.CENTER && verticalGravity == Gravity.CENTER) {
                    mAnimator.yStart(-viewHeight).yEnd(mMarginRect.top + mPaddingFixRect.top + (containerHeight - viewHeight) / 2);
                } else if (horizontalGravity == Gravity.NO_GRAVITY || horizontalGravity == Gravity.LEFT) {
                    mAnimator.xStart(-viewWidth).xEnd(mMarginRect.left + mPaddingFixRect.left);
                } else if (horizontalGravity == Gravity.RIGHT) {
                    mAnimator.xStart(containerWidth).xEnd(containerWidth - mMarginRect.right - mPaddingFixRect.right - viewWidth);
                } else if (verticalGravity == Gravity.NO_GRAVITY || verticalGravity == Gravity.TOP) {
                    mAnimator.yStart(-viewHeight).yEnd(mMarginRect.top + mPaddingFixRect.top);
                } else if (verticalGravity == Gravity.BOTTOM) {
                    mAnimator.yStart(containerHeight).yEnd(containerHeight - mMarginRect.bottom - mPaddingFixRect.bottom - viewHeight);
                }
                break;
        }
        mAnimator.visibilityStart(View.VISIBLE).visibilityReverseEnd(View.INVISIBLE);
    }

    public final void setAppearanceAnimation(@NonNull AppearanceAnimation appearanceAnimation) {
        mAppearanceAnimation = appearanceAnimation;
    }

    public final void setAutoResponse(@NonNull AutoResponse autoResponse) {
        mAutoResponse = autoResponse;
    }

    @Override
    public final void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    public final void setFullScreenResponse(@NonNull FullScreenResponse fullScreenResponse) {
        mFullScreenResponse = fullScreenResponse;
    }

    public final void setLockResponse(@NonNull LockResponse lockResponse) {
        mLockResponse = lockResponse;
    }

    @Override
    public void show(boolean animate) {
        if (mEnabled) {
            if (mContainer != null) {
                mShowing = true;
                onShowStatusChanged(true);
                setAnimator();
                mAnimator.start(animate);
            }
        }
    }
}