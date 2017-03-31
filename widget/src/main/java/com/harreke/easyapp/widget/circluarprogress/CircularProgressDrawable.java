package com.harreke.easyapp.widget.circluarprogress;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.harreke.easyapp.util.MetricUtil;
import com.harreke.easyapp.util.ResourceUtil;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/12/10
 */
public class CircularProgressDrawable extends Drawable implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener, Animatable {
    private static final float ARC_MAX_DEGREE = 300f;
    private static final float ARC_MIN_DEGREE = 8f;
    private static final float ARC_STROKE = MetricUtil.getPixel(2f);
    private static final int DEFAULT_SIZE = (int) MetricUtil.getPixel(24f);
    private ValueAnimator mAnimator;
    private float mArcDegree = 0;
    private float mArcHead = 0f;
    private Paint mBottomPaint;
    private RectF mDrawBounds = new RectF();
    private float mEndDegree = 0;
    private int mIntrinsicHeight = DEFAULT_SIZE;
    private int mIntrinsicWidth = DEFAULT_SIZE;
    private float mLastDegree = 0f;
    private boolean mPlayForward = true;
    private float mProgress = 0f;
    private float mStartDegree = 0f;
    private Paint mTopPaint;

    public CircularProgressDrawable(Context context) {
        this(ResourceUtil.obtainThemeColor(context)[2]);
    }

    public CircularProgressDrawable(int color) {
        mAnimator = ValueAnimator.ofFloat(0f, 1f);
        mAnimator.setDuration(600L);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.addUpdateListener(this);
        mAnimator.addListener(this);

        mTopPaint = new Paint();
        mTopPaint.setColor(color);
        mTopPaint.setStyle(Paint.Style.STROKE);
        mTopPaint.setStrokeWidth(ARC_STROKE);
        mTopPaint.setAntiAlias(true);
        mTopPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        mBottomPaint = new Paint();
        mBottomPaint.setColor((color & 0x00ffffff) | 0x40000000);
        mBottomPaint.setStyle(Paint.Style.STROKE);
        mBottomPaint.setStrokeWidth(ARC_STROKE);
        mTopPaint.setAntiAlias(true);
        mBottomPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mProgress >= 0f) {
            canvas.drawArc(mDrawBounds, -90, 360f, false, mBottomPaint);
            canvas.drawArc(mDrawBounds, -90f, mProgress * 360f, false, mTopPaint);
        } else {
            canvas.drawArc(mDrawBounds, mArcHead - 90, mArcDegree, false, mTopPaint);
        }
    }

    @Override
    public int getIntrinsicHeight() {
        return mIntrinsicHeight;
    }

    @Override
    public int getIntrinsicWidth() {
        return mIntrinsicWidth;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    public float getProgress() {
        return mProgress;
    }

    @Override
    public boolean isRunning() {
        return getProgress() == -1f;
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        mStartDegree = ARC_MIN_DEGREE;
        mEndDegree = ARC_MAX_DEGREE - ARC_MIN_DEGREE;
        mPlayForward = !mPlayForward;
        mLastDegree = 0f;
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float step;

        mArcDegree = mStartDegree + mEndDegree * animation.getAnimatedFraction();

        if (mPlayForward) {
            mArcHead += 3.6f;
        } else {
            if (mLastDegree == 0) {
                step = 0;
            } else {
                step = mLastDegree - mArcDegree;
            }
            mLastDegree = mArcDegree;
            mArcHead += 3.6f + step;
        }
        if (mArcHead >= 360f) {
            mArcHead -= 360f;
        }
        invalidateSelf();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mIntrinsicWidth = bounds.width();
        mIntrinsicHeight = bounds.height();
        mDrawBounds.set(bounds.left + ARC_STROKE, bounds.top + ARC_STROKE, bounds.right - ARC_STROKE, bounds.bottom - ARC_STROKE);
    }

    @Override
    public void setAlpha(int alpha) {
        mTopPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    /**
     设置进度

     @param progress 进度
     0f~1f，表示从0%到100%
     -1f，表示一直转动
     */
    public void setProgress(float progress) {
        mAnimator.cancel();
        if (progress < 0f) {
            mProgress = -1f;
            mArcHead = 0f;
            mArcDegree = 0f;
            mStartDegree = 0f;
            mEndDegree = ARC_MAX_DEGREE;
            mLastDegree = 0f;
            mPlayForward = true;
            mAnimator.start();
        } else if (progress > 1f) {
            mProgress = 1f;
            invalidateSelf();
        } else {
            mProgress = progress;
            invalidateSelf();
        }
    }

    @Override
    public void start() {
        setProgress(-1f);
    }

    @Override
    public void stop() {
        setProgress(0f);
    }
}