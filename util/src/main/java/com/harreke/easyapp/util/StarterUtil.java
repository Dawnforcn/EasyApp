package com.harreke.easyapp.util;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.harreke.easyapp.common.singleton.CommonHandler;

import java.util.WeakHashMap;

/**
 Created by Harreke on 2016/1/14.
 */
public class StarterUtil {
    private static WeakHashMap<Integer, Intent> mIntentMap = new WeakHashMap<>();

    private static boolean checkIntent(Object object, Intent intent) {
        int key = object.hashCode();
        Intent stored = mIntentMap.get(key);

        if (stored != null) {
            return false;
        } else {
            mIntentMap.put(key, intent);
            CommonHandler.getInstance().repostDelayed(new RemoveCallback(key), 1000L);

            return true;
        }
    }

    public static boolean start(@NonNull Activity activity, @NonNull Intent intent, int requestCode, @Nullable ActivityOptionsCompat options) {
        if (checkIntent(activity, intent)) {
            if (options != null) {
                ActivityCompat.startActivityForResult(activity, intent, requestCode, options.toBundle());
            } else {
                ActivityCompat.startActivityForResult(activity, intent, requestCode, null);
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean start(@NonNull Fragment fragment, @NonNull Intent intent, int requestCode, @Nullable ActivityOptionsCompat options) {
        if (checkIntent(fragment, intent)) {
            if (options != null) {
                fragment.startActivityForResult(intent, requestCode, options.toBundle());
            } else {
                fragment.startActivityForResult(intent, requestCode, null);
            }

            return true;
        } else {
            return false;
        }
    }

    private static class RemoveCallback implements Runnable {
        private int mKey;

        RemoveCallback(int key) {
            mKey = key;
        }

        @Override
        public void run() {
            mIntentMap.remove(mKey);
        }
    }
}