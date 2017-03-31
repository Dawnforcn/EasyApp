package com.harreke.easyapp.widget.circluarprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.harreke.easyapp.util.ResourceUtil;
import com.harreke.easyapp.widget.R;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/01/20
 */
public class CircularProgressView extends AppCompatImageView implements Animatable {
    private boolean mAttached = false;
    private float mProgress = 0f;
    private CircularProgressDrawable mProgressDrawable = null;

    public CircularProgressView(Context context) {
        this(context, null);
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray style = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressView);
        float progress = style.getFloat(R.styleable.CircularProgressView_android_progress, 0f);
        int progressColor = style.getColor(R.styleable.CircularProgressView_progressColor, ResourceUtil.obtainThemeColor(context)[2]);
        style.recycle();

        mProgressDrawable = new CircularProgressDrawable(progressColor);
        setImageDrawable(mProgressDrawable);
        setProgress(progress);
    }

    @Override
    public boolean isRunning() {
        return mProgressDrawable.isRunning();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttached = true;
        mProgressDrawable.setProgress(mProgress);
    }

    @Override
    protected void onDetachedFromWindow() {
        mAttached = false;
        mProgressDrawable.setProgress(0f);
        super.onDetachedFromWindow();
    }

    public void setProgress(float progress) {
        mProgress = progress;
        if (mAttached) {
            mProgressDrawable.setProgress(progress);
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