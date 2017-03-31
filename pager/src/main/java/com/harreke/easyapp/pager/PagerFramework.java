package com.harreke.easyapp.pager;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/08/06
 */
public abstract class PagerFramework {
    private PagerAdapter mAdapter = null;
    private View mRootView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public PagerFramework(@NonNull View parent) {
        this(parent, R.id.view_pager_root);
    }

    public PagerFramework(@NonNull Fragment fragment, int rootViewId) {
        this(fragment.getView(), rootViewId);
    }

    public PagerFramework(@NonNull Fragment fragment) {
        this(fragment, R.id.view_pager_root);
    }

    public PagerFramework(@NonNull FragmentActivity activity) {
        this(activity, R.id.view_pager_root);
    }

    public PagerFramework(@NonNull Activity activity, int rootViewId) {
        this(activity.getWindow().getDecorView(), rootViewId);
    }

    public PagerFramework(View parent, int rootViewId) {
        mRootView = parent.findViewById(rootViewId);
        if (mRootView == null) {
            mRootView = parent.findViewById(R.id.view_pager);
        }
        if (mRootView == null) {
            throw new IllegalStateException("Cannot find a root view!");
        }
        if (mRootView instanceof ViewPager) {
            mViewPager = (ViewPager) mRootView;
        } else {
            mViewPager = (ViewPager) mRootView.findViewById(R.id.view_pager);
        }
        if (mViewPager == null) {
            throw new IllegalStateException("Cannot find a view pager!");
        }
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tab_layout);
    }

    public final void addOnPageChangeListener(
            ViewPager.OnPageChangeListener onPagerChangeListener) {
        mViewPager.addOnPageChangeListener(onPagerChangeListener);
    }

    public final void attachAdapter() {
        mAdapter = createAdapter();
        mViewPager.setAdapter(mAdapter);
        if (mTabLayout != null) {
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    protected abstract PagerAdapter createAdapter();

    public final int getCurrentPage() {
        return mViewPager.getCurrentItem();
    }

    public View getRootView() {
        return mRootView;
    }

    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public final void hide() {
        mRootView.setVisibility(View.GONE);
    }

    public final boolean isShowing() {
        return mRootView.getVisibility() == View.VISIBLE;
    }

    public final void recreateCurrentPage() {
        recreatePage(getCurrentPage());
    }

    public abstract void recreatePage(int position);

    public final void refreshAll() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public final void removeOnPageChangeListener(
            ViewPager.OnPageChangeListener onPageChangeListener) {
        mViewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    public final void setCurrentPage(int position, boolean smoothScroll) {
        mViewPager.setCurrentItem(position, smoothScroll);
    }

    public final void setOffscreenPageLimit(int limit) {
        mViewPager.setOffscreenPageLimit(limit);
    }

    public void setSelectedTabIndicatorColor(int color) {
        if (mTabLayout != null) {
            mTabLayout.setSelectedTabIndicatorColor(color);
        }
    }

    public void setSelectedTabIndicatorHeight(int height) {
        if (mTabLayout != null) {
            mTabLayout.setSelectedTabIndicatorHeight(height);
        }
    }

    public void setTabMode(int mode) {
        if (mTabLayout != null) {
            mTabLayout.setTabMode(mode);
        }
    }

    public final void setTabScrollable(boolean tabScrollable) {
        if (mTabLayout != null) {
            if (tabScrollable) {
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            } else {
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            }
        }
    }

    public void setTabTextColors(ColorStateList textColor) {
        if (mTabLayout != null) {
            mTabLayout.setTabTextColors(textColor);
        }
    }

    public void setTabTextColors(int normalColor, int selectedColor) {
        if (mTabLayout != null) {
            mTabLayout.setTabTextColors(normalColor, selectedColor);
        }
    }

    public final void show() {
        mRootView.setVisibility(View.VISIBLE);
    }
}