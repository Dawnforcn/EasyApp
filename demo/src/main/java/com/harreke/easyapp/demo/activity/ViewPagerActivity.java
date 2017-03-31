package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.demo.R;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.pager.IViewParser;
import com.harreke.easyapp.pager.ViewPagerFramework;

import java.util.Locale;

/**
 由Harreke于2016/1/12创建
 */
@InjectLayout
@InjectToolbar
public class ViewPagerActivity extends ActivityFramework implements IViewParser {
    private ViewPagerFramework mViewPagerFramework;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        mViewPagerFramework = new ViewPagerFramework(this);
        mViewPagerFramework.setViewParser(this);
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, int position) {
        SimpleDraweeView view = (SimpleDraweeView) inflater.inflate(R.layout.widget_viewpager, container, false);
        view.setImageURI("http://img1.3lian.com/2015/w7/85/d/102.jpg");
        return view;
    }

    @Override
    public int getViewCount() {
        return 5;
    }

    @Override
    public CharSequence getViewTitle(int position) {
        return String.format(Locale.getDefault(), "第%d张", (position + 1));
    }

    @Override
    public void launch() {
        mViewPagerFramework.attachAdapter();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}