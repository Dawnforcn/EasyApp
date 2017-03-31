package com.harreke.easyapp.app.helper;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.harreke.easyapp.EasyNetwork;
import com.harreke.easyapp.app.IFramework;
import com.harreke.easyapp.app.MeasureContent;
import com.harreke.easyapp.common.interf.IDestroyable;
import com.harreke.easyapp.common.singleton.CommonHandler;
import com.harreke.easyapp.injection.Injection;
import com.harreke.easyapp.network.executor.BinaryExecutor;
import com.harreke.easyapp.network.executor.ImageExecutor;
import com.harreke.easyapp.network.executor.RequestExecutor;
import com.harreke.easyapp.network.executor.StringExecutor;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 由huoqisheng于2016/9/14创建
 */
public class FrameworkHelper {
    private WeakHashMap<Integer, IDestroyable> mDestroyableRefMap = new WeakHashMap<>();
    private WeakHashMap<Integer, Dialog> mDialogRefMap = new WeakHashMap<>();
    private WeakHashMap<String, RequestExecutor> mExecutorRefMap = new WeakHashMap<>();
    private WeakReference<IFramework> mFrameworkRef;
    private MeasureContent mMeasureContent = null;
    private MeasureContent.OnMeasuredListener mMeasureListener = new MeasureContent.OnMeasuredListener() {
        @Override
        public void onMeasured() {
            IFramework framework = getFramework();
            if (framework != null) {
                framework.launch();
            }
        }
    };
    private long mPauseTime = 0L;
    private long mRefreshTime = -1L;
    private boolean mResume = false;
    private WeakHashMap<Integer, Runnable> mRunnableRefMap = new WeakHashMap<>();

    FrameworkHelper(IFramework framework) {
        mFrameworkRef = new WeakReference<>(framework);
    }

    public final void addContentView(View view, ViewGroup.LayoutParams params) {
        if (mMeasureContent != null) {
            mMeasureContent.addView(view, params);
        }
    }

    private void destroyAll() {
        for (IDestroyable destroyable : mDestroyableRefMap.values()) {
            if (destroyable != null) {
                destroyable.destroy();
            }
        }
        mDestroyableRefMap.clear();
        for (Dialog dialog : mDialogRefMap.values()) {
            if (dialog != null) {
                dialog.dismiss();
            }
        }
        mDialogRefMap.clear();
        for (Runnable runnable : mRunnableRefMap.values()) {
            if (runnable != null) {
                CommonHandler.getInstance().remove(runnable);
            }
        }
        mRunnableRefMap.clear();
        for (RequestExecutor requestExecutor : mExecutorRefMap.values()) {
            if (requestExecutor != null) {
                requestExecutor.cancel();
            }
        }
        mExecutorRefMap.clear();
    }

    public View findViewById(int viewId) {
        return mMeasureContent == null ? null : mMeasureContent.findViewById(viewId);
    }

    IFramework getFramework() {
        return mFrameworkRef == null ? null : mFrameworkRef.get();
    }

    MeasureContent getMeasureContent() {
        return mMeasureContent;
    }

    public View getView() {
        return mMeasureContent;
    }

    public boolean isResume() {
        return mResume;
    }

    public boolean isViewInitialized() {
        return mMeasureContent != null && mMeasureContent.isMeasured();
    }


    public BinaryExecutor obtainBinaryExecutor(@NonNull String tag) {
        RequestExecutor requestExecutor = mExecutorRefMap.get(tag);
        if (requestExecutor != null) {
            requestExecutor.destroy();
            if (requestExecutor instanceof BinaryExecutor) {
                return (BinaryExecutor) requestExecutor;
            } else {
                mExecutorRefMap.remove(tag);
            }
        }
        requestExecutor = EasyNetwork.createBinaryExecutor();
        mExecutorRefMap.put(tag, requestExecutor);
        return (BinaryExecutor) requestExecutor;
    }

    public ImageExecutor obtainImageExecutor(@NonNull String tag) {
        RequestExecutor requestExecutor = mExecutorRefMap.get(tag);
        if (requestExecutor != null) {
            requestExecutor.destroy();
            if (requestExecutor instanceof ImageExecutor) {
                return (ImageExecutor) requestExecutor;
            } else {
                mExecutorRefMap.remove(tag);
            }
        }
        requestExecutor = EasyNetwork.createImageExecutor();
        mExecutorRefMap.put(tag, requestExecutor);
        return (ImageExecutor) requestExecutor;
    }

    public StringExecutor obtainStringExecutor(@NonNull String tag) {
        RequestExecutor requestExecutor = mExecutorRefMap.get(tag);
        if (requestExecutor != null) {
            Logger.e("obtain destroy");
            requestExecutor.destroy();
            if (requestExecutor instanceof StringExecutor) {
                return (StringExecutor) requestExecutor;
            } else {
                mExecutorRefMap.remove(tag);
            }
        }
        requestExecutor = EasyNetwork.createStringExecutor();
        mExecutorRefMap.put(tag, requestExecutor);
        return (StringExecutor) requestExecutor;
    }

    void onCreate(View contentView) {
        IFramework framework = getFramework();
        if (contentView != null && framework != null) {
            mMeasureContent.addView(contentView);
            mMeasureContent.setOnMeasuredListener(mMeasureListener);
            Injection.inject(framework, mMeasureContent);
            framework.config();
            mMeasureContent.measure();
        }
    }

    public void onDestroy() {
        mMeasureContent.destroy();
        mMeasureContent = null;
        destroyAll();
    }

    public void onPause() {
        mResume = false;
        mPauseTime = System.currentTimeMillis();
    }

    public void onResume() {
        mResume = true;
        IFramework framework = getFramework();
        if (isViewInitialized() && framework != null) {
            if (mRefreshTime >= 0 && mPauseTime > 0) {
                long pausedTime = System.currentTimeMillis() - mPauseTime;
                mPauseTime = 0;
                if (pausedTime > mRefreshTime) {
                    framework.onRefresh();
                }
            }
        }
    }

    public void post(@NonNull Runnable runnable) {
        CommonHandler.getInstance().post(runnable);
        registerRunnable(runnable);
    }

    public void postDelayed(@NonNull Runnable runnable, long delay) {
        CommonHandler.getInstance().postDelayed(runnable, delay);
        registerRunnable(runnable);
    }

    public final void registerDestroyable(@NonNull IDestroyable destroyable) {
        mDestroyableRefMap.put(destroyable.hashCode(), destroyable);
    }

    public final void registerDialog(@NonNull Dialog dialog) {
        mDialogRefMap.put(dialog.hashCode(), dialog);
    }

    public final void registerRunnable(@NonNull Runnable runnable) {
        mRunnableRefMap.put(runnable.hashCode(), runnable);
    }

    public void remove(Runnable runnable) {
        CommonHandler.getInstance().remove(runnable);
        mRunnableRefMap.remove(runnable.hashCode());
    }

    void setMeasureContent(MeasureContent measureContent) {
        mMeasureContent = measureContent;
    }

    public final void setRefreshTime(long refreshTime) {
        mRefreshTime = refreshTime;
    }
}
