package com.harreke.easyapp.common.toaster;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.harreke.easyapp.common.singleton.CommonHandler;

import java.util.WeakHashMap;

/**
 由huoqisheng于2016/8/29创建
 */
public class Toaster {
    public static final long DURATION_INFINITE = -1L;
    public static final long DURATION_LONG = 3000L;
    public static final long DURATION_SHORT = 1500L;
    private static WeakHashMap<Activity, ToastHolder> mToastMap = new WeakHashMap<>();

    public static void destroy(@NonNull Activity activity) {
        ToastHolder toastHolder = mToastMap.get(activity);
        if (toastHolder != null) {
            toastHolder.destroy();
        }
        mToastMap.remove(activity);
    }

    private static ToastHolder getOrCreateToastHolder(Activity activity) {
        ToastHolder toastHolder = mToastMap.get(activity);
        if (toastHolder == null) {
            toastHolder = new ToastHolder(activity);
            mToastMap.put(activity, toastHolder);
        }
        return toastHolder;
    }

    public static void hide(@NonNull Activity activity) {
        ToastHolder toastHolder = getOrCreateToastHolder(activity);
        CommonHandler.getInstance().remove(toastHolder);
        toastHolder.hide();
    }

    public static void show(@NonNull Activity activity, @NonNull String text) {
        show(activity, text, DURATION_LONG);
    }

    public static void show(@NonNull Activity activity, @NonNull String text, long duration) {
        ToastHolder toastHolder = getOrCreateToastHolder(activity);
        CommonHandler commonHandler = CommonHandler.getInstance();
        commonHandler.remove(toastHolder);
        boolean indeterminate = (duration == DURATION_INFINITE);
        toastHolder.show(text, indeterminate);
        if (!indeterminate) {
            commonHandler.postDelayed(toastHolder, duration);
        }
    }

    public static void show(@NonNull Activity activity, int textId) {
        show(activity, textId, DURATION_LONG);
    }

    public static void show(@NonNull Activity activity, int textId, long duration) {
        ToastHolder toastHolder = getOrCreateToastHolder(activity);
        CommonHandler commonHandler = CommonHandler.getInstance();
        commonHandler.remove(toastHolder);
        boolean indeterminate = (duration == DURATION_INFINITE);
        toastHolder.show(textId, indeterminate);
        if (!indeterminate) {
            commonHandler.postDelayed(toastHolder, duration);
        }
    }
}