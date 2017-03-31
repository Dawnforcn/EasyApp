package com.harreke.easyapp.util;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * 由Harreke于2016/1/25创建
 */
public class MetricUtil {
    public static float Density;
    public static int HeightPixels;
    public static float ScaledDensity;
    public static float TouchSlop;
    public static int WidthPixels;
    
    public static float getDP(float pixel) {
        return pixel / Density;
    }

    public static float getPixel(float dp) {
        return Density * dp;
    }

    public static void init(@NonNull Application application) {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();

        Density = metrics.density;
        ScaledDensity = metrics.scaledDensity;
        TouchSlop = Density * 8f;
        WidthPixels = metrics.widthPixels;
        HeightPixels = metrics.heightPixels;
    }
}