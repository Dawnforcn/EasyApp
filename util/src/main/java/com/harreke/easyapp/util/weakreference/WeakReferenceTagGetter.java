package com.harreke.easyapp.util.weakreference;

import android.support.annotation.NonNull;

import com.harreke.easyapp.common.interf.IDestroyable;

import java.lang.ref.WeakReference;

/**
 Created by Harreke on 2016/3/4.
 */
public abstract class WeakReferenceTagGetter<OBJECT> implements IDestroyable {
    private WeakReference<OBJECT> mRef;

    public WeakReferenceTagGetter(@NonNull OBJECT object) {
        mRef = new WeakReference<>(object);
    }

    @Override
    public final void destroy() {
        mRef.clear();
    }

    public final void get(@NonNull String tag) {
        OBJECT object = mRef.get();

        if (object != null) {
            get(tag, object);
        }
    }

    public abstract void get(String tag, OBJECT object);
}