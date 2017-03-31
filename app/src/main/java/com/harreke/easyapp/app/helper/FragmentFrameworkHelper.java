package com.harreke.easyapp.app.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.harreke.easyapp.app.IFramework;
import com.harreke.easyapp.app.IToaster;
import com.harreke.easyapp.app.MeasureContent;
import com.harreke.easyapp.app.activity.IActivityData;
import com.harreke.easyapp.app.activity.IActivityOtherFragmentData;
import com.harreke.easyapp.injection.Injection;
import com.harreke.easyapp.util.StarterUtil;
import com.harreke.easyapp.util.StringUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 由huoqisheng于2016/9/14创建
 */
public class FragmentFrameworkHelper extends FrameworkHelper {
    private WeakReference<IActivityData> mActivityDataRef = null;
    private WeakReference<IActivityOtherFragmentData> mActivityOtherFragmentDataRef = null;
    private WeakReference<IToaster> mToasterRef = null;
    private boolean mUseContainerLayout = false;

    public FragmentFrameworkHelper(IFramework framework, boolean useContainerLayout) {
        super(framework);
        mUseContainerLayout = useContainerLayout;
    }

    private Fragment findChildFragment(Fragment fragment, String tag) {
        List<Fragment> subFragmentList = fragment.getChildFragmentManager().getFragments();
        if (subFragmentList == null) {
            return null;
        }
        for (Fragment subFragment : subFragmentList) {
            Fragment result = findChildFragment(subFragment, tag);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private IActivityData getActivityData() {
        return mActivityDataRef == null ? null : mActivityDataRef.get();
    }

    private IActivityOtherFragmentData getActivityOtherFragmentData() {
        return mActivityOtherFragmentDataRef == null ? null : mActivityOtherFragmentDataRef.get();
    }

    public Fragment getChildFragment(String tag) {
        IFramework framework = getFramework();
        if (framework == null) {
            return null;
        }
        List<Fragment> fragmentList = framework.getFragment().getChildFragmentManager().getFragments();
        if (fragmentList == null) {
            return null;
        }
        for (Fragment fragment : fragmentList) {
            if (StringUtil.equals(fragment.getTag(), tag)) {
                return fragment;
            }
        }
        for (Fragment fragment : fragmentList) {
            Fragment result = findChildFragment(fragment, tag);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private IToaster getToaster() {
        return mToasterRef == null ? null : mToasterRef.get();
    }

    public void hideToast() {
        IToaster toast = getToaster();

        if (toast != null) {
            toast.hideToast();
        }
    }

    public void onAttach(Context context) {
        mToasterRef = new WeakReference<>((IToaster) context);
        mActivityDataRef = new WeakReference<>((IActivityData) context);
        mActivityOtherFragmentDataRef = new WeakReference<>((IActivityOtherFragmentData) context);
    }

    public View onCreateView(LayoutInflater inflater) {
        IFramework framework = getFramework();
        if (framework == null) {
            return null;
        }
        Context context = framework.getContext();
        MeasureContent measureContent = new MeasureContent(context);
        setMeasureContent(measureContent);
        View contentView;
        int contentLayoutId = Injection.injectLayout(framework, context);
        if (contentLayoutId > 0) {
            if (mUseContainerLayout) {
                contentView = framework.getActivity().findViewById(contentLayoutId);
            } else {
                contentView = inflater.inflate(contentLayoutId, measureContent, false);
            }
            onCreate(contentView);
        }

        return measureContent;
    }

    public void onDetach() {
        if (mToasterRef != null) {
            mToasterRef.clear();
            mToasterRef = null;
        }
        if (mActivityDataRef != null) {
            mActivityDataRef.clear();
            mActivityDataRef = null;
        }
        if (mActivityOtherFragmentDataRef != null) {
            mActivityOtherFragmentDataRef.clear();
            mActivityOtherFragmentDataRef = null;
        }
    }

    public final void sendDataToActivity(@NonNull String tag, @NonNull String name, @Nullable Object data) {
        IActivityData activityData = getActivityData();

        if (activityData != null) {
            activityData.onReceiveDataFromFragment(tag, name, data);
        }
    }

    public void sendDataToOtherFragment(@NonNull String senderTag, @NonNull String receiverTag, @NonNull String name, @Nullable Object data) {
        IActivityOtherFragmentData activityOtherFragmentData = getActivityOtherFragmentData();

        if (activityOtherFragmentData != null) {
            activityOtherFragmentData.onReceiveOtherFragmentData(senderTag, receiverTag, name, data);
        }
    }

    public void setContentView(View contentView) {
        MeasureContent measureContent = getMeasureContent();
        measureContent.removeAllViews();
        if (contentView != null) {
            onCreate(contentView);
        }
    }

    public void setContentView(int contentLayoutId) {
        MeasureContent measureContent = getMeasureContent();
        if (contentLayoutId > 0) {
            View contentView = LayoutInflater.from(measureContent.getContext()).inflate(contentLayoutId, measureContent, false);
            setContentView(contentView);
        }
    }

    public void showToast(int textId, long duration) {
        IToaster toaster = getToaster();

        if (toaster != null) {
            toaster.showToast(textId, duration);
        }
    }

    public void showToast(String text, long duration) {
        IToaster toaster = getToaster();

        if (toaster != null) {
            toaster.showToast(text, duration);
        }
    }

    public void showToast(String text) {
        IToaster toaster = getToaster();

        if (toaster != null) {
            toaster.showToast(text);
        }
    }

    public void showToast(int textId) {
        IToaster toaster = getToaster();

        if (toaster != null) {
            toaster.showToast(textId);
        }
    }

    public void start(@NonNull Intent intent) {
        start(intent, 0, null);
    }

    public void start(@NonNull Intent intent, @Nullable ActivityOptionsCompat options) {
        start(intent, 0, options);
    }

    public void start(@NonNull Intent intent, int requestCode) {
        start(intent, requestCode, null);
    }

    public void start(@NonNull Intent intent, int requestCode, @Nullable ActivityOptionsCompat options) {
        IFramework framework = getFramework();
        if (framework != null) {
            StarterUtil.start(framework.getFragment(), intent, requestCode, options);
        }
    }
}