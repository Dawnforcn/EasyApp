package com.harreke.easyapp.common.singleton;

import android.os.Handler;
import android.support.annotation.NonNull;

/**
 由 huoqisheng 于 2017/3/30 创建
 */
public class CommonHandler {
    private volatile static CommonHandler mInstance = null;
    private Handler mHandler;

    private CommonHandler() {
        mHandler = new Handler();
    }

    public static CommonHandler getInstance() {
        if (mInstance == null) {
            synchronized (CommonHandler.class) {
                if (mInstance == null) {
                    mInstance = new CommonHandler();
                }
            }
        }
        return mInstance;
    }

    public void post(@NonNull Runnable runnable) {
        mHandler.post(runnable);
    }

    public void postDelayed(@NonNull Runnable runnable, long delayed) {
        mHandler.postDelayed(runnable, delayed);
    }

    public void remove(@NonNull Runnable runnable) {
        mHandler.removeCallbacks(runnable);
    }

    public void repost(@NonNull Runnable runnable) {
        remove(runnable);
        post(runnable);
    }

    public void repostDelayed(@NonNull Runnable runnable, long delayed) {
        remove(runnable);
        postDelayed(runnable, delayed);
    }
}