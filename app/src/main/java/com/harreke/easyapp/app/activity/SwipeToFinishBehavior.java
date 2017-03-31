package com.harreke.easyapp.app.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.harreke.easyapp.animator.viewanimator.ViewAnimator;
import com.harreke.easyapp.util.MetricUtil;

import java.util.HashMap;
import java.util.Map;

/**
 由Harreke于2016/1/4创建
 */
public class SwipeToFinishBehavior extends CoordinatorLayout.Behavior {
    private final float mTouchDownThreshold = MetricUtil.Density * 16;
    private final float mTouchThreshold = MetricUtil.TouchSlop;
    private boolean mAnimating = false;
    private Map<View, ViewAnimator> mAnimatorMap = new HashMap<>();
    private float mLastTouchX = 0f;
    private OnFinishListener mOnFinishListener = null;
    private Animator.AnimatorListener mAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationCancel(Animator animation) {
            View view = ((ViewAnimator) animation).getView();

            animation.removeListener(this);
            mAnimating = false;
            mAnimatorMap.remove(view);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            View view = ((ViewAnimator) animation).getView();

            animation.removeListener(this);
            mAnimating = false;
            mAnimatorMap.remove(view);
            if (mOnFinishListener != null) {
                mOnFinishListener.onFinish(view);
            }
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }

        @Override
        public void onAnimationStart(Animator animation) {
            mAnimating = true;
        }
    };
    private boolean mShouldIntercept = false;
    private float mSwipeOffset = 0f;

    private void animateBack(View view) {
        ViewAnimator animator = getAnimator(view);

        animator.x(0f).start();
    }

    private void animateFinish(View view, int width) {
        ViewAnimator animator = getAnimator(view);

        animator.addListener(mAnimatorListener);
        animator.x(width).start();
    }

    private ViewAnimator getAnimator(View view) {
        ViewAnimator animator = mAnimatorMap.get(view);

        if (animator == null) {
            animator = ViewAnimator.animate(view);
            mAnimatorMap.put(view, animator);
        } else {
            animator.removeListener(mAnimatorListener);
        }

        return animator;
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
        float dx;
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
                mLastTouchX = event.getX();
                mShouldIntercept = (mLastTouchX <= mTouchDownThreshold);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mShouldIntercept) {
                    dx = event.getX() - mLastTouchX;

                    return dx > mTouchThreshold || (dx < -mTouchThreshold && mSwipeOffset > 0);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
        float touchX;
        float dx;
        int width;
        int swipeThreshold;

        if (mShouldIntercept && !mAnimating) {
            width = child.getMeasuredWidth();
            swipeThreshold = width / 3;
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    touchX = event.getX();
                    dx = touchX - mLastTouchX;
                    mLastTouchX = touchX;

                    mSwipeOffset += dx;
                    if (mSwipeOffset < 0f) {
                        mSwipeOffset = 0f;
                    } else if (mSwipeOffset > width) {
                        mSwipeOffset = width;
                    }
                    parent.setBackgroundColor(Color.argb((int) (255f * (1 - mSwipeOffset / parent.getMeasuredWidth())), 0, 0, 0));
                    child.setX(mSwipeOffset);

                    return true;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (mSwipeOffset > swipeThreshold) {
                        mSwipeOffset = 0;
                        animateFinish(child, width);
                    } else {
                        mSwipeOffset = 0;
                        animateBack(child);
                    }

                    return true;
            }
        }

        return false;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        mOnFinishListener = onFinishListener;
    }

    public interface OnFinishListener {
        void onFinish(View view);
    }
}