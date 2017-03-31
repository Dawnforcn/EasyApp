package com.harreke.easyapp.util.weakreference;

import android.support.annotation.NonNull;

import com.harreke.easyapp.common.interf.IDestroyable;

import java.lang.ref.WeakReference;

/**
 Created by Harreke on 2016/3/4.
 */
public abstract class WeakReferenceGetter<OBJECT> implements IDestroyable {
    private WeakReference<OBJECT> mRef;

    public WeakReferenceGetter(@NonNull OBJECT object) {
        mRef = new WeakReference<>(object);
    }

    @Override
    public final void destroy() {
        mRef.clear();
    }

    public final void get() {
        OBJECT object = mRef.get();

        if (object != null) {
            get(object);
        }
    }

    public abstract void get(OBJECT object);
}