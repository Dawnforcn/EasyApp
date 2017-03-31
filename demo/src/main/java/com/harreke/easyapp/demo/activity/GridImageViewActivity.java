package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.MenuItem;
import android.widget.ImageView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.widget.gridview.GridImageView;

import java.util.Locale;

/**
 由Harreke于2016/1/12创建
 */
@InjectLayout
@InjectToolbar
public class GridImageViewActivity extends ActivityFramework implements GridImageView.OnGridClickListener {
    @InjectView
    GridImageView grid_image;
    @InjectView
    ImageView grid_target;
    Bitmap mBitmap = null;

    @Override
    public void argument(Intent intent) {
    }

    @Override
    public void config() {
        grid_image.setOnGridClickListener(this);
    }

    @Override
    public void launch() {
    }

    @Override
    public void onGridClick(int row, int line) {
        showToast(String.format(Locale.getDefault(), "点击了%d行%d列", (row + 1), (line + 1)));
        if (mBitmap != null) {
            mBitmap.recycle();
        }
        mBitmap = grid_image.getBitmap(row, line);
        grid_target.setImageBitmap(mBitmap);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}