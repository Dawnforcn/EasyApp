package com.harreke.easyapp.app.helper;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.harreke.easyapp.app.IFramework;
import com.harreke.easyapp.app.MeasureContent;
import com.harreke.easyapp.app.activity.SwipeToFinishBehavior;
import com.harreke.easyapp.util.StarterUtil;
import com.harreke.easyapp.util.StringUtil;

import java.util.List;

/**
 由huoqisheng于2016/9/20创建
 */
public class ActivityFrameworkHelper extends FrameworkHelper implements SwipeToFinishBehavior.OnFinishListener {
    private Runnable mBackPressedRunnable = new Runnable() {
        @Override
        public void run() {
            IFramework framework = getFramework();
            if (framework != null) {
                framework.getActivity().onBackPressed();
            }
            remove(this);
        }
    };
    private boolean mSwipeToFinishEnabled = false;

    public ActivityFrameworkHelper(IFramework framework) {
        super(framework);
    }

    public final void enableSwipeToFinish() {
        if (!mSwipeToFinishEnabled) {
            mSwipeToFinishEnabled = true;
            MeasureContent measureContent = getMeasureContent();
            if (measureContent != null) {
                if (measureContent.getChildCount() > 0) {
                    measureContent.setClickable(true);
                    SwipeToFinishBehavior behavior = new SwipeToFinishBehavior();
                    behavior.setOnFinishListener(this);
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) measureContent.getChildAt(0).getLayoutParams();
                    params.setBehavior(behavior);
                }
            } else {
                throw new IllegalStateException("Must call this feature in config() method!");
            }
        }
    }

    private Fragment findFragment(Fragment fragment, String tag) {
        List<Fragment> subFragmentList = fragment.getChildFragmentManager().getFragments();
        if (subFragmentList == null) {
            return null;
        }
        for (Fragment subFragment : subFragmentList) {
            Fragment result = findFragment(subFragment, tag);
            if (result != null) {
                return result;
            }
        }
        return null;

    }

    public final Fragment getManagerFragment(String tag) {
        IFramework framework = getFramework();
        if (framework != null) {
            List<Fragment> fragmentList = framework.getActivity().getSupportFragmentManager().getFragments();
            if (fragmentList == null) {
                return null;
            }
            for (Fragment fragment : fragmentList) {
                if (StringUtil.equals(fragment.getTag(), tag)) {
                    return fragment;
                }
            }
            for (Fragment fragment : fragmentList) {
                Fragment result = findFragment(fragment, tag);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public int getManagerFragmentCount() {
        IFramework framework = getFramework();
        if (framework != null) {
            List<Fragment> fragmentList = framework.getActivity().getSupportFragmentManager().getFragments();
            if (fragmentList != null) {
                return fragmentList.size();
            }
        }
        return 0;
    }

    public boolean isSwipeToFinishEnabled() {
        return mSwipeToFinishEnabled;
    }

    public void onBackPressed(long delay) {
        remove(mBackPressedRunnable);
        postDelayed(mBackPressedRunnable, delay);
    }

    public void onCreate(Activity activity) {
        MeasureContent measureContent = new MeasureContent(activity);
        setMeasureContent(measureContent);
    }

    @Override
    public void onFinish(View view) {
        mBackPressedRunnable.run();
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
            StarterUtil.start(framework.getActivity(), intent, requestCode, options);
        }
    }
}