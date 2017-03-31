package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.common.toaster.Toaster;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.network.IRequestCallback;

/**
 Created by Harreke on 2016/1/12.
 */
@InjectLayout
@InjectToolbar
public class LoaderActivity extends ActivityFramework {
    @InjectView
    ImageView loader_image;
    @InjectView
    TextView loader_text;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
    }

    @Override
    public void launch() {
    }

    @InjectClick
    void loader_load_image() {
        showToast("正在加载图片", Toaster.DURATION_INFINITE);
        obtainImageExecutor("load_pic").request("http://img1.3lian.com/2015/w7/85/d/102.jpg")
                .imageView(loader_image)
                .requestCallback(new IRequestCallback<Bitmap>() {
                    @Override
                    public void onFailure() {
                        showToast("图片加载失败");
                    }

                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        hideToast();
                    }
                })
                .execute(this);
    }

    @InjectClick
    void loader_load_text() {
        showToast("正在加载文本", Toaster.DURATION_INFINITE);
        obtainStringExecutor("load_text").request("http://wapp.baidu.com").requestCallback(new IRequestCallback<String>() {
            @Override
            public void onFailure() {
                showToast("文本加载失败");
            }

            @Override
            public void onSuccess(String result) {
                hideToast();
                loader_text.setText(result);
            }
        }).execute(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}