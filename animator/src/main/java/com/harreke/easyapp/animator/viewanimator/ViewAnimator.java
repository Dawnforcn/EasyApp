package com.harreke.easyapp.animator.viewanimator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import com.harreke.easyapp.util.ColorUtil;
import com.harreke.easyapp.util.ViewInfo;
import com.harreke.easyapp.util.ViewUtil;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/01/26
 */
public class ViewAnimator extends ValueAnimator implements IViewProperty, IStateViewProperty {
    private static WeakHashMap<View, ViewAnimator> mAnimatorMap = new WeakHashMap<>();
    private boolean mDebug = false;
    private long mDuration = 0L;
    private List<Runnable> mEndActionList = new ArrayList<>();
    private Map<String, Object> mEndMap = new HashMap<>();
    private boolean mForward = false;
    private List<Runnable> mStartActionList = new ArrayList<>();
    private Map<String, Object> mStartMap = new HashMap<>();
    private WeakReference<View> mViewRef;
    @SuppressWarnings("FieldCanBeLocal")
    private AnimatorListener mInnerListener = new AnimatorListener() {
        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @SuppressWarnings("ResourceType")
        @Override
        public void onAnimationEnd(Animator animation) {
            View view = getView();

            if (view != null) {
                if (mForward) {
                    ViewAnimatorUtil.set(view, mEndMap, mDebug);
                    if (ViewAnimatorUtil.containsVisibility(mEndMap)) {
                        if (mDebug) {
                            Logger.e("Animator Log:\nVisibility set from " + ViewAnimatorUtil.getVisibility(view) + " to " +
                                    ViewAnimatorUtil.getVisibility(mEndMap));
                        }
                        //noinspection ResourceType
                        view.setVisibility(ViewAnimatorUtil.getVisibility(mEndMap));
                    }
                    for (Runnable runnable : mEndActionList) {
                        runnable.run();
                    }
                } else {
                    ViewAnimatorUtil.set(view, mStartMap, mDebug);
                    if (ViewAnimatorUtil.containsVisibilityReverse(mEndMap)) {
                        if (mDebug) {
                            Logger.e("Animator Log:\nVisibility set from " + ViewAnimatorUtil.getVisibility(view) + " to " +
                                    ViewAnimatorUtil.getVisibilityReverse(mEndMap));
                        }
                        //noinspection ResourceType
                        view.setVisibility(ViewAnimatorUtil.getVisibilityReverse(mEndMap));
                    }
                    for (Runnable runnable : mStartActionList) {
                        runnable.run();
                    }
                }
                resetDuration();
            }
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }

        @Override
        public void onAnimationStart(Animator animation) {
            View view = getView();

            if (view != null) {
                if (mForward) {
                    ViewAnimatorUtil.set(view, mStartMap, mDebug);
                    if (ViewAnimatorUtil.containsVisibility(mStartMap)) {
                        if (mDebug) {
                            Logger.e("Animator Log:\nVisibility set from " + ViewAnimatorUtil.getVisibility(view) + " to " +
                                    ViewAnimatorUtil.getVisibility(mStartMap));
                        }
                        //noinspection ResourceType
                        view.setVisibility(ViewAnimatorUtil.getVisibility(mStartMap));
                    }
                    for (Runnable runnable : mStartActionList) {
                        runnable.run();
                    }
                } else {
                    ViewAnimatorUtil.set(view, mEndMap, mDebug);
                    if (ViewAnimatorUtil.containsVisibilityReverse(mStartMap)) {
                        if (mDebug) {
                            Logger.e("Animator Log:\nVisibility set from " + ViewAnimatorUtil.getVisibility(view) + " to " +
                                    ViewAnimatorUtil.getVisibilityReverse(mStartMap));
                        }
                        //noinspection ResourceType
                        view.setVisibility(ViewAnimatorUtil.getVisibilityReverse(mStartMap));
                    }
                    for (Runnable runnable : mEndActionList) {
                        runnable.run();
                    }
                }
            }
        }
    };
    @SuppressWarnings("FieldCanBeLocal")
    private AnimatorUpdateListener mInnerUpdateListener = new AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            View view = getView();

            if (view != null) {
                ViewAnimatorUtil.update(view, animation);
            } else {
                clear();
            }
        }
    };

    private ViewAnimator(@NonNull View view) {
        mViewRef = new WeakReference<>(view);
        addListener(mInnerListener);
        addUpdateListener(mInnerUpdateListener);
    }

    public static ViewAnimator animate(@NonNull View view) {
        ViewAnimator animator = mAnimatorMap.get(view);

        if (animator == null) {
            animator = new ViewAnimator(view);
            mAnimatorMap.put(view, animator);
        }

        return animator;
    }

    public ViewAnimator addEndAction(@NonNull Runnable endAction) {
        mEndActionList.add(endAction);

        return this;
    }

    public ViewAnimator addStartAction(@NonNull Runnable startAction) {
        mStartActionList.add(startAction);

        return this;
    }

    @Override
    public ViewAnimator alpha(float alpha) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setAlpha(mStartMap, view.getAlpha());
            ViewAnimatorUtil.setAlpha(mEndMap, alpha);
        }

        return this;
    }

    @Override
    public ViewAnimator alpha(@NonNull View view) {
        return alpha(view.getAlpha());
    }

    @Override
    public ViewAnimator alphaBy(float alphaBy) {
        View view = getView();
        float alpha;

        if (view != null) {
            alpha = view.getAlpha();
            ViewAnimatorUtil.setAlpha(mStartMap, alpha);
            ViewAnimatorUtil.setAlpha(mEndMap, alpha + alphaBy);
        }

        return this;
    }

    @Override
    public ViewAnimator alphaEnd(float alpha) {
        ViewAnimatorUtil.setAlpha(mEndMap, alpha);

        return this;
    }

    @Override
    public ViewAnimator alphaEnd(@NonNull View view) {
        return alphaEnd(view.getAlpha());
    }

    @Override
    public ViewAnimator alphaStart(float alpha) {
        ViewAnimatorUtil.setAlpha(mStartMap, alpha);

        return this;
    }

    @Override
    public ViewAnimator alphaStart(@NonNull View view) {
        return alphaStart(view.getAlpha());
    }

    @Override
    public ViewAnimator backgroundColor(int backgroundColor) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setBackgroundColor(mStartMap, ViewUtil.getBackgroundColor(view));
            ViewAnimatorUtil.setBackgroundColor(mEndMap, backgroundColor);
        }

        return this;
    }

    @Override
    public ViewAnimator backgroundColor(@NonNull View view) {
        return backgroundColor(ViewUtil.getBackgroundColor(view));
    }

    @Override
    public ViewAnimator backgroundColorBy(int backgroundColorBy) {
        View view = getView();
        int backgroundColor;

        if (view != null) {
            backgroundColor = ViewUtil.getBackgroundColor(view);
            ViewAnimatorUtil.setBackgroundColor(mStartMap, backgroundColor);
            ViewAnimatorUtil.setBackgroundColor(mEndMap, ColorUtil.plus(backgroundColor, backgroundColorBy));
        }

        return this;
    }

    @Override
    public ViewAnimator backgroundColorEnd(int backgroundColor) {
        ViewAnimatorUtil.setBackgroundColor(mEndMap, backgroundColor);

        return this;
    }

    @Override
    public ViewAnimator backgroundColorEnd(@NonNull View view) {
        return backgroundColorEnd(ViewUtil.getBackgroundColor(view));
    }

    @Override
    public ViewAnimator backgroundColorStart(int backgroundColor) {
        ViewAnimatorUtil.setBackgroundColor(mStartMap, backgroundColor);

        return this;
    }

    @Override
    public ViewAnimator backgroundColorStart(@NonNull View view) {
        return backgroundColorStart(ViewUtil.getBackgroundColor(view));
    }

    public ViewAnimator clear() {
        cancel();
        resetDuration();
        mStartMap.clear();
        mEndMap.clear();
        mStartActionList.clear();
        mEndActionList.clear();

        return this;
    }

    @Override
    public ViewAnimator coordinate(float x, float y) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setX(mStartMap, view.getX());
            ViewAnimatorUtil.setY(mStartMap, view.getY());
            ViewAnimatorUtil.setX(mEndMap, x);
            ViewAnimatorUtil.setY(mEndMap, y);
        }

        return this;
    }

    @Override
    public ViewAnimator coordinate(@NonNull ViewInfo viewInfo) {
        return coordinate(viewInfo.x, viewInfo.y);
    }

    @Override
    public ViewAnimator coordinate(@NonNull View view) {
        return coordinate(view.getX(), view.getY());
    }

    @Override
    public ViewAnimator coordinateBy(float xBy, float yBy) {
        View view = getView();
        float x;
        float y;

        if (view != null) {
            x = view.getX();
            y = view.getY();
            ViewAnimatorUtil.setX(mStartMap, x);
            ViewAnimatorUtil.setY(mStartMap, y);
            ViewAnimatorUtil.setX(mEndMap, x + xBy);
            ViewAnimatorUtil.setY(mEndMap, y + yBy);
        }

        return this;
    }

    @Override
    public ViewAnimator coordinateEnd(float x, float y) {
        ViewAnimatorUtil.setX(mEndMap, x);
        ViewAnimatorUtil.setY(mEndMap, y);

        return this;
    }

    @Override
    public ViewAnimator coordinateEnd(@NonNull ViewInfo viewInfo) {
        return coordinateEnd(viewInfo.x, viewInfo.y);
    }

    @Override
    public ViewAnimator coordinateEnd(@NonNull View view) {
        return coordinateEnd(view.getX(), view.getY());
    }

    @Override
    public ViewAnimator coordinateStart(float x, float y) {
        ViewAnimatorUtil.setX(mStartMap, x);
        ViewAnimatorUtil.setY(mStartMap, y);

        return this;
    }

    @Override
    public ViewAnimator coordinateStart(@NonNull ViewInfo viewInfo) {
        return coordinateStart(viewInfo.x, viewInfo.y);
    }

    @Override
    public ViewAnimator coordinateStart(@NonNull View view) {
        return coordinateStart(view.getX(), view.getY());
    }

    public ViewAnimator debug(boolean debug) {
        mDebug = debug;

        return this;
    }

    public View getView() {
        return mViewRef.get();
    }

    @Override
    public ViewAnimator height(int height) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setHeight(mStartMap, view.getMeasuredHeight());
            ViewAnimatorUtil.setHeight(mEndMap, height);
        }

        return this;
    }

    @Override
    public ViewAnimator height(@NonNull ViewInfo viewInfo) {
        return height(viewInfo.height);
    }

    @Override
    public ViewAnimator height(@NonNull View view) {
        return height(view.getMeasuredHeight());
    }

    @Override
    public ViewAnimator heightBy(int heightBy) {
        View view = getView();
        int height;

        if (view != null) {
            height = view.getHeight();
            ViewAnimatorUtil.setHeight(mStartMap, height);
            ViewAnimatorUtil.setHeight(mEndMap, height + heightBy);
        }

        return this;
    }

    @Override
    public ViewAnimator heightEnd(int height) {
        ViewAnimatorUtil.setHeight(mEndMap, height);

        return this;
    }

    @Override
    public ViewAnimator heightEnd(@NonNull ViewInfo viewInfo) {
        return heightEnd(viewInfo.height);
    }

    @Override
    public ViewAnimator heightEnd(@NonNull View view) {
        return heightEnd(view.getMeasuredHeight());
    }

    @Override
    public ViewAnimator heightStart(int height) {
        ViewAnimatorUtil.setHeight(mStartMap, height);

        return this;
    }

    @Override
    public ViewAnimator heightStart(@NonNull ViewInfo viewInfo) {
        return heightStart(viewInfo.height);
    }

    @Override
    public ViewAnimator heightStart(@NonNull View view) {
        return heightStart(view.getMeasuredHeight());
    }

    public boolean isForward() {
        return mForward;
    }

    @Override
    public ViewAnimator pivot(float pivotX, float pivotY) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setPivotX(mStartMap, view.getPivotX());
            ViewAnimatorUtil.setPivotY(mStartMap, view.getPivotY());
            ViewAnimatorUtil.setPivotX(mEndMap, pivotX);
            ViewAnimatorUtil.setPivotY(mEndMap, pivotY);
        }

        return this;
    }

    @Override
    public ViewAnimator pivot(@NonNull View view) {
        return pivot(view.getPivotX(), view.getPivotY());
    }

    @Override
    public ViewAnimator pivotBy(float pivotXBy, float pivotYBy) {
        View view = getView();
        float pivotX;
        float pivotY;

        if (view != null) {
            pivotX = view.getPivotX();
            pivotY = view.getPivotY();
            ViewAnimatorUtil.setPivotX(mStartMap, pivotX);
            ViewAnimatorUtil.setPivotY(mStartMap, pivotY);
            ViewAnimatorUtil.setPivotX(mEndMap, pivotX + pivotXBy);
            ViewAnimatorUtil.setPivotY(mEndMap, pivotY + pivotYBy);
        }

        return this;
    }

    @Override
    public ViewAnimator pivotEnd(float pivotX, float pivotY) {
        ViewAnimatorUtil.setPivotX(mEndMap, pivotX);
        ViewAnimatorUtil.setPivotY(mEndMap, pivotY);

        return this;
    }

    @Override
    public ViewAnimator pivotEnd(@NonNull View view) {
        return pivotEnd(view.getPivotX(), view.getPivotY());
    }

    @Override
    public ViewAnimator pivotStart(float pivotX, float pivotY) {
        ViewAnimatorUtil.setPivotX(mStartMap, pivotX);
        ViewAnimatorUtil.setPivotY(mStartMap, pivotY);

        return this;
    }

    @Override
    public ViewAnimator pivotStart(@NonNull View view) {
        return pivotStart(view.getPivotX(), view.getPivotY());
    }

    @Override
    public ViewAnimator pivotX(float pivotX) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setPivotX(mStartMap, view.getPivotX());
            ViewAnimatorUtil.setPivotX(mEndMap, pivotX);
        }

        return this;
    }

    @Override
    public ViewAnimator pivotX(@NonNull View view) {
        return pivotX(view.getPivotX());
    }

    @Override
    public ViewAnimator pivotXBy(float pivotXBy) {
        View view = getView();
        float pivotX;

        if (view != null) {
            pivotX = view.getPivotX();
            ViewAnimatorUtil.setPivotX(mStartMap, pivotX);
            ViewAnimatorUtil.setPivotX(mEndMap, pivotX + pivotXBy);
        }

        return this;
    }

    @Override
    public ViewAnimator pivotXEnd(float pivotX) {
        ViewAnimatorUtil.setPivotX(mEndMap, pivotX);

        return this;
    }

    @Override
    public ViewAnimator pivotXEnd(@NonNull View view) {
        return pivotXEnd(view.getPivotX());
    }

    @Override
    public ViewAnimator pivotXStart(float pivotX) {
        ViewAnimatorUtil.setPivotX(mStartMap, pivotX);

        return this;
    }

    @Override
    public ViewAnimator pivotXStart(@NonNull View view) {
        return pivotXStart(view.getPivotX());
    }

    @Override
    public ViewAnimator pivotY(float pivotY) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setPivotY(mStartMap, view.getPivotY());
            ViewAnimatorUtil.setPivotY(mEndMap, pivotY);
        }

        return this;
    }

    @Override
    public ViewAnimator pivotY(@NonNull View view) {
        return pivotY(view.getPivotY());
    }

    @Override
    public ViewAnimator pivotYBy(float pivotYBy) {
        View view = getView();
        float pivotY;

        if (view != null) {
            pivotY = view.getPivotY();
            ViewAnimatorUtil.setPivotY(mStartMap, pivotY);
            ViewAnimatorUtil.setPivotY(mEndMap, pivotY + pivotYBy);
        }

        return this;
    }

    @Override
    public ViewAnimator pivotYEnd(float pivotY) {
        return pivotY(pivotY);
    }

    @Override
    public ViewAnimator pivotYEnd(@NonNull View view) {
        return pivotYEnd(view.getPivotY());
    }

    @Override
    public ViewAnimator pivotYStart(float pivotY) {
        ViewAnimatorUtil.setPivotY(mStartMap, pivotY);

        return this;
    }

    @Override
    public ViewAnimator pivotYStart(@NonNull View view) {
        return pivotYStart(view.getPivotY());
    }

    public ViewAnimator removeAllEndActions() {
        mEndActionList.clear();

        return this;
    }

    @Override
    public void removeAllListeners() {
        super.removeAllListeners();
        addListener(mInnerListener);
    }

    public ViewAnimator removeAllStartActions() {
        mStartActionList.clear();

        return this;
    }

    @Override
    public void removeAllUpdateListeners() {
        super.removeAllUpdateListeners();
        addUpdateListener(mInnerUpdateListener);
    }

    public ViewAnimator removeEndAction(@NonNull Runnable endAction) {
        mStartActionList.remove(endAction);

        return this;
    }

    public ViewAnimator removeStartAction(@NonNull Runnable startAction) {
        mStartActionList.remove(startAction);

        return this;
    }

    private void resetDuration() {
        if (mDuration > 0L) {
            setDuration(mDuration);
            mDuration = 0L;
        }
    }

    @Override
    public void reverse() {
        reverse(true);
    }

    public void reverse(boolean animate) {
        View view = getView();

        if (view != null) {
            mForward = false;
            if (!animate) {
                mDuration = getDuration();
                setDuration(0L);
            }
            if (getValues() == null) {
                setValues(ViewAnimatorUtil.make(view, mStartMap, mEndMap, mDebug));
            }
            super.reverse();
        }
    }

    @Override
    public ViewAnimator rotation(float rotation) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setRotation(mStartMap, view.getRotation());
            ViewAnimatorUtil.setRotation(mEndMap, rotation);
        }

        return this;
    }

    @Override
    public ViewAnimator rotation(@NonNull View view) {
        return rotation(view.getRotation());
    }

    @Override
    public ViewAnimator rotationBy(float rotationBy) {
        View view = getView();
        float rotation;

        if (view != null) {
            rotation = view.getRotation();
            ViewAnimatorUtil.setRotation(mStartMap, rotation);
            ViewAnimatorUtil.setRotation(mEndMap, rotation + rotationBy);
        }

        return this;
    }

    @Override
    public ViewAnimator rotationEnd(float rotation) {
        ViewAnimatorUtil.setRotation(mEndMap, rotation);

        return this;
    }

    @Override
    public ViewAnimator rotationEnd(@NonNull View view) {
        return rotationEnd(view.getRotation());
    }

    @Override
    public ViewAnimator rotationStart(float rotation) {
        ViewAnimatorUtil.setRotation(mStartMap, rotation);

        return this;
    }

    @Override
    public ViewAnimator rotationStart(@NonNull View view) {
        return rotationStart(view.getRotation());
    }

    @Override
    public ViewAnimator rotationX(float rotationX) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setRotationX(mStartMap, view.getRotationX());
            ViewAnimatorUtil.setRotationX(mEndMap, rotationX);
        }

        return this;
    }

    @Override
    public ViewAnimator rotationX(@NonNull View view) {
        return rotationX(view.getRotationX());
    }

    @Override
    public ViewAnimator rotationXBy(float rotationXBy) {
        View view = getView();
        float rotationX;

        if (view != null) {
            rotationX = view.getRotationX();
            ViewAnimatorUtil.setRotationX(mStartMap, rotationX);
            ViewAnimatorUtil.setRotationX(mEndMap, rotationX + rotationXBy);
        }

        return this;
    }

    @Override
    public ViewAnimator rotationXEnd(float rotationX) {
        ViewAnimatorUtil.setRotationX(mEndMap, rotationX);

        return this;
    }

    @Override
    public ViewAnimator rotationXEnd(@NonNull View view) {
        return rotationXEnd(view.getRotationX());
    }

    @Override
    public ViewAnimator rotationXStart(float rotationX) {
        ViewAnimatorUtil.setRotationX(mStartMap, rotationX);

        return this;
    }

    @Override
    public ViewAnimator rotationXStart(@NonNull View view) {
        return rotationXStart(view.getRotationX());
    }

    @Override
    public ViewAnimator rotationY(float rotationY) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setRotationY(mStartMap, rotationY);
            ViewAnimatorUtil.setRotationY(mEndMap, rotationY);
        }

        return this;
    }

    @Override
    public ViewAnimator rotationY(@NonNull View view) {
        return rotationY(view.getRotationY());
    }

    @Override
    public ViewAnimator rotationYBy(float rotationYBy) {
        View view = getView();
        float rotationY;

        if (view != null) {
            rotationY = view.getRotationY();
            ViewAnimatorUtil.setRotationY(mStartMap, rotationY);
            ViewAnimatorUtil.setRotationY(mEndMap, rotationY + rotationYBy);
        }

        return this;
    }

    @Override
    public ViewAnimator rotationYEnd(float rotationY) {
        ViewAnimatorUtil.setRotationY(mEndMap, rotationY);

        return this;
    }

    @Override
    public ViewAnimator rotationYEnd(@NonNull View view) {
        return rotationYEnd(view.getRotationY());
    }

    @Override
    public ViewAnimator rotationYStart(float rotationY) {
        ViewAnimatorUtil.setRotationY(mStartMap, rotationY);

        return this;
    }

    @Override
    public ViewAnimator rotationYStart(@NonNull View view) {
        return rotationYStart(view.getRotationY());
    }

    @Override
    public ViewAnimator scale(float scaleX, float scaleY) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setScaleX(mStartMap, view.getScaleX());
            ViewAnimatorUtil.setScaleY(mStartMap, view.getScaleY());
            ViewAnimatorUtil.setScaleX(mEndMap, scaleX);
            ViewAnimatorUtil.setScaleY(mEndMap, scaleY);
        }

        return this;
    }

    @Override
    public ViewAnimator scale(@NonNull View view) {
        return scale(view.getScaleX(), view.getScaleY());
    }

    @Override
    public ViewAnimator scaleBy(float scaleXBy, float scaleYBy) {
        View view = getView();
        float scaleX;
        float scaleY;

        if (view != null) {
            scaleX = view.getScaleX();
            scaleY = view.getScaleY();
            ViewAnimatorUtil.setScaleX(mStartMap, scaleX);
            ViewAnimatorUtil.setScaleY(mStartMap, scaleY);
            ViewAnimatorUtil.setScaleX(mEndMap, scaleX + scaleXBy);
            ViewAnimatorUtil.setScaleY(mEndMap, scaleY + scaleYBy);
        }

        return this;
    }

    @Override
    public ViewAnimator scaleEnd(float scaleX, float scaleY) {
        ViewAnimatorUtil.setScaleX(mEndMap, scaleX);
        ViewAnimatorUtil.setScaleY(mEndMap, scaleY);

        return this;
    }

    @Override
    public ViewAnimator scaleEnd(@NonNull View view) {
        return scaleEnd(view.getScaleX(), view.getScaleY());
    }

    @Override
    public ViewAnimator scaleStart(float scaleX, float scaleY) {
        ViewAnimatorUtil.setScaleX(mStartMap, scaleX);
        ViewAnimatorUtil.setScaleY(mStartMap, scaleY);

        return this;
    }

    @Override
    public ViewAnimator scaleStart(@NonNull View view) {
        return scaleStart(view.getScaleX(), view.getScaleY());
    }

    @Override
    public ViewAnimator scaleX(float scaleX) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setScaleX(mStartMap, view.getScaleX());
            ViewAnimatorUtil.setScaleX(mEndMap, scaleX);
        }

        return this;
    }

    @Override
    public ViewAnimator scaleX(@NonNull View view) {
        return scaleX(view.getScaleX());
    }

    @Override
    public ViewAnimator scaleXBy(float scaleXBy) {
        View view = getView();
        float scaleX;

        if (view != null) {
            scaleX = view.getScaleX();
            ViewAnimatorUtil.setScaleX(mStartMap, scaleX);
            ViewAnimatorUtil.setScaleX(mEndMap, scaleX + scaleXBy);
        }

        return this;
    }

    @Override
    public ViewAnimator scaleXEnd(float scaleX) {
        ViewAnimatorUtil.setScaleX(mEndMap, scaleX);

        return this;
    }

    @Override
    public ViewAnimator scaleXEnd(@NonNull View view) {
        return scaleXEnd(view.getScaleX());
    }

    @Override
    public ViewAnimator scaleXStart(float scaleX) {
        ViewAnimatorUtil.setScaleX(mStartMap, scaleX);

        return this;
    }

    @Override
    public ViewAnimator scaleXStart(@NonNull View view) {
        return scaleXStart(view.getScaleX());
    }

    @Override
    public ViewAnimator scaleY(float scaleY) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setScaleY(mStartMap, view.getScaleY());
            ViewAnimatorUtil.setScaleY(mEndMap, scaleY);
        }

        return this;
    }

    @Override
    public ViewAnimator scaleY(@NonNull View view) {
        return scaleY(view.getScaleY());
    }

    @Override
    public ViewAnimator scaleYBy(float scaleYBy) {
        View view = getView();
        float scaleY;

        if (view != null) {
            scaleY = view.getScaleY();
            ViewAnimatorUtil.setScaleY(mStartMap, scaleY);
            ViewAnimatorUtil.setScaleY(mEndMap, scaleY + scaleYBy);
        }

        return this;
    }

    @Override
    public ViewAnimator scaleYEnd(float scaleY) {
        ViewAnimatorUtil.setScaleY(mEndMap, scaleY);

        return this;
    }

    @Override
    public ViewAnimator scaleYEnd(@NonNull View view) {
        return scaleYEnd(view.getScaleY());
    }

    @Override
    public ViewAnimator scaleYStart(float scaleY) {
        ViewAnimatorUtil.setScaleY(mStartMap, scaleY);

        return this;
    }

    @Override
    public ViewAnimator scaleYStart(@NonNull View view) {
        return scaleYStart(view.getScaleY());
    }

    @Override
    public ViewAnimator size(int width, int height) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setWidth(mStartMap, view.getMeasuredWidth());
            ViewAnimatorUtil.setHeight(mStartMap, view.getMeasuredHeight());
            ViewAnimatorUtil.setWidth(mEndMap, width);
            ViewAnimatorUtil.setHeight(mEndMap, height);
        }

        return this;
    }

    @Override
    public ViewAnimator size(@NonNull ViewInfo viewInfo) {
        return size(viewInfo.width, viewInfo.height);
    }

    @Override
    public ViewAnimator size(@NonNull View view) {
        return size(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Override
    public ViewAnimator sizeBy(int widthBy, int heightBy) {
        View view = getView();
        int width;
        int height;

        if (view != null) {
            width = view.getWidth();
            height = view.getHeight();
            ViewAnimatorUtil.setWidth(mStartMap, width);
            ViewAnimatorUtil.setHeight(mStartMap, height);
            ViewAnimatorUtil.setWidth(mEndMap, width + widthBy);
            ViewAnimatorUtil.setHeight(mEndMap, height + heightBy);
        }

        return this;
    }

    @Override
    public ViewAnimator sizeEnd(int width, int height) {
        ViewAnimatorUtil.setWidth(mEndMap, width);
        ViewAnimatorUtil.setHeight(mEndMap, height);

        return this;
    }

    @Override
    public ViewAnimator sizeEnd(@NonNull ViewInfo viewInfo) {
        return sizeEnd(viewInfo.width, viewInfo.height);
    }

    @Override
    public ViewAnimator sizeEnd(@NonNull View view) {
        return sizeEnd(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Override
    public ViewAnimator sizeStart(int width, int height) {
        ViewAnimatorUtil.setWidth(mStartMap, width);
        ViewAnimatorUtil.setHeight(mStartMap, height);

        return this;
    }

    @Override
    public ViewAnimator sizeStart(@NonNull ViewInfo viewInfo) {
        return sizeStart(viewInfo.width, viewInfo.height);
    }

    @Override
    public ViewAnimator sizeStart(@NonNull View view) {
        return sizeStart(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Override
    public void start() {
        start(true);
    }

    public void start(boolean animate) {
        View view = getView();

        if (view != null) {
            mForward = true;
            if (!animate) {
                mDuration = getDuration();
                setDuration(0L);
            }
            setValues(ViewAnimatorUtil.make(view, mStartMap, mEndMap, mDebug));
            //            Logger.e("start animation");
            super.start();
        }
    }

    @Override
    public ViewAnimator textColor(int textColor) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setTextColor(mStartMap, ViewUtil.getTextColor(view));
            ViewAnimatorUtil.setTextColor(mEndMap, textColor);
        }

        return this;
    }

    @Override
    public ViewAnimator textColor(@NonNull View view) {
        return textColor(ViewUtil.getTextColor(view));
    }

    @Override
    public ViewAnimator textColorBy(int textColorBy) {
        View view = getView();
        int textColor;

        if (view != null) {
            textColor = ViewUtil.getTextColor(view);
            ViewAnimatorUtil.setTextColor(mStartMap, textColor);
            ViewAnimatorUtil.setTextColor(mEndMap, ColorUtil.plus(textColor, textColorBy));
        }

        return this;
    }

    @Override
    public ViewAnimator textColorEnd(int textColor) {
        ViewAnimatorUtil.setTextColor(mEndMap, textColor);

        return this;
    }

    @Override
    public ViewAnimator textColorEnd(@NonNull View view) {
        return textColorEnd(ViewUtil.getTextColor(view));
    }

    @Override
    public ViewAnimator textColorStart(int textColor) {
        ViewAnimatorUtil.setTextColor(mStartMap, textColor);

        return this;
    }

    @Override
    public ViewAnimator textColorStart(@NonNull View view) {
        return textColorStart(ViewUtil.getTextColor(view));
    }

    @Override
    public ViewAnimator translation(float translationX, float translationY) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setTranslationX(mStartMap, view.getTranslationX());
            ViewAnimatorUtil.setTranslationY(mStartMap, view.getTranslationY());
            ViewAnimatorUtil.setTranslationX(mEndMap, translationX);
            ViewAnimatorUtil.setTranslationY(mEndMap, translationY);
        }

        return this;
    }

    @Override
    public ViewAnimator translation(@NonNull View view) {
        return translation(view.getTranslationX(), view.getTranslationY());
    }

    @Override
    public ViewAnimator translationBy(float translationXBy, float translationYBy) {
        View view = getView();
        float translationX;
        float translationY;

        if (view != null) {
            translationX = view.getTranslationX();
            translationY = view.getTranslationY();
            ViewAnimatorUtil.setTranslationX(mStartMap, translationX);
            ViewAnimatorUtil.setTranslationY(mStartMap, translationY);
            ViewAnimatorUtil.setTranslationX(mEndMap, translationX + translationXBy);
            ViewAnimatorUtil.setTranslationY(mEndMap, translationY + translationYBy);
        }

        return this;
    }

    @Override
    public ViewAnimator translationEnd(float translationX, float translationY) {
        ViewAnimatorUtil.setTranslationX(mEndMap, translationX);
        ViewAnimatorUtil.setTranslationY(mEndMap, translationY);

        return this;
    }

    @Override
    public ViewAnimator translationEnd(@NonNull View view) {
        return translationEnd(view.getTranslationX(), view.getTranslationY());
    }

    @Override
    public ViewAnimator translationStart(float translationX, float translationY) {
        ViewAnimatorUtil.setTranslationX(mStartMap, translationX);
        ViewAnimatorUtil.setTranslationY(mStartMap, translationY);

        return this;
    }

    @Override
    public ViewAnimator translationStart(@NonNull View view) {
        return translationStart(view.getTranslationX(), view.getTranslationY());
    }

    @Override
    public ViewAnimator translationX(float translationX) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setTranslationX(mStartMap, view.getTranslationX());
            ViewAnimatorUtil.setTranslationX(mEndMap, translationX);
        }

        return this;
    }

    @Override
    public ViewAnimator translationX(@NonNull View view) {
        return translationX(view.getTranslationX());
    }

    @Override
    public ViewAnimator translationXBy(float translationXBy) {
        View view = getView();
        float translationX;

        if (view != null) {
            translationX = view.getTranslationX();
            ViewAnimatorUtil.setTranslationX(mStartMap, translationX);
            ViewAnimatorUtil.setTranslationX(mEndMap, translationX + translationXBy);
        }

        return this;
    }

    @Override
    public ViewAnimator translationXEnd(float translationX) {
        ViewAnimatorUtil.setTranslationX(mEndMap, translationX);

        return this;
    }

    @Override
    public ViewAnimator translationXEnd(@NonNull View view) {
        return translationXEnd(view.getTranslationX());
    }

    @Override
    public ViewAnimator translationXStart(float translationX) {
        ViewAnimatorUtil.setTranslationX(mStartMap, translationX);

        return this;
    }

    @Override
    public ViewAnimator translationXStart(@NonNull View view) {
        return translationXStart(view.getTranslationX());
    }

    @Override
    public ViewAnimator translationY(float translationY) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setTranslationY(mStartMap, view.getTranslationY());
            ViewAnimatorUtil.setTranslationY(mEndMap, translationY);
        }

        return this;
    }

    @Override
    public ViewAnimator translationY(@NonNull View view) {
        return translationY(view.getTranslationY());
    }

    @Override
    public ViewAnimator translationYBy(float translationYBy) {
        View view = getView();
        float translationY;

        if (view != null) {
            translationY = view.getTranslationY();
            ViewAnimatorUtil.setTranslationY(mStartMap, translationY);
            ViewAnimatorUtil.setTranslationY(mEndMap, translationY + translationYBy);
        }

        return this;
    }

    @Override
    public ViewAnimator translationYEnd(float translationY) {
        return translationY(translationY);
    }

    @Override
    public ViewAnimator translationYEnd(@NonNull View view) {
        return translationYEnd(view.getTranslationY());
    }

    @Override
    public ViewAnimator translationYStart(float translationY) {
        ViewAnimatorUtil.setTranslationY(mStartMap, translationY);

        return this;
    }

    @Override
    public ViewAnimator translationYStart(@NonNull View view) {
        return translationYStart(view.getTranslationY());
    }

    @Override
    public ViewAnimator visibilityEnd(int visibilityOff) {
        ViewAnimatorUtil.setVisibility(mEndMap, visibilityOff);

        return this;
    }

    @Override
    public ViewAnimator visibilityReverseEnd(int visibilityReverse) {
        ViewAnimatorUtil.setVisibilityReverse(mEndMap, visibilityReverse);

        return this;
    }

    @Override
    public ViewAnimator visibilityReverseStart(int visibilityReverse) {
        ViewAnimatorUtil.setVisibilityReverse(mStartMap, visibilityReverse);

        return this;
    }

    @Override
    public ViewAnimator visibilityStart(int visibility) {
        ViewAnimatorUtil.setVisibility(mStartMap, visibility);

        return this;
    }

    public ViewAnimator width(int width) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setWidth(mStartMap, view.getMeasuredWidth());
            ViewAnimatorUtil.setWidth(mEndMap, width);
        }

        return this;
    }

    @Override
    public ViewAnimator width(@NonNull ViewInfo viewInfo) {
        return width(viewInfo.width);
    }

    @Override
    public ViewAnimator width(@NonNull View view) {
        return width(view.getMeasuredWidth());
    }

    @Override
    public ViewAnimator widthBy(int widthBy) {
        View view = getView();
        int width;

        if (view != null) {
            width = view.getMeasuredWidth();
            ViewAnimatorUtil.setWidth(mStartMap, width);
            ViewAnimatorUtil.setWidth(mEndMap, width + widthBy);
        }

        return this;
    }

    @Override
    public ViewAnimator widthEnd(int width) {
        ViewAnimatorUtil.setWidth(mEndMap, width);

        return this;
    }

    @Override
    public ViewAnimator widthEnd(@NonNull ViewInfo viewInfo) {
        return widthEnd(viewInfo.width);
    }

    @Override
    public ViewAnimator widthEnd(@NonNull View view) {
        return widthEnd(view.getMeasuredWidth());
    }

    @Override
    public ViewAnimator widthStart(int width) {
        ViewAnimatorUtil.setWidth(mStartMap, width);

        return this;
    }

    @Override
    public ViewAnimator widthStart(@NonNull ViewInfo viewInfo) {
        return widthStart(viewInfo.width);
    }

    @Override
    public ViewAnimator widthStart(@NonNull View view) {
        return widthStart(view.getMeasuredWidth());
    }

    @Override
    public ViewAnimator x(float x) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setX(mStartMap, view.getX());
            ViewAnimatorUtil.setX(mEndMap, x);
        }

        return this;
    }

    @Override
    public ViewAnimator x(@NonNull ViewInfo viewInfo) {
        return x(viewInfo.x);
    }

    @Override
    public ViewAnimator x(@NonNull View view) {
        return x(view.getX());
    }

    @Override
    public ViewAnimator xBy(float xBy) {
        View view = getView();
        float x;

        if (view != null) {
            x = view.getX();
            ViewAnimatorUtil.setX(mStartMap, x);
            ViewAnimatorUtil.setX(mEndMap, x + xBy);
        }

        return this;
    }

    @Override
    public ViewAnimator xEnd(float x) {
        ViewAnimatorUtil.setX(mEndMap, x);

        return this;
    }

    @Override
    public ViewAnimator xEnd(@NonNull ViewInfo viewInfo) {
        return xEnd(viewInfo.x);
    }

    @Override
    public ViewAnimator xEnd(@NonNull View view) {
        return xEnd(view.getX());
    }

    @Override
    public ViewAnimator xStart(float x) {
        ViewAnimatorUtil.setX(mStartMap, x);

        return this;
    }

    @Override
    public ViewAnimator xStart(@NonNull ViewInfo viewInfo) {
        return xStart(viewInfo.x);
    }

    @Override
    public ViewAnimator xStart(@NonNull View view) {
        return xStart(view.getX());
    }

    @Override
    public ViewAnimator y(float y) {
        View view = getView();

        if (view != null) {
            ViewAnimatorUtil.setY(mStartMap, view.getY());
            ViewAnimatorUtil.setY(mEndMap, y);
        }

        return this;
    }

    @Override
    public ViewAnimator y(@NonNull ViewInfo viewInfo) {
        return y(viewInfo.y);
    }

    @Override
    public ViewAnimator y(@NonNull View view) {
        return y(view.getY());
    }

    @Override
    public ViewAnimator yBy(float yBy) {
        View view = getView();
        float y;

        if (view != null) {
            y = view.getY();
            ViewAnimatorUtil.setY(mStartMap, y);
            ViewAnimatorUtil.setY(mEndMap, y + yBy);
        }

        return this;
    }

    @Override
    public ViewAnimator yEnd(float y) {
        ViewAnimatorUtil.setY(mEndMap, y);

        return this;
    }

    @Override
    public ViewAnimator yEnd(@NonNull ViewInfo viewInfo) {
        return yEnd(viewInfo.y);
    }

    @Override
    public ViewAnimator yEnd(@NonNull View view) {
        return yEnd(view.getY());
    }

    @Override
    public ViewAnimator yStart(float y) {
        ViewAnimatorUtil.setY(mStartMap, y);

        return this;
    }

    @Override
    public ViewAnimator yStart(@NonNull ViewInfo viewInfo) {
        return yStart(viewInfo.y);
    }

    @Override
    public ViewAnimator yStart(@NonNull View view) {
        return yStart(view.getY());
    }
}