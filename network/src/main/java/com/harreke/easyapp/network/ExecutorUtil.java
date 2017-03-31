package com.harreke.easyapp.network;

import android.support.annotation.Nullable;

/**
 由启圣于2015/6/24创建
 */
public class ExecutorUtil {
    public static void cancel(@Nullable IRequestExecutor executor) {
        if (executor != null && executor.isExecuting()) {
            executor.cancel();
        }
    }

    public static boolean isFree(@Nullable IRequestExecutor executor) {
        return executor == null || !executor.isExecuting();
    }
}
