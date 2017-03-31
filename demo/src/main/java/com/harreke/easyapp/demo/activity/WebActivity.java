//package com.harreke.easyapp.demo.activity;
//
//import android.content.Intent;
//import android.view.MenuItem;
//import android.view.View;
//
//import com.harreke.easyapp.base.activity.ActivityFramework;
//import com.harreke.easyapp.injection.annotations.InjectLayout;
//import com.harreke.easyapp.injection.annotations.InjectToolbar;
//import com.harreke.easyapp.swipe.webview.IWebRequestor;
//import com.harreke.easyapp.swipe.webview.WebFramework;
//
///**
// * Created by Harreke on 2016/1/12.
// */
//@InjectLayout
//@InjectToolbar
//public class WebActivity extends ActivityFramework implements IWebRequestor {
//    private WebFramework mWeb;
//
//    @Override
//    public void argument(Intent intent) {
//    }
//
//    @Override
//    public void config() {
//        mWeb = new WebFramework(this);
//        mWeb.setShowRetryWhenEmptyIdle(false);
//        mWeb.setDataRequestor(this);
//    }
//
//    @Override
//    public void launch() {
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (mWeb != null && mWeb.canGoBack()) {
//            mWeb.goBack();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public void onFinishRequest() {
//    }
//
//    public void onLoadClick(View view) {
//        onRequestData();
//    }
//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        return false;
//    }
//
//    @Override
//    public void onRequestData() {
//        mWeb.load("http://cn.bing.com");
//    }
//}