package com.harreke.easyapp.animator.viewanimator;

import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import com.harreke.easyapp.util.ViewUtil;
import com.orhanobut.logger.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/04/28
 */
public abstract class ViewAnimatorUtil {
    public static final long DURATION = 300L;
    private static final String Key_Alpha = "alpha";
    private static final String Key_BackgroundColor = "backgroundColor";
    private static final String Key_Height = "height";
    private static final String Key_PivotX = "pivotX";
    private static final String Key_PivotY = "pivotY";
    private static final String Key_Rotation = "rotation";
    private static final String Key_RotationX = "rotationX";
    private static final String Key_RotationY = "rotationY";
    private static final String Key_ScaleX = "scaleX";
    private static final String Key_ScaleY = "scaleY";
    private static final String Key_TextColor = "textColor";
    private static final String Key_TranslationX = "translationX";
    private static final String Key_TranslationY = "translationY";
    private static final String Key_Visibility = "visibility";
    private static final String Key_VisibilityReverse = "visibilityReverse";
    private static final String Key_Width = "width";
    private static final String Key_X = "x";
    private static final String Key_Y = "y";
    private static TypeEvaluator<Integer> rgbEvaluator = new TypeEvaluator<Integer>() {
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int startInt = startValue;
            int startA = (startInt >> 24) & 0xff;
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = endValue;
            int endA = (endInt >> 24) & 0xff;
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (startA + (int) (fraction * (endA - startA))) << 24 | (startR + (int) (fraction * (endR - startR))) << 16 |
                    (startG + (int) (fraction * (endG - startG))) << 8 | (startB + (int) (fraction * (endB - startB)));
        }
    };

    public static boolean containsAlpha(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_Alpha);
    }

    public static boolean containsBackgroundColor(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_BackgroundColor);
    }

    public static boolean containsHeight(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_Height);
    }

    public static boolean containsPivotX(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_PivotX);
    }

    public static boolean containsPivotY(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_PivotY);
    }

    public static boolean containsRotation(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_Rotation);
    }

    public static boolean containsRotationX(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_RotationX);
    }

    public static boolean containsRotationY(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_RotationY);
    }

    public static boolean containsScaleX(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_ScaleX);
    }

    public static boolean containsScaleY(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_ScaleY);
    }

    public static boolean containsTextColor(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_TextColor);
    }

    public static boolean containsTranslationX(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_TranslationX);
    }

    public static boolean containsTranslationY(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_TranslationY);
    }

    public static boolean containsVisibility(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_Visibility);
    }

    public static boolean containsVisibilityReverse(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_VisibilityReverse);
    }

    public static boolean containsWidth(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_Width);
    }

    public static boolean containsX(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_X);
    }

    public static boolean containsY(@NonNull Map<String, Object> map) {
        return map.containsKey(Key_Y);
    }

    public static float getAlpha(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_Alpha);
    }

    public static float getAlpha(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsAlpha(map)) {
            return getAlpha(map);
        } else {
            return view.getAlpha();
        }
    }

    public static int getBackgroundColor(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsBackgroundColor(map)) {
            return getBackgroundColor(map);
        } else {
            return ViewUtil.getBackgroundColor(view);
        }
    }

    public static int getBackgroundColor(@NonNull Map<String, Object> map) {
        return (int) map.get(Key_BackgroundColor);
    }

    public static int getHeight(@NonNull Map<String, Object> map) {
        return (int) map.get(Key_Height);
    }

    public static int getHeight(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsHeight(map)) {
            return getHeight(map);
        } else {
            return view.getMeasuredHeight();
        }
    }

    public static float getPivotX(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_PivotX);
    }

    public static float getPivotX(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsPivotX(map)) {
            return getPivotX(map);
        } else {
            return view.getPivotX();
        }
    }

    public static float getPivotY(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_PivotY);
    }

    public static float getPivotY(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsPivotY(map)) {
            return getPivotY(map);
        } else {
            return view.getPivotY();
        }
    }

    public static float getRotation(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_Rotation);
    }

    public static float getRotation(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsRotation(map)) {
            return getRotation(map);
        } else {
            return view.getRotation();
        }
    }

    public static float getRotationX(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_RotationX);
    }

    public static float getRotationX(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsRotationX(map)) {
            return getRotationX(map);
        } else {
            return view.getRotationX();
        }
    }

    public static float getRotationY(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsRotationY(map)) {
            return getRotationY(map);
        } else {
            return view.getRotationY();
        }
    }

    public static float getRotationY(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_RotationY);
    }

    public static float getScaleX(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsScaleX(map)) {
            return getScaleX(map);
        } else {
            return view.getScaleX();
        }
    }

    public static float getScaleX(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_ScaleX);
    }

    public static float getScaleY(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsScaleY(map)) {
            return getScaleY(map);
        } else {
            return view.getScaleY();
        }
    }

    public static float getScaleY(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_ScaleY);
    }

    public static int getTextColor(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsTextColor(map)) {
            return getTextColor(map);
        } else {
            return ViewUtil.getTextColor(view);
        }
    }

    public static int getTextColor(@NonNull Map<String, Object> map) {
        return (int) map.get(Key_TextColor);
    }

    public static float getTranslationX(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsTranslationX(map)) {
            return getTranslationX(map);
        } else {
            return view.getTranslationX();
        }
    }

    public static float getTranslationX(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_TranslationX);
    }

    public static float getTranslationY(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsTranslationY(map)) {
            return getTranslationY(map);
        } else {
            return view.getTranslationY();
        }
    }

    public static float getTranslationY(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_TranslationY);
    }

    public static float getVisibility(@NonNull View view) {
        return view.getVisibility();
    }

    public static int getVisibility(@NonNull Map<String, Object> map) {
        return (int) map.get(Key_Visibility);
    }

    public static int getVisibilityReverse(@NonNull Map<String, Object> map) {
        return (int) map.get(Key_VisibilityReverse);
    }

    public static int getWidth(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsWidth(map)) {
            return getWidth(map);
        } else {
            return view.getMeasuredWidth();
        }
    }

    public static int getWidth(@NonNull Map<String, Object> map) {
        return (int) map.get(Key_Width);
    }

    public static float getX(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_X);
    }

    public static float getX(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsX(map)) {
            return getX(map);
        } else {
            return view.getX();
        }
    }

    public static float getY(@NonNull View view, @NonNull Map<String, Object> map) {
        if (containsY(map)) {
            return getY(map);
        } else {
            return view.getY();
        }
    }

    public static float getY(@NonNull Map<String, Object> map) {
        return (float) map.get(Key_Y);
    }

    public static PropertyValuesHolder[] make(@NonNull View view, @NonNull Map<String, Object> map) {
        return make(view, map, false);
    }

    public static PropertyValuesHolder[] make(@NonNull View view, @NonNull Map<String, Object> map, boolean debug) {
        List<PropertyValuesHolder> holderList = new LinkedList<>();
        StringBuilder logBuilder = new StringBuilder("Animator Log:");

        //Visibility
        logBuilder.append("\nVisibility start ").append(getVisibility(view));
        if (containsVisibility(map)) {
            logBuilder.append("\nVisibility end ").append(getVisibility(map));
        }
        logBuilder.append("\nVisibilityReverse start ").append(getVisibility(view));
        if (containsVisibilityReverse(map)) {
            logBuilder.append("\nVisibilityReverse end ").append(getVisibilityReverse(map));
        }
        if (containsVisibilityReverse(map)) {
            logBuilder.append("\nVisibilityReverse from ").append(getVisibility(view)).append(" to ").append(getVisibilityReverse(map));
        }
        //Alpha
        if (containsAlpha(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_Alpha, view.getAlpha(), getAlpha(map)));
            logBuilder.append("\nAlpha from ").append(view.getAlpha()).append(" to ").append(getAlpha(map));
        }
        //Width
        if (containsWidth(map)) {
            holderList.add(PropertyValuesHolder.ofInt(Key_Width, view.getMeasuredWidth(), getWidth(map)));
            logBuilder.append("\nWidth from ").append(view.getMeasuredWidth()).append(" to ").append(getWidth(map));
        }
        //Height
        if (containsHeight(map)) {
            holderList.add(PropertyValuesHolder.ofInt(Key_Height, view.getMeasuredHeight(), getHeight(map)));
            logBuilder.append("\nHeight from ").append(view.getMeasuredHeight()).append(" to ").append(getHeight(map));
        }
        //PivotX
        if (containsPivotX(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_PivotX, view.getPivotX(), getPivotX(map)));
            logBuilder.append("\nPivotX from ").append(view.getPivotX()).append(" to ").append(getPivotX(map));
        }
        //PivotY
        if (containsPivotY(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_PivotY, view.getPivotY(), getPivotY(map)));
            logBuilder.append("\nPivotY from ").append(view.getPivotY()).append(" to ").append(getPivotY(map));
        }
        //Rotation
        if (containsRotation(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_Rotation, view.getRotation(), getRotation(map)));
            logBuilder.append("\nRotation from ").append(view.getRotation()).append(" to ").append(getRotation(map));
        }
        //RotationX
        if (containsRotationX(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_RotationX, view.getRotationX(), getRotationX(map)));
            logBuilder.append("\nRotationX from ").append(view.getRotationX()).append(" to ").append(getRotationX(map));
        }
        //RotationY
        if (containsRotationY(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_RotationY, view.getRotationY(), getRotationY(map)));
            logBuilder.append("\nRotationY from ").append(view.getRotationY()).append(" to ").append(getRotationY(map));
        }
        //ScaleX
        if (containsScaleX(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_ScaleX, view.getScaleX(), getScaleX(map)));
            logBuilder.append("\nScaleX from ").append(view.getScaleX()).append(" to ").append(getScaleX(map));
        }
        //ScaleY
        if (containsScaleY(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_ScaleY, view.getScaleY(), getScaleY(map)));
            logBuilder.append("\nScaleY from ").append(view.getScaleY()).append(" to ").append(getScaleY(map));
        }
        //TranslationX
        if (containsTranslationX(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_TranslationX, view.getTranslationX(), getTranslationX(map)));
            logBuilder.append("\nTranslationX from ").append(view.getTranslationX()).append(" to ").append(getTranslationX(map));
        }
        //TranslationY
        if (containsTranslationY(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_TranslationY, view.getTranslationY(), getTranslationY(map)));
            logBuilder.append("\nTranslationY from ").append(view.getTranslationY()).append(" to ").append(getTranslationY(map));
        }
        //X
        if (containsX(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_X, view.getX(), getX(map)));
            logBuilder.append("\nX from ").append(view.getX()).append(" to ").append(getX(map));
        }
        //Y
        if (containsY(map)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_Y, view.getY(), getY(map)));
            logBuilder.append("\nY from ").append(view.getY()).append(" to ").append(getY(map));
        }
        //BackgroundColor
        if (containsBackgroundColor(map)) {
            holderList.add(PropertyValuesHolder.ofObject(Key_BackgroundColor, rgbEvaluator, ViewUtil.getBackgroundColor(view), getBackgroundColor(map)));
            logBuilder.append("\nBackgroundColor from ")
                    .append(Integer.toHexString(ViewUtil.getBackgroundColor(view)))
                    .append(" to ")
                    .append(Integer.toHexString(getBackgroundColor(map)));
        }
        //TextColor
        if (containsTextColor(map)) {
            holderList.add(PropertyValuesHolder.ofObject(Key_TextColor, rgbEvaluator, ViewUtil.getTextColor(view), getTextColor(map)));
            logBuilder.append("\nTextColor from ")
                    .append(Integer.toHexString(ViewUtil.getTextColor(view)))
                    .append(" to ")
                    .append(Integer.toHexString(getTextColor(map)));
        }
        if (debug) {
            Logger.e(logBuilder.toString());
        }

        return holderList.toArray(new PropertyValuesHolder[holderList.size()]);
    }

    public static PropertyValuesHolder[] make(@NonNull View view, @NonNull Map<String, Object> fromMap, @NonNull Map<String, Object> toMap) {
        return make(view, fromMap, toMap, false);
    }

    public static PropertyValuesHolder[] make(@NonNull View view, @NonNull Map<String, Object> fromMap, @NonNull Map<String, Object> toMap, boolean debug) {
        List<PropertyValuesHolder> holderList = new LinkedList<>();
        StringBuilder logBuilder = new StringBuilder("Animator Log:");

        //Visibility
        if (containsVisibility(fromMap)) {
            logBuilder.append("\nVisibility start ").append(getVisibility(fromMap));
        }
        if (containsVisibility(toMap)) {
            logBuilder.append("\nVisibility end ").append(getVisibility(toMap));
        }
        if (containsVisibilityReverse(fromMap)) {
            logBuilder.append("\nVisibilityReverse start ").append(getVisibilityReverse(fromMap));
        }
        if (containsVisibilityReverse(toMap)) {
            logBuilder.append("\nVisibilityReverse end ").append(getVisibilityReverse(toMap));
        }
        //Alpha
        if (containsAlpha(fromMap) || containsAlpha(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_Alpha, getAlpha(view, fromMap), getAlpha(view, toMap)));
            logBuilder.append("\nAlpha from ").append(getAlpha(view, fromMap)).append(" to ").append(getAlpha(view, toMap));
        }
        //Width
        if (containsWidth(fromMap) || containsWidth(toMap)) {
            holderList.add(PropertyValuesHolder.ofInt(Key_Width, getWidth(view, fromMap), getWidth(view, toMap)));
            logBuilder.append("\nWidth from ").append(getWidth(view, fromMap)).append(" to ").append(getWidth(view, toMap));
        }
        //Height
        if (containsHeight(fromMap) || containsHeight(toMap)) {
            holderList.add(PropertyValuesHolder.ofInt(Key_Height, getHeight(view, fromMap), getHeight(view, toMap)));
            logBuilder.append("\nHeight from ").append(getHeight(view, fromMap)).append(" to ").append(getHeight(view, toMap));
        }
        //PivotX
        if (containsPivotX(fromMap) || containsPivotX(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_PivotX, getPivotX(view, fromMap), getPivotX(view, toMap)));
            logBuilder.append("\nPivotX from ").append(getPivotX(view, fromMap)).append(" to ").append(getPivotX(view, toMap));
        }
        //PivotY
        if (containsPivotY(fromMap) || containsPivotY(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_PivotY, getPivotY(view, fromMap), getPivotY(view, toMap)));
            logBuilder.append("\nPivotY from ").append(getPivotY(view, fromMap)).append(" to ").append(getPivotY(view, toMap));
        }
        //Rotation
        if (containsRotation(fromMap) || containsRotation(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_Rotation, getRotation(view, fromMap), getRotation(view, toMap)));
            logBuilder.append("\nRotation from ").append(getRotation(view, fromMap)).append(" to ").append(getRotation(view, toMap));
        }
        //RotationX
        if (containsRotationX(fromMap) || containsRotationX(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_RotationX, getRotationX(view, fromMap), getRotationX(view, toMap)));
            logBuilder.append("\nRotationX from ").append(getRotationX(view, fromMap)).append(" to ").append(getRotationX(view, toMap));
        }
        //RotationY
        if (containsRotationY(fromMap) || containsRotationY(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_RotationY, getRotationY(view, fromMap), getRotationY(view, toMap)));
            logBuilder.append("\nRotationY from ").append(getRotationY(view, fromMap)).append(" to ").append(getRotationY(view, toMap));
        }
        //ScaleX
        if (containsScaleX(fromMap) || containsScaleX(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_ScaleX, getScaleX(view, fromMap), getScaleX(view, toMap)));
            logBuilder.append("\nScaleX from ").append(getScaleX(view, fromMap)).append(" to ").append(getScaleX(view, toMap));
        }
        //ScaleY
        if (containsScaleY(fromMap) || containsScaleY(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_ScaleY, getScaleY(view, fromMap), getScaleY(view, toMap)));
            logBuilder.append("\nScaleY from ").append(getScaleY(view, fromMap)).append(" to ").append(getScaleY(view, toMap));
        }
        //TranslationX
        if (containsTranslationX(fromMap) || containsTranslationX(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_TranslationX, getTranslationX(view, fromMap), getTranslationX(view, toMap)));
            logBuilder.append("\nTranslationX from ").append(getTranslationX(view, fromMap)).append(" to ").append(getTranslationX(view, toMap));
        }
        //TranslationY
        if (containsTranslationY(fromMap) || containsTranslationY(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_TranslationY, getTranslationY(view, fromMap), getTranslationY(view, toMap)));
            logBuilder.append("\nTranslationY from ").append(getTranslationY(view, fromMap)).append(" to ").append(getTranslationY(view, toMap));
        }
        //X
        if (containsX(fromMap) || containsX(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_X, getX(view, fromMap), getX(view, toMap)));
            logBuilder.append("\nX from ").append(getX(view, fromMap)).append(" to ").append(getX(view, toMap));
        }
        //Y
        if (containsY(fromMap) || containsY(toMap)) {
            holderList.add(PropertyValuesHolder.ofFloat(Key_Y, getY(view, fromMap), getY(view, toMap)));
            logBuilder.append("\nY from ").append(getY(view, fromMap)).append(" to ").append(getY(view, toMap));
        }
        //BackgroundColor
        if (containsBackgroundColor(fromMap) || containsBackgroundColor(toMap)) {
            holderList.add(PropertyValuesHolder.ofObject(Key_BackgroundColor,
                    rgbEvaluator,
                    getBackgroundColor(view, fromMap),
                    getBackgroundColor(view, toMap)));
            logBuilder.append("\nBackgroundColor from ")
                    .append(Integer.toHexString(getBackgroundColor(view, fromMap)))
                    .append(" to ")
                    .append(Integer.toHexString(getBackgroundColor(view, toMap)));
        }
        //TextColor
        if (containsTextColor(fromMap) || containsTextColor(toMap)) {
            holderList.add(PropertyValuesHolder.ofObject(Key_TextColor, rgbEvaluator, getTextColor(view, fromMap), getTextColor(view, toMap)));
            logBuilder.append("\nTextColor from ")
                    .append(Integer.toHexString(getTextColor(view, fromMap)))
                    .append(" to ")
                    .append(Integer.toHexString(getTextColor(view, toMap)));
        }
        if (debug) {
            Logger.e(logBuilder.toString());
        }

        return holderList.toArray(new PropertyValuesHolder[holderList.size()]);
    }

    public static void set(@NonNull View view, @NonNull Map<String, Object> map) {
        set(view, map, false);
    }

    public static void set(@NonNull View view, @NonNull Map<String, Object> map, boolean debug) {
        StringBuilder logBuilder = new StringBuilder("Animator Log:");

        //Alpha
        if (containsAlpha(map)) {
            view.setAlpha(getAlpha(map));
            logBuilder.append("\nAlpha set to ").append(ViewAnimatorUtil.getAlpha(map));
        }
        //Width
        if (containsWidth(map)) {
            ViewUtil.setWidth(view, getWidth(map));
            logBuilder.append("\nWidth set to ").append(ViewAnimatorUtil.getWidth(map));
        }
        //Height
        if (containsHeight(map)) {
            ViewUtil.setHeight(view, getHeight(map));
            logBuilder.append("\nHeight set to ").append(ViewAnimatorUtil.getHeight(map));
        }
        //PivotX
        if (containsPivotX(map)) {
            view.setPivotX(getPivotX(map));
            logBuilder.append("\nPivotX set to ").append(ViewAnimatorUtil.getPivotX(map));
        }
        //PivotY
        if (containsPivotY(map)) {
            view.setPivotY(getPivotY(map));
            logBuilder.append("\nPivotY set to ").append(ViewAnimatorUtil.getPivotY(map));
        }
        //Rotation
        if (containsRotation(map)) {
            view.setRotation(getRotation(map));
            logBuilder.append("\nRotation set to ").append(ViewAnimatorUtil.getRotation(map));
        }
        //RotationX
        if (containsRotationX(map)) {
            view.setRotationX(getRotationX(map));
            logBuilder.append("\nRotationX set to ").append(ViewAnimatorUtil.getRotationX(map));
        }
        //RotationY
        if (containsRotationY(map)) {
            view.setRotationY(getRotationY(map));
            logBuilder.append("\nRotationY set to ").append(ViewAnimatorUtil.getRotationY(map));
        }
        //ScaleX
        if (containsScaleX(map)) {
            view.setScaleX(getScaleX(map));
            logBuilder.append("\nScaleX set to ").append(ViewAnimatorUtil.getScaleX(map));
        }
        //ScaleY
        if (containsScaleY(map)) {
            view.setScaleY(getScaleY(map));
            logBuilder.append("\nScaleY set to ").append(ViewAnimatorUtil.getScaleY(map));
        }
        //TranslationX
        if (containsTranslationX(map)) {
            view.setTranslationX(getTranslationX(map));
            logBuilder.append("\nTranslationX set to ").append(ViewAnimatorUtil.getTranslationX(map));
        }
        //TranslationY
        if (containsTranslationY(map)) {
            view.setTranslationY(getTranslationY(map));
            logBuilder.append("\nTranslationY set to ").append(ViewAnimatorUtil.getTranslationY(map));
        }
        //X
        if (containsX(map)) {
            view.setX(getX(map));
            logBuilder.append("\nX set to ").append(ViewAnimatorUtil.getX(map));
        }
        //Y
        if (containsY(map)) {
            view.setY(getY(map));
            logBuilder.append("\nY set to ").append(ViewAnimatorUtil.getY(map));
        }
        //BackgroundColor
        if (containsBackgroundColor(map)) {
            view.setBackgroundColor(getBackgroundColor(map));
            logBuilder.append("\nBackgroundColor set to ").append(Integer.toHexString(ViewAnimatorUtil.getBackgroundColor(map)));
        }
        //TextColor
        if (containsTextColor(map)) {
            ViewUtil.setTextColor(view, getTextColor(map));
            logBuilder.append("\nTextColor set to ").append(Integer.toHexString(ViewAnimatorUtil.getTextColor(map)));
        }
        if (debug) {
            Logger.e(logBuilder.toString());
        }
    }

    public static void setAlpha(@NonNull Map<String, Object> map, float alpha) {
        map.put(Key_Alpha, alpha);
    }

    public static void setBackgroundColor(@NonNull Map<String, Object> map, int backgroundColor) {
        map.put(Key_BackgroundColor, backgroundColor);
    }

    public static void setHeight(@NonNull Map<String, Object> map, int height) {
        map.put(Key_Height, height);
    }

    public static void setPivotX(@NonNull Map<String, Object> map, float pivotX) {
        map.put(Key_PivotX, pivotX);
    }

    public static void setPivotY(@NonNull Map<String, Object> map, float pivotY) {
        map.put(Key_PivotY, pivotY);
    }

    public static void setRotation(@NonNull Map<String, Object> map, float rotation) {
        map.put(Key_Rotation, rotation);
    }

    public static void setRotationX(@NonNull Map<String, Object> map, float rotationX) {
        map.put(Key_RotationX, rotationX);
    }

    public static void setRotationY(@NonNull Map<String, Object> map, float rotationY) {
        map.put(Key_RotationY, rotationY);
    }

    public static void setScaleX(@NonNull Map<String, Object> map, float scaleX) {
        map.put(Key_ScaleX, scaleX);
    }

    public static void setScaleY(@NonNull Map<String, Object> map, float scaleY) {
        map.put(Key_ScaleY, scaleY);
    }

    public static void setTextColor(@NonNull Map<String, Object> map, int backgroundColor) {
        map.put(Key_TextColor, backgroundColor);
    }

    public static void setTranslationX(@NonNull Map<String, Object> map, float translationX) {
        map.put(Key_TranslationX, translationX);
    }

    public static void setTranslationY(@NonNull Map<String, Object> map, float translationY) {
        map.put(Key_TranslationY, translationY);
    }

    public static void setVisibility(@NonNull Map<String, Object> map, int visibility) {
        map.put(Key_Visibility, visibility);
    }

    public static void setVisibilityReverse(@NonNull Map<String, Object> map, int visibilityReverse) {
        map.put(Key_VisibilityReverse, visibilityReverse);
    }

    public static void setWidth(@NonNull Map<String, Object> map, int width) {
        map.put(Key_Width, width);
    }

    public static void setX(@NonNull Map<String, Object> map, float x) {
        map.put(Key_X, x);
    }

    public static void setY(@NonNull Map<String, Object> map, float y) {
        map.put(Key_Y, y);
    }

    public static void update(@NonNull View view, @NonNull ValueAnimator animation) {
        update(view, animation, false);
    }

    public static void update(@NonNull View view, @NonNull ValueAnimator animation, boolean debug) {
        Object value;
        StringBuilder logBuilder = new StringBuilder("Animator Log:");

        value = animation.getAnimatedValue(Key_Alpha);
        if (value != null) {
            view.setAlpha((float) value);
            logBuilder.append("\nAlpha update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_Width);
        if (value != null) {
            ViewUtil.setWidth(view, (int) value);
            logBuilder.append("\nWidth update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_Height);
        if (value != null) {
            ViewUtil.setHeight(view, (int) value);
            logBuilder.append("\nHeight update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_PivotX);
        if (value != null) {
            view.setPivotX((float) value);
            logBuilder.append("\nPivotX update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_PivotY);
        if (value != null) {
            view.setPivotY((float) value);
            logBuilder.append("\nPivotY update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_Rotation);
        if (value != null) {
            view.setRotation((float) value);
            logBuilder.append("\nRotation update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_RotationX);
        if (value != null) {
            view.setRotationX((float) value);
            logBuilder.append("\nRotationX update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_RotationY);
        if (value != null) {
            view.setRotationY((float) value);
            logBuilder.append("\nRotationY update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_ScaleX);
        if (value != null) {
            view.setScaleX((float) value);
            logBuilder.append("\nScaleX update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_ScaleY);
        if (value != null) {
            view.setScaleY((float) value);
            logBuilder.append("\nScaleY update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_TranslationX);
        if (value != null) {
            view.setTranslationX((float) value);
            logBuilder.append("\nTranslationX update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_TranslationY);
        if (value != null) {
            view.setTranslationY((float) value);
            logBuilder.append("\nTranslationY update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_X);
        if (value != null) {
            view.setX((float) value);
            logBuilder.append("\nX update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_Y);
        if (value != null) {
            view.setY((float) value);
            logBuilder.append("\nY update to ").append(value);
        }
        value = animation.getAnimatedValue(Key_BackgroundColor);
        if (value != null) {
            view.setBackgroundColor((int) value);
            logBuilder.append("\nBackgroundColor update to ").append(Integer.toHexString((Integer) value));
        }
        value = animation.getAnimatedValue(Key_TextColor);
        if (value != null) {
            ViewUtil.setTextColor(view, (int) value);
            logBuilder.append("\nTextColor update to ").append(Integer.toHexString((Integer) value));
        }
        if (debug) {
            Logger.e(logBuilder.toString());
        }
    }
}