package com.harreke.easyapp.animator.viewanimator;

import android.support.annotation.NonNull;
import android.view.View;

import com.harreke.easyapp.util.ViewInfo;

/**
 由 启圣 于 2015/6/19 创建
 */
public interface IStateViewProperty {
    ViewAnimator alphaEnd(float alpha);

    ViewAnimator alphaEnd(@NonNull View view);

    ViewAnimator alphaStart(float alpha);

    ViewAnimator alphaStart(@NonNull View view);

    ViewAnimator backgroundColorEnd(int backgroundColor);

    ViewAnimator backgroundColorEnd(@NonNull View view);

    ViewAnimator backgroundColorStart(int backgroundColor);

    ViewAnimator backgroundColorStart(@NonNull View view);

    ViewAnimator coordinateEnd(float x, float y);

    ViewAnimator coordinateEnd(@NonNull ViewInfo viewInfo);

    ViewAnimator coordinateEnd(@NonNull View view);

    ViewAnimator coordinateStart(float x, float y);

    ViewAnimator coordinateStart(@NonNull ViewInfo viewInfo);

    ViewAnimator coordinateStart(@NonNull View view);

    ViewAnimator heightEnd(int height);

    ViewAnimator heightEnd(@NonNull ViewInfo viewInfo);

    ViewAnimator heightEnd(@NonNull View view);

    ViewAnimator heightStart(int height);

    ViewAnimator heightStart(@NonNull ViewInfo viewInfo);

    ViewAnimator heightStart(@NonNull View view);

    ViewAnimator pivotEnd(float pivotX, float pivotY);

    ViewAnimator pivotEnd(@NonNull View view);

    ViewAnimator pivotStart(float pivotX, float pivotY);

    ViewAnimator pivotStart(@NonNull View view);

    ViewAnimator pivotXEnd(float pivotX);

    ViewAnimator pivotXEnd(@NonNull View view);

    ViewAnimator pivotXStart(float pivotX);

    ViewAnimator pivotXStart(@NonNull View view);

    ViewAnimator pivotYEnd(float pivotY);

    ViewAnimator pivotYEnd(@NonNull View view);

    ViewAnimator pivotYStart(float pivotY);

    ViewAnimator pivotYStart(@NonNull View view);

    ViewAnimator rotationEnd(float rotation);

    ViewAnimator rotationEnd(@NonNull View view);

    ViewAnimator rotationStart(float rotation);

    ViewAnimator rotationStart(@NonNull View view);

    ViewAnimator rotationXEnd(float rotationX);

    ViewAnimator rotationXEnd(@NonNull View view);

    ViewAnimator rotationXStart(float rotationX);

    ViewAnimator rotationXStart(@NonNull View view);

    ViewAnimator rotationYEnd(float rotationY);

    ViewAnimator rotationYEnd(@NonNull View view);

    ViewAnimator rotationYStart(float rotationY);

    ViewAnimator rotationYStart(@NonNull View view);

    ViewAnimator scaleEnd(float scaleX, float scaleY);

    ViewAnimator scaleEnd(@NonNull View view);

    ViewAnimator scaleStart(float scaleX, float scaleY);

    ViewAnimator scaleStart(@NonNull View view);

    ViewAnimator scaleXEnd(float scaleX);

    ViewAnimator scaleXEnd(@NonNull View view);

    ViewAnimator scaleXStart(float scaleX);

    ViewAnimator scaleXStart(@NonNull View view);

    ViewAnimator scaleYEnd(float scaleY);

    ViewAnimator scaleYEnd(@NonNull View view);

    ViewAnimator scaleYStart(float scaleY);

    ViewAnimator scaleYStart(@NonNull View view);

    ViewAnimator sizeEnd(int width, int height);

    ViewAnimator sizeEnd(@NonNull ViewInfo viewInfo);

    ViewAnimator sizeEnd(@NonNull View view);

    ViewAnimator sizeStart(int width, int height);

    ViewAnimator sizeStart(@NonNull ViewInfo viewInfo);

    ViewAnimator sizeStart(@NonNull View view);

    ViewAnimator textColorEnd(int textColor);

    ViewAnimator textColorEnd(@NonNull View view);

    ViewAnimator textColorStart(int textColor);

    ViewAnimator textColorStart(@NonNull View view);

    ViewAnimator translationEnd(float translationX, float translationY);

    ViewAnimator translationEnd(@NonNull View view);

    ViewAnimator translationStart(float translationX, float translationY);

    ViewAnimator translationStart(@NonNull View view);

    ViewAnimator translationXEnd(float translationX);

    ViewAnimator translationXEnd(@NonNull View view);

    ViewAnimator translationXStart(float translationX);

    ViewAnimator translationXStart(@NonNull View view);

    ViewAnimator translationYEnd(float translationY);

    ViewAnimator translationYEnd(@NonNull View view);

    ViewAnimator translationYStart(float translationY);

    ViewAnimator translationYStart(@NonNull View view);

    ViewAnimator visibilityEnd(int visibilityOff);

    ViewAnimator visibilityReverseEnd(int visibilityReverse);

    ViewAnimator visibilityReverseStart(int visibilityReverse);

    ViewAnimator visibilityStart(int visibilityOff);

    ViewAnimator widthEnd(int width);

    ViewAnimator widthEnd(@NonNull ViewInfo viewInfo);

    ViewAnimator widthEnd(@NonNull View view);

    ViewAnimator widthStart(int width);

    ViewAnimator widthStart(@NonNull ViewInfo viewInfo);

    ViewAnimator widthStart(@NonNull View view);

    ViewAnimator xEnd(float x);

    ViewAnimator xEnd(@NonNull ViewInfo viewInfo);

    ViewAnimator xEnd(@NonNull View view);

    ViewAnimator xStart(float x);

    ViewAnimator xStart(@NonNull ViewInfo viewInfo);

    ViewAnimator xStart(@NonNull View view);

    ViewAnimator yEnd(float y);

    ViewAnimator yEnd(@NonNull ViewInfo viewInfo);

    ViewAnimator yEnd(@NonNull View view);

    ViewAnimator yStart(float y);

    ViewAnimator yStart(@NonNull ViewInfo viewInfo);

    ViewAnimator yStart(@NonNull View view);
}