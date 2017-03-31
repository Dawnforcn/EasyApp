package com.harreke.easyapp.util.weakreference;

import android.support.annotation.NonNull;

import com.harreke.easyapp.common.interf.IDestroyable;

import java.lang.ref.WeakReference;

/**
 Created by Harreke on 2016/3/4.
 */
public abstract class WeakReferenceRunnable<OBJECT> implements Runnable, IDestroyable {
    private WeakReference<OBJECT> mRef;

    public WeakReferenceRunnable(@NonNull OBJECT object) {
        mRef = new WeakReference<>(object);
    }

    @Override
    public final void destroy() {
        mRef.clear();
    }

    @Override
    public final void run() {
        OBJECT object = mRef.get();

        if (object != null) {
            run(object);
        }
    }

    public abstract void run(OBJECT object);
}