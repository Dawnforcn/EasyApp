package com.harreke.easyapp.util;

/**
 由Harreke于2016/3/17创建
 */
public class ExitUtil {
    private static final long mExitTime = 2000L;
    private static long mTime = 0L;

    public static boolean shouldExit() {
        long time = System.currentTimeMillis();

        if (time - mTime < mExitTime) {
            mTime = time;

            return true;
        } else {
            mTime = time;

            return false;
        }
    }
}