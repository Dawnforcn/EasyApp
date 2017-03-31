package com.harreke.easyapp.animator.viewanimator;

import android.support.annotation.NonNull;
import android.view.View;

import com.harreke.easyapp.util.ViewInfo;

/**
 由启圣于2015/6/19创建
 */
public interface IViewProperty {
    ViewAnimator alpha(float alpha);

    ViewAnimator alpha(@NonNull View view);

    ViewAnimator alphaBy(float alphaBy);

    ViewAnimator backgroundColor(int backgroundColor);

    ViewAnimator backgroundColor(@NonNull View view);

    ViewAnimator backgroundColorBy(int backgroundColorBy);

    ViewAnimator coordinate(float x, float y);

    ViewAnimator coordinate(@NonNull ViewInfo viewInfo);

    ViewAnimator coordinate(@NonNull View view);

    ViewAnimator coordinateBy(float xBy, float yBy);

    ViewAnimator height(int height);

    ViewAnimator height(@NonNull ViewInfo viewInfo);

    ViewAnimator height(@NonNull View view);

    ViewAnimator heightBy(int heightBy);

    ViewAnimator pivot(float pivotX, float pivotY);

    ViewAnimator pivot(@NonNull View view);

    ViewAnimator pivotBy(float pivotXBy, float pivotYBy);

    ViewAnimator pivotX(float pivotX);

    ViewAnimator pivotX(@NonNull View view);

    ViewAnimator pivotXBy(float pivotXBy);

    ViewAnimator pivotY(float pivotY);

    ViewAnimator pivotY(@NonNull View view);

    ViewAnimator pivotYBy(float pivotYBy);

    ViewAnimator rotation(float rotation);

    ViewAnimator rotation(@NonNull View view);

    ViewAnimator rotationBy(float rotationBy);

    ViewAnimator rotationX(float rotationX);

    ViewAnimator rotationX(@NonNull View view);

    ViewAnimator rotationXBy(float rotationXBy);

    ViewAnimator rotationY(float rotationY);

    ViewAnimator rotationY(@NonNull View view);

    ViewAnimator rotationYBy(float rotationYBy);

    ViewAnimator scale(float scaleX, float scaleY);

    ViewAnimator scale(@NonNull View view);

    ViewAnimator scaleBy(float scaleXBy, float scaleYBy);

    ViewAnimator scaleX(float scaleX);

    ViewAnimator scaleX(@NonNull View view);

    ViewAnimator scaleXBy(float scaleXBy);

    ViewAnimator scaleY(float scaleY);

    ViewAnimator scaleY(@NonNull View view);

    ViewAnimator scaleYBy(float scaleYBy);

    ViewAnimator size(int width, int height);

    ViewAnimator size(@NonNull ViewInfo viewInfo);

    ViewAnimator size(@NonNull View view);

    ViewAnimator sizeBy(int widthBy, int heightBy);

    ViewAnimator textColor(int textColor);

    ViewAnimator textColor(@NonNull View view);

    ViewAnimator textColorBy(int textColorBy);

    ViewAnimator translation(float translationX, float translationY);

    ViewAnimator translation(@NonNull View view);

    ViewAnimator translationBy(float translationXBy, float translationYBy);

    ViewAnimator translationX(float translationX);

    ViewAnimator translationX(@NonNull View view);

    ViewAnimator translationXBy(float translationXBy);

    ViewAnimator translationY(float translationY);

    ViewAnimator translationY(@NonNull View view);

    ViewAnimator translationYBy(float translationYBy);

    ViewAnimator width(int width);

    ViewAnimator width(@NonNull ViewInfo viewInfo);

    ViewAnimator width(@NonNull View view);

    ViewAnimator widthBy(int widthBy);

    ViewAnimator x(float x);

    ViewAnimator x(@NonNull ViewInfo viewInfo);

    ViewAnimator x(@NonNull View view);

    ViewAnimator xBy(float xBy);

    ViewAnimator y(float y);

    ViewAnimator y(@NonNull ViewInfo viewInfo);

    ViewAnimator y(@NonNull View view);

    ViewAnimator yBy(float yBy);
}