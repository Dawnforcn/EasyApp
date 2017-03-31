package com.harreke.easyapp.pager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.WeakHashMap;

/**
 * 由Harreke于2016/1/5创建
 */
public class ViewPagerFramework extends PagerFramework {
    private WeakHashMap<Integer, View> mViewMap = new WeakHashMap<>();
    private IViewParser mViewParser = null;

    public ViewPagerFramework(@NonNull View parent) {
        super(parent);
    }

    public ViewPagerFramework(@NonNull Fragment fragment, int pagerRootId) {
        super(fragment, pagerRootId);
    }

    public ViewPagerFramework(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerFramework(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public ViewPagerFramework(@NonNull Activity activity, int pagerRootId) {
        super(activity, pagerRootId);
    }

    public ViewPagerFramework(@NonNull View parent, int pagerRootId) {
        super(parent, pagerRootId);
    }

    private void checkViewParser() {
        if (mViewParser == null) {
            throw new IllegalStateException("Cannot find a view parser for view generation!");
        }
    }

    @Override
    protected PagerAdapter createAdapter() {
        checkViewParser();

        return new Adapter();
    }

    @Override
    public void recreatePage(int position) {
        mViewMap.remove(position);
        refreshAll();
    }

    public void setViewParser(IViewParser viewParser) {
        mViewParser = viewParser;
    }

    private class Adapter extends PagerAdapter {
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mViewParser.getViewCount();
        }

        @Override
        public int getItemPosition(Object object) {
            //noinspection SuspiciousMethodCalls
            return mViewMap.containsValue(object) ? POSITION_UNCHANGED : POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mViewParser.getViewTitle(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViewParser.createView(LayoutInflater.from(container.getContext()), container, position);

            container.addView(view);
            mViewMap.put(position, view);

            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
