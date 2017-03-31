package com.harreke.easyapp.app.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.harreke.easyapp.app.IFramework;
import com.harreke.easyapp.app.fragment.IFragmentData;
import com.harreke.easyapp.app.fragment.IOtherFragmentData;
import com.harreke.easyapp.app.helper.ActivityFrameworkHelper;
import com.harreke.easyapp.common.interf.IDestroyable;
import com.harreke.easyapp.common.toaster.Toaster;
import com.harreke.easyapp.injection.Injection;
import com.harreke.easyapp.network.executor.BinaryExecutor;
import com.harreke.easyapp.network.executor.ImageExecutor;
import com.harreke.easyapp.network.executor.StringExecutor;
import com.harreke.easyapp.util.ConnectionUtil;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/07/24

 Activity框架
 */
public abstract class ActivityFramework extends AppCompatActivity
        implements IFramework, IToolbar, IActivity, IActivityOtherFragmentData, Toolbar.OnMenuItemClickListener {
    private ActivityFrameworkHelper mActivityFrameworkHelper;
    private IntentFilter mConnectionIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private BroadcastReceiver mConnectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectionUtil.checkConnection(context);
            boolean connected = ConnectionUtil.isConnected();
            if (connected != ConnectionUtil.isConnected()) {
                onConnectionChange(ConnectionUtil.isConnected());
            }
        }
    };

    /**
     {@inheritDoc}
     */
    @Override
    public void hideToast() {
        Toaster.hide(this);
    }

    private View.OnClickListener mOnNavigationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onToolbarNavigationClick();
        }
    };
    private Toolbar mToolbar = null;

    public ActivityFramework() {
        mActivityFrameworkHelper = new ActivityFrameworkHelper(this);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        mActivityFrameworkHelper.addContentView(view, params);
    }

    public final void enableSwipeToFinish() {
        mActivityFrameworkHelper.enableSwipeToFinish();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final void exit() {
        ActivityCompat.finishAfterTransition(this);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final FragmentActivity getActivity() {
        return this;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Context getContext() {
        return this;
    }

    @Override
    public final Fragment getFragment() {
        throw new IllegalStateException("Cannot call this method from ActivityFramework!");
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final Fragment getManagerFragment(String tag) {
        return mActivityFrameworkHelper.getManagerFragment(tag);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public int getManagerFragmentCount() {
        return mActivityFrameworkHelper.getManagerFragmentCount();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void hideToolbar() {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.GONE);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void hideToolbarItem(int id) {
        MenuItem item;

        if (mToolbar != null) {
            item = mToolbar.getMenu().findItem(id);
            if (item != null) {
                item.setVisible(false);
            }
        }
        //        invalidateOptionsMenu();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public boolean isActivity() {
        return true;
    }

    public boolean isResume() {
        return mActivityFrameworkHelper.isResume();
    }

    public boolean isSwipeToFinishEnabled() {
        return mActivityFrameworkHelper.isSwipeToFinishEnabled();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public boolean isToolbarShowing() {
        return mToolbar != null && mToolbar.getVisibility() == View.VISIBLE;
    }

    /**
     判断Activity的视图是否已经经过初始化，即执行过{@link #config()}

     @return Activity的视图是否已经经过初始化
     */
    public boolean isViewInitialized() {
        return mActivityFrameworkHelper.isViewInitialized();
    }

    @Override
    public BinaryExecutor obtainBinaryExecutor(@NonNull String tag) {
        return mActivityFrameworkHelper.obtainBinaryExecutor(tag);
    }

    @Override
    public ImageExecutor obtainImageExecutor(@NonNull String tag) {
        return mActivityFrameworkHelper.obtainImageExecutor(tag);
    }

    @Override
    public StringExecutor obtainStringExecutor(@NonNull String tag) {
        return mActivityFrameworkHelper.obtainStringExecutor(tag);
    }

    public final void onBackPressed(long delay) {
        mActivityFrameworkHelper.onBackPressed(delay);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    protected void onConnectionChange(boolean connected) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        argument(getIntent());
        super.onCreate(savedInstanceState);
        mActivityFrameworkHelper.onCreate(this);
        super.setContentView(mActivityFrameworkHelper.getView());
        setContentView(Injection.injectLayout(this));
    }

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        if (mToolbar != null) {
            int menuId = Injection.injectMenu(this);
            if (menuId > 0) {
                mToolbar.inflateMenu(menuId);
            }
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        Toaster.destroy(this);
        mActivityFrameworkHelper.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    protected void onPause() {
        mActivityFrameworkHelper.onPause();
        unregisterReceiver(mConnectionReceiver);
        hideToast();
        super.onPause();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void onReceiveDataFromFragment(@NonNull String tag, @NonNull String name, @Nullable Object data) {
    }

    @Override
    public void onReceiveOtherFragmentData(@NonNull String senderTag, @NonNull String receiverTag, @NonNull String name, @Nullable Object data) {
        Fragment fragment = getManagerFragment(receiverTag);
        if (fragment instanceof IOtherFragmentData) {
            ((IOtherFragmentData) fragment).onReceiveDataFromOtherFragment(senderTag, name, data);
        }
    }

    @Override
    public void onRefresh() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectionUtil.checkConnection(this);
        registerReceiver(mConnectionReceiver, mConnectionIntentFilter);
        mActivityFrameworkHelper.onResume();
    }

    protected void onToolbarNavigationClick() {
        onBackPressed();
    }

    @Override
    public void post(@NonNull Runnable runnable) {
        mActivityFrameworkHelper.post(runnable);
    }

    @Override
    public void postDelayed(@NonNull Runnable runnable, long delay) {
        mActivityFrameworkHelper.postDelayed(runnable, delay);
    }

    /**
     注册一个可销毁的对象

     该对象将在Activity销毁时自动销毁

     @param destroyable 可销毁的对象
     */
    @Override
    public final void registerDestroyable(@NonNull IDestroyable destroyable) {
        mActivityFrameworkHelper.registerDestroyable(destroyable);
    }

    @Override
    public void registerDialog(@NonNull Dialog dialog) {
        mActivityFrameworkHelper.registerDialog(dialog);
    }

    @Override
    public void registerRunnable(@NonNull Runnable runnable) {
        mActivityFrameworkHelper.registerRunnable(runnable);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public final void sendDataToFragment(@NonNull String tag, @NonNull String name, @Nullable Object data) {
        Fragment fragment = getManagerFragment(tag);

        if (fragment instanceof IFragmentData) {
            ((IFragmentData) fragment).onReceiveDataFromActivity(name, data);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mActivityFrameworkHelper.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View contentView) {
        mActivityFrameworkHelper.setContentView(contentView);
    }

    public final void setRefreshTime(long refreshTime) {
        mActivityFrameworkHelper.setRefreshTime(refreshTime);
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        mToolbar = toolbar;
        if (mToolbar != null) {
            super.setSupportActionBar(toolbar);
            mToolbar.setNavigationOnClickListener(mOnNavigationClickListener);
            mToolbar.setOnMenuItemClickListener(this);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setToolbarNavigation(int imageId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(imageId);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setToolbarSubTitle(int titleId) {
        if (mToolbar != null) {
            mToolbar.setSubtitle(titleId);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setToolbarSubTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setSubtitle(title);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setToolbarTitle(int textId) {
        if (mToolbar != null) {
            mToolbar.setTitle(textId);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setToolbarTitle(String text) {
        if (mToolbar != null) {
            mToolbar.setTitle(text);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(int textId) {
        Toaster.show(this, textId, Toaster.DURATION_LONG);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(String text) {
        Toaster.show(this, text, Toaster.DURATION_LONG);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(int textId, long duration) {
        Toaster.show(this, textId, duration);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToast(String text, long duration) {
        Toaster.show(this, text, duration);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToolbar() {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void showToolbarItem(int id) {
        MenuItem item;

        if (mToolbar != null) {
            item = mToolbar.getMenu().findItem(id);
            if (item != null) {
                item.setVisible(true);
            }
        }
        //        invalidateOptionsMenu();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent) {
        mActivityFrameworkHelper.start(intent);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent, int requestCode) {
        mActivityFrameworkHelper.start(intent, requestCode);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent, @Nullable ActivityOptionsCompat options) {
        mActivityFrameworkHelper.start(intent, options);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void start(@NonNull Intent intent, int requestCode, @Nullable ActivityOptionsCompat options) {
        mActivityFrameworkHelper.start(intent, requestCode, options);
    }
}