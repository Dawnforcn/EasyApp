package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.helper.PaletteHelper;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.network.IRequestCallback;

/**
 由 Harreke 于 2016/1/12 创建
 */
@InjectLayout
@InjectToolbar
public class PaletteActivity extends ActivityFramework {
    PaletteHelper mPaletteHelper;
    @InjectView
    ImageView palette_image1;
    @InjectView
    View palette_image1_background;
    @InjectView
    TextView palette_image1_body;
    @InjectView
    TextView palette_image1_title;
    @InjectView
    ImageView palette_image2;
    @InjectView
    View palette_image2_background;
    @InjectView
    TextView palette_image2_body;
    @InjectView
    TextView palette_image2_title;
    @InjectView
    ImageView palette_image3;
    @InjectView
    View palette_image3_background;
    @InjectView
    TextView palette_image3_body;
    @InjectView
    TextView palette_image3_title;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        mPaletteHelper = new PaletteHelper(this);
    }

    @Override
    public void launch() {
        obtainImageExecutor("palette_image1").request("http://uc.douyutv.com/upload/avatar/001/73/45/77_avatar_big.jpg")
                .imageView(palette_image1)
                .requestCallback(new IRequestCallback<Bitmap>() {
                    @Override
                    public void onFailure() {
                    }

                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        mPaletteHelper.parse(bitmap);
                        palette_image1_background.setBackgroundColor(mPaletteHelper.getBackgroundColor());
                        palette_image1_title.setTextColor(mPaletteHelper.getTitleTextColor());
                        palette_image1_body.setTextColor(mPaletteHelper.getBodyTextColor());
                    }
                })
                .execute(this);
        obtainImageExecutor("palette_image2").request("http://uc.douyutv.com/upload/avatar/001/73/45/78_avatar_big.jpg")
                .imageView(palette_image2)
                .requestCallback(new IRequestCallback<Bitmap>() {
                    @Override
                    public void onFailure() {
                    }

                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        mPaletteHelper.parse(bitmap);
                        palette_image2_background.setBackgroundColor(mPaletteHelper.getBackgroundColor());
                        palette_image2_title.setTextColor(mPaletteHelper.getTitleTextColor());
                        palette_image2_body.setTextColor(mPaletteHelper.getBodyTextColor());
                    }
                })
                .execute(this);
        obtainImageExecutor("palette_image3").request("http://uc.douyutv.com/upload/avatar/001/73/45/79_avatar_big.jpg")
                .imageView(palette_image3)
                .requestCallback(new IRequestCallback<Bitmap>() {
                    @Override
                    public void onFailure() {
                    }

                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        mPaletteHelper.parse(bitmap);
                        palette_image3_background.setBackgroundColor(mPaletteHelper.getBackgroundColor());
                        palette_image3_title.setTextColor(mPaletteHelper.getTitleTextColor());
                        palette_image3_body.setTextColor(mPaletteHelper.getBodyTextColor());
                    }
                })
                .execute(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}