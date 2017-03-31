package com.harreke.easyapp.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 Created by huoqisheng on 2016/7/7.
 */
public class PreferenceUtil {
    private static WeakReference<Application> mApplicationRef;

    private static Application getApplication() {
        return mApplicationRef == null ? null : mApplicationRef.get();
    }

    public static String getMd5UUID() {
        String uuid = readString("device_id_md5", null);

        if (uuid == null) {
            uuid = new RandomGUID().valueAfterMD5;
            writeString("device_id_md5", uuid);
        }

        return uuid;
    }

    private static SharedPreferences getPreference() {
        Application application = getApplication();
        return application == null ? null : PreferenceManager.getDefaultSharedPreferences(application);
    }

    private static SharedPreferences getPreference(@NonNull String name) {
        Application application = getApplication();
        return application == null ? null : application.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static String getUUID() {
        String uuid = readString("device_id", null);

        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            writeString("device_id", uuid);
        }

        return uuid;
    }

    public static void init(@NonNull Application application) {
        mApplicationRef = new WeakReference<>(application);
    }

    /**
     从指定文档文档中读取一个布尔型数据

     @param name 文档名
     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static boolean readBoolean(String name, @NonNull String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = getPreference(name);
        return sharedPreferences == null ? defaultValue : sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     从App默认文档中读取一个布尔型数据

     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static boolean readBoolean(@NonNull String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = getPreference();
        return sharedPreferences == null ? defaultValue : sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     从指定文档文档中读取一个浮点型数据

     @param name 文档名
     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static float readFloat(String name, @NonNull String key, float defaultValue) {
        SharedPreferences sharedPreferences = getPreference(name);
        return sharedPreferences == null ? defaultValue : sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     从App默认文档中读取一个浮点型数据

     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static float readFloat(@NonNull String key, float defaultValue) {
        SharedPreferences sharedPreferences = getPreference();
        return sharedPreferences == null ? defaultValue : sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     从指定文档文档中读取一个整型数据

     @param name 文档名
     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static int readInt(String name, @NonNull String key, int defaultValue) {
        SharedPreferences sharedPreferences = getPreference(name);
        return sharedPreferences == null ? defaultValue : sharedPreferences.getInt(key, defaultValue);
    }

    /**
     从App默认文档中读取一个整型数据

     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static int readInt(@NonNull String key, int defaultValue) {
        SharedPreferences sharedPreferences = getPreference();
        return sharedPreferences == null ? defaultValue : sharedPreferences.getInt(key, defaultValue);
    }

    /**
     从指定文档文档中读取一个长整型数据

     @param name 文档名
     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static long readLong(String name, @NonNull String key, long defaultValue) {
        SharedPreferences sharedPreferences = getPreference(name);
        return sharedPreferences == null ? defaultValue : sharedPreferences.getLong(key, defaultValue);
    }

    /**
     从App默认文档中读取一个长整型数据

     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static long readLong(@NonNull String key, long defaultValue) {
        SharedPreferences sharedPreferences = getPreference();
        return sharedPreferences == null ? defaultValue : sharedPreferences.getLong(key, defaultValue);
    }

    /**
     从指定文档文档中读取一个字符串型数据

     @param name 文档名
     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static String readString(String name, @NonNull String key, String defaultValue) {
        SharedPreferences sharedPreferences = getPreference(name);
        return sharedPreferences == null ? defaultValue : sharedPreferences.getString(key, defaultValue);
    }

    /**
     从App默认文档中读取一个字符串型数据

     @param key 索引名
     @param defaultValue 缺省索引值

     @return 索引值
     */
    public static String readString(@NonNull String key, String defaultValue) {
        SharedPreferences sharedPreferences = getPreference();
        return sharedPreferences == null ? defaultValue : sharedPreferences.getString(key, defaultValue);
    }

    /**
     将一个布尔型数据写入指定文档

     @param name 文档名
     @param key 索引名
     @param value 索引值
     */
    public static void writeBoolean(@NonNull String name, @NonNull String key, boolean value) {
        SharedPreferences sharedPreferences = getPreference(name);
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     将一个布尔型数据写入App默认文档

     @param key 索引名
     @param value 索引值
     */
    public static void writeBoolean(@NonNull String key, boolean value) {
        SharedPreferences sharedPreferences = getPreference();
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     将一个浮点型数据写入指定文档

     @param name 文档名
     @param key 索引名
     @param value 索引值
     */
    public static void writeFloat(@NonNull String name, @NonNull String key, float value) {
        SharedPreferences sharedPreferences = getPreference(name);
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     将一个浮点型数据写入App默认文档

     @param key 索引名
     @param value 索引值
     */
    public static void writeFloat(@NonNull String key, float value) {
        SharedPreferences sharedPreferences = getPreference();
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     将一个整型数据写入指定文档

     @param name 文档名
     @param key 索引名
     @param value 索引值
     */
    public static void writeInt(@NonNull String name, @NonNull String key, int value) {
        SharedPreferences sharedPreferences = getPreference(name);
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     将一个整型数据写入App默认文档

     @param key 索引名
     @param value 索引值
     */
    public static void writeInt(@NonNull String key, int value) {
        SharedPreferences sharedPreferences = getPreference();
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     将一个长整型数据写入指定文档

     @param name 文档名
     @param key 索引名
     @param value 索引值
     */
    public static void writeLong(@NonNull String name, @NonNull String key, long value) {
        SharedPreferences sharedPreferences = getPreference(name);
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     将一个长整型数据写入App默认文档

     @param key 索引名
     @param value 索引值
     */
    public static void writeLong(@NonNull String key, long value) {
        SharedPreferences sharedPreferences = getPreference();
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     将一个字符串型数据写入指定文档

     @param name 文档名
     @param key 索引名
     @param value 索引值
     */
    public static void writeString(@NonNull String name, @NonNull String key, String value) {
        SharedPreferences sharedPreferences = getPreference(name);
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     将一个字符串型数据写入App默认文档

     @param key 索引名
     @param value 索引值
     */
    public static void writeString(@NonNull String key, String value) {
        SharedPreferences sharedPreferences = getPreference();
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
