package com.harreke.easyapp.app.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harreke.easyapp.app.IFramework;
import com.harreke.easyapp.app.helper.FragmentFrameworkHelper;
import com.harreke.easyapp.common.interf.IDestroyable;
import com.harreke.easyapp.network.executor.BinaryExecutor;
import com.harreke.easyapp.network.executor.ImageExecutor;
import com.harreke.easyapp.network.executor.StringExecutor;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/07/24

 Fragment框架
 */
public abstract class FragmentFramework extends Fragment implements IFramework, IFragment, IOtherFragmentData {
    private FragmentFrameworkHelper mFragmentFrameworkHelper;

    /**
     构造Fragment框架

     @param useContainerLayout 是否使用容器作为布局

     注：如果设置为true，则会使用{@link View#findViewById(int)}容器里的指定视图作为该Fragment的内容，指定视图的id为该Fragment的对象名称
     如果设置为false，则会从xml文件中加载{@link LayoutInflater#inflate(int, ViewGroup)}一个布局作为该Fragment的内容，布局id为该Fragment的对象名称
     */
    public FragmentFramework(boolean useContainerLayout) {
        mFragmentFrameworkHelper = new FragmentFrameworkHelper(this, useContainerLayout);
    }

    public FragmentFramework() {
        mFragmentFrameworkHelper = new FragmentFrameworkHelper(this, false);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final void addContentView(View view, ViewGroup.LayoutParams params) {
        mFragmentFrameworkHelper.addContentView(view, params);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final View findViewById(int viewId) {
        return mFragmentFrameworkHelper.findViewById(viewId);
    }

    @Override
    public Fragment getChildFragment(String tag) {
        return mFragmentFrameworkHelper.getChildFragment(tag);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Context getContext() {
        return getActivity();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Nullable
    @Override
    public View getView() {
        return mFragmentFrameworkHelper.getView();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void hideToast() {
        mFragmentFrameworkHelper.hideToast();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public boolean isActivity() {
        return false;
    }

    public boolean isResume() {
        return mFragmentFrameworkHelper.isResume();
    }

    public boolean isViewInitialized() {
        return mFragmentFrameworkHelper.isViewInitialized();
    }

    @Override
    public BinaryExecutor obtainBinaryExecutor(@NonNull String tag) {
        return mFragmentFrameworkHelper.obtainBinaryExecutor(tag);
    }

    @Override
    public ImageExecutor obtainImageExecutor(@NonNull String tag) {
        return mFragmentFrameworkHelper.obtainImageExecutor(tag);
    }

    @Override
    public StringExecutor obtainStringExecutor(@NonNull String tag) {
        return mFragmentFrameworkHelper.obtainStringExecutor(tag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentFrameworkHelper.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        argument(getArguments());
        return mFragmentFrameworkHelper.onCreateView(inflater);
    }

    @Override
    public void onDestroyView() {
        mFragmentFrameworkHelper.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        mFragmentFrameworkHelper.onDetach();
        super.onDetach();
    }

    @Override
    public void onPause() {
        mFragmentFrameworkHelper.onPause();
        super.onPause();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void onReceiveDataFromActivity(@NonNull String name, @Nullable Object data) {
    }

    @Override
    public void onReceiveDataFromOtherFragment(@NonNull String senderTag, @NonNull String name, @Nullable Object data) {
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragmentFrameworkHelper.onResume();
    }

    @Override
    public void post(@NonNull Runnable runnable) {
        mFragmentFrameworkHelper.post(runnable);
    }

    @Override
    public void postDelayed(@NonNull Runnable runnable, long delay) {
        mFragmentFrameworkHelper.postDelayed(runnable, delay);
    }

    /**
     注册一个可销毁的对象

     该对象将在Fragment销毁时自动销毁

     @param destroyable 柯晓辉的对象
     */
    @Override
    public final void registerDestroyable(@NonNull IDestroyable destroyable) {
        mFragmentFrameworkHelper.registerDestroyable(destroyable);
    }

    @Override
    public void registerDialog(@NonNull Dialog dialog) {
        mFragmentFrameworkHelper.registerDialog(dialog);
    }

    @Override
    public void registerRunnable(@NonNull Runnable runnable) {
        mFragmentFrameworkHelper.registerRunnable(runnable);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final void sendDataToActivity(@NonNull String name, @Nullable Object data) {
        mFragmentFrameworkHelper.sendDataToActivity(getTag(), name, data);
    }

    @Override
    public void sendDataToOtherFragment(@NonNull String receiverTag, @NonNull String name, @Nullable Object data) {
        mFragmentFrameworkHelper.sendDataToOtherFragment(getTag(), receiverTag, name, data);
    }

    public void setContentView(@LayoutRes int layoutResID) {
        mFragmentFrameworkHelper.setContentView(layoutResID);
    }

    public void setContentView(View contentView) {
        mFragmentFrameworkHelper.setContentView(contentView);
    }

    /**
     设置刷新间隔

     当该Fragment被暂停（{@link #onPause()}）后，重新运作（{@link #onResume()}）时，会检测暂停总时长。若大于刷新间隔时间，则会触发{@link #onRefresh()}函数要求刷新Fragment的内容。

     @param refreshTime 刷新间隔，单位为毫秒
     设置-1则禁止刷新检测
     */
    public final void setRefreshTime(long refreshTime) {
        mFragmentFrameworkHelper.setRefreshTime(refreshTime);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(int textId) {
        mFragmentFrameworkHelper.showToast(textId);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(String text) {
        mFragmentFrameworkHelper.showToast(text);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(int textId, long duration) {
        mFragmentFrameworkHelper.showToast(textId, duration);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(String text, long duration) {
        mFragmentFrameworkHelper.showToast(text, duration);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent) {
        mFragmentFrameworkHelper.start(intent);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent, @Nullable ActivityOptionsCompat options) {
        mFragmentFrameworkHelper.start(intent, options);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent, int requestCode) {
        mFragmentFrameworkHelper.start(intent, requestCode);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent, int requestCode, @Nullable ActivityOptionsCompat options) {
        mFragmentFrameworkHelper.start(intent, requestCode, options);
    }
}