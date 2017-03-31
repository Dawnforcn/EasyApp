package com.harreke.easyapp.pager;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.harreke.easyapp.util.StringUtil;

import java.util.WeakHashMap;
import java.util.regex.Matcher;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/08/06
 */
public class FragmentPagerFramework extends PagerFramework {
    private WeakHashMap<Integer, Fragment> mFragmentMap = new WeakHashMap<>();
    private IFragmentParser mFragmentParser = null;
    private FragmentManager mManager = null;

    public FragmentPagerFramework(@NonNull FragmentManager manager, @NonNull View parent) {
        super(parent, R.id.view_pager_root);
        mManager = manager;
    }

    public FragmentPagerFramework(@NonNull Fragment fragment, int pagerRootId) {
        super(fragment.getView(), pagerRootId);
        mManager = fragment.getChildFragmentManager();
    }

    public FragmentPagerFramework(@NonNull Fragment fragment) {
        super(fragment, R.id.view_pager_root);
        mManager = fragment.getChildFragmentManager();
    }

    public FragmentPagerFramework(@NonNull FragmentActivity activity) {
        super(activity, R.id.view_pager_root);
        mManager = activity.getSupportFragmentManager();
    }

    public FragmentPagerFramework(@NonNull FragmentActivity activity, int pagerRootId) {
        super(activity.getWindow().getDecorView(), pagerRootId);
        mManager = activity.getSupportFragmentManager();
    }

    public FragmentPagerFramework(@NonNull FragmentManager manager, @NonNull View parent, int pagerRootId) {
        super(parent, pagerRootId);
        mManager = manager;
    }

    private void checkFragmentParser() {
        if (mFragmentParser == null) {
            throw new IllegalStateException("Cannot find a fragment parser for fragment generation!");
        }
    }

    @Override
    protected PagerAdapter createAdapter() {
        checkFragmentParser();

        return new Adapter();
    }

    public int getFragmentPosition(@NonNull String tag) {
        Matcher matcher = StringUtil.getMatcher("android:switcher:" + getViewPager().getId() + ":([0-9]*)", tag);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            return -1;
        }
    }

    @Override
    public final void recreatePage(int position) {
        String tag = mFragmentParser.getFragmentTag(position);
        Fragment fragment = mManager.findFragmentByTag(tag);
        if (fragment != null) {
            FragmentTransaction transaction = mManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
        }
        mFragmentMap.remove(position);
        refreshAll();
    }

    public void setFragmentParser(IFragmentParser fragmentParser) {
        mFragmentParser = fragmentParser;
    }

    public class Adapter extends PagerAdapter {
        private Fragment mCurrentPrimaryItem = null;

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            FragmentTransaction transaction = mManager.beginTransaction();
            if (transaction != null) {
                transaction.detach((Fragment) object);
                transaction.commit();
            }
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            FragmentTransaction transaction = mManager.beginTransaction();
            if (transaction != null) {
                transaction.commitAllowingStateLoss();
                mManager.executePendingTransactions();
            }
        }

        @Override
        public int getCount() {
            return mFragmentParser.getFragmentCount();
        }

        public Fragment getItem(int position) {
            Fragment fragment = mFragmentParser.createFragment(position);

            mFragmentMap.put(position, fragment);

            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            //noinspection SuspiciousMethodCalls
            return mFragmentMap.containsValue(object) ? POSITION_UNCHANGED : POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentParser.getFragmentTitle(position);
        }

        @Override
        public float getPageWidth(int position) {
            return mFragmentParser.getFragmentWidthScale(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            FragmentTransaction transaction = mManager.beginTransaction();
            if (transaction == null) {
                return null;
            }
            String tag = mFragmentParser.getFragmentTag(position);
            Fragment fragment = mManager.findFragmentByTag(tag);
            if (fragment != null) {
                transaction.attach(fragment);
                transaction.commit();
            } else {
                fragment = getItem(position);
                transaction.add(container.getId(), fragment, tag);
                transaction.commit();
            }
            if (fragment != mCurrentPrimaryItem) {
                fragment.setMenuVisibility(false);
                fragment.setUserVisibleHint(false);
            }

            return fragment;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return ((Fragment) object).getView() == view;
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            Fragment fragment = (Fragment) object;
            if (fragment != mCurrentPrimaryItem) {
                if (mCurrentPrimaryItem != null) {
                    mCurrentPrimaryItem.setMenuVisibility(false);
                    mCurrentPrimaryItem.setUserVisibleHint(false);
                }
                if (fragment != null) {
                    fragment.setMenuVisibility(true);
                    fragment.setUserVisibleHint(true);
                }
                mCurrentPrimaryItem = fragment;
            }
        }

        @Override
        public void startUpdate(ViewGroup container) {
        }
    }
}