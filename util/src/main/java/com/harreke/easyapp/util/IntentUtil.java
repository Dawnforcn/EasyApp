package com.harreke.easyapp.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by 启圣 on 2015/8/13.
 */
public class IntentUtil {
    public static Intent create(@NonNull Context context, @NonNull Class clazz) {
        return new Intent(context, clazz);
    }

    public static Intent create(@NonNull Context context, @NonNull Class clazz, String key, String value) {
        Intent intent = new Intent(context, clazz);

        intent.putExtra(key, value);

        return intent;
    }

    public static Intent create(@NonNull Context context, @NonNull Class clazz, String key, int value) {
        Intent intent = new Intent(context, clazz);

        intent.putExtra(key, value);

        return intent;
    }

    public static Intent create(@NonNull Context context, @NonNull Class clazz, String key, long value) {
        Intent intent = new Intent(context, clazz);

        intent.putExtra(key, value);

        return intent;
    }

    public static Intent create(@NonNull Context context, @NonNull Class clazz, String key, float value) {
        Intent intent = new Intent(context, clazz);

        intent.putExtra(key, value);

        return intent;
    }

    public static Intent create(@NonNull Context context, @NonNull Class clazz, String key, Serializable value) {
        Intent intent = new Intent(context, clazz);

        intent.putExtra(key, value);

        return intent;
    }
}