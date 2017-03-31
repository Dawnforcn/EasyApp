package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.demo.fragment.FragmentDataFragment;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.pager.FragmentPagerFramework;
import com.harreke.easyapp.pager.IFragmentParser;

/**
 由 Harreke 于 2016/1/12 创建
 */
@InjectLayout
@InjectToolbar
public class FragmentDataActivity extends ActivityFramework implements IFragmentParser {
    @InjectView
    EditText fragment_data_input;
    @InjectView
    TextView fragment_data_toast;
    FragmentPagerFramework mFragmentPagerFramework;

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
        return new FragmentDataFragment();
    }

    @InjectClick
    void fragment_data_send1() {
        String input = fragment_data_input.getText().toString();

        if (input.length() > 0) {
            sendDataToFragment(getFragmentTag(0), "message", input);
        } else {
            showToast("发送的内容不能为空");
        }
    }

    @InjectClick
    void fragment_data_send2() {
        String input = fragment_data_input.getText().toString();

        if (input.length() > 0) {
            sendDataToFragment(getFragmentTag(1), "message", input);
        } else {
            showToast("发送的内容不能为空");
        }
    }

    @Override
    public int getFragmentCount() {
        return 2;
    }

    @Override
    public String getFragmentTag(int position) {
        return getFragmentTitle(position);
    }

    @Override
    public String getFragmentTitle(int position) {
        return "Fragment " + (position + 1);
    }

    @Override
    public float getFragmentWidthScale(int position) {
        return 1f;
    }

    @Override
    public void launch() {
        mFragmentPagerFramework.attachAdapter();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onReceiveDataFromFragment(@NonNull String tag, @NonNull String name, @Nullable Object data) {
        int position = mFragmentPagerFramework.getFragmentPosition(tag);

        fragment_data_toast.setText("收到来自Fragment " + (position + 1) + "的消息：" + String.valueOf(data));
    }
}