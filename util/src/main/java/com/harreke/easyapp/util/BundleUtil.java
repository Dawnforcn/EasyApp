package com.harreke.easyapp.util;

import android.os.Bundle;

import java.io.Serializable;

/**
 * 由启圣于2015/8/13创建
 */
public class BundleUtil {
    public static Bundle create(String key, String value) {
        Bundle bundle = new Bundle();
        
        bundle.putString(key, value);
        
        return bundle;
    }
    
    public static Bundle create(String key, int value) {
        Bundle bundle = new Bundle();
        
        bundle.putInt(key, value);
        
        return bundle;
    }
    
    public static Bundle create(String key, long value) {
        Bundle bundle = new Bundle();
        
        bundle.putLong(key, value);
        
        return bundle;
    }
    
    public static Bundle create(String key, float value) {
        Bundle bundle = new Bundle();
        
        bundle.putFloat(key, value);
        
        return bundle;
    }
    
    public static Bundle create(String key, Serializable value) {
        Bundle bundle = new Bundle();
        
        bundle.putSerializable(key, value);
        
        return bundle;
    }
}