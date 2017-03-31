package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.demo.fragment.FragmentDataFragment;
import com.harreke.easyapp.demo.fragment.HelloworldFragment;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.pager.FragmentPagerFramework;
import com.harreke.easyapp.pager.IFragmentParser;

import java.util.Locale;

/**
 由Harreke于2016/1/12创建
 */
@InjectLayout
@InjectToolbar
public class FragmentPagerActivity extends ActivityFramework implements IFragmentParser {
    private FragmentPagerFramework mFragmentPagerFramework;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        mFragmentPagerFramework = new FragmentPagerFramework(this);
        mFragmentPagerFramework.setFragmentParser(this);
    }

    @Override
    public Fragment createFragment(int position) {
        return new HelloworldFragment();
    }

    @Override
    public int getFragmentCount() {
        return 3;
    }

    @Override
    public String getFragmentTag(int position) {
        return getFragmentTitle(position);
    }

    @Override
    public String getFragmentTitle(int position) {
        return String.format(Locale.getDefault(), "第%d项", (position + 1));
    }

    @Override
    public float getFragmentWidthScale(int position) {
        return 1f;
    }

    @Override
    public int getManagerFragmentCount() {
        return super.getManagerFragmentCount();
    }

    @Override
    public void launch() {
        mFragmentPagerFramework.attachAdapter();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}