package com.harreke.easyapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/12/31
 */
public class ViewUtil {
    public static byte[] bitmap2Bytes(@NonNull Bitmap bitmap) {
        return bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG, 100);
    }

    public static byte[] bitmap2Bytes(@NonNull Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bitmap.compress(compressFormat, quality, outputStream);

        return outputStream.toByteArray();
    }

    public static Bitmap bytes2Bitmap(@NonNull byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static void check(@NonNull RadioGroup group, int index) {
        if (index >= 0 && index < group.getChildCount()) {
            group.check(group.getChildAt(index).getId());
        }
    }

    public static int findChild(@NonNull ViewGroup parent, int childId) {
        int position = -1;
        int i;

        for (i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChildAt(i).getId() == childId) {
                position = i;
                break;
            }
        }

        return position;
    }

    public static int getBackgroundColor(@NonNull View view) {
        Drawable drawable = view.getBackground();

        if (drawable != null && drawable instanceof ColorDrawable) {
            return ((ColorDrawable) drawable).getColor();
        } else {
            return Color.TRANSPARENT;
        }
    }

    public static Bitmap getBitmap(@NonNull ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();

        return drawable != null ? drawable.getBitmap() : null;
    }

    public static byte[] getBitmapBytes(@NonNull ImageView imageView) {
        return bitmap2Bytes(getBitmap(imageView));
    }

    public static float[] getCoordinate(@NonNull View view) {
        return new float[]{view.getX(), view.getY()};
    }

    public static Bitmap getDrawingCache(@NonNull View view) {
        Bitmap bitmap;

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        bitmap = view.getDrawingCache();
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public static byte[] getDrawingCacheBytes(@NonNull View view) {
        byte[] bytes = bitmap2Bytes(getDrawingCache(view), Bitmap.CompressFormat.JPEG, 25);

        view.setDrawingCacheEnabled(false);

        return bytes;
    }

    public static float getFloatTag(@NonNull View view) {
        Object tag = view.getTag();

        return tag instanceof Float ? (Float) tag : 0;
    }

    public static float getFloatTag(@NonNull View view, int key) {
        Object tag = view.getTag(key);

        return tag instanceof Float ? (Float) tag : 0;
    }

    public static int getIntTag(@NonNull View view) {
        Object tag = view.getTag();

        return tag instanceof Integer ? (Integer) tag : 0;
    }

    public static int getIntTag(@NonNull View view, int key) {
        Object tag = view.getTag(key);

        return tag instanceof Integer ? (Integer) tag : 0;
    }

    public static int getInvisibleHeight(Window window) {
        View decorView = window.getDecorView();
        Rect rect;

        rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);

        return decorView.getHeight() - rect.bottom;
    }

    public static long getLongTag(@NonNull View view) {
        Object tag = view.getTag();

        return tag instanceof Long ? (Long) tag : 0;
    }

    public static long getLongTag(@NonNull View view, int key) {
        Object tag = view.getTag(key);

        return tag instanceof Long ? (Long) tag : 0;
    }

    public static Object getObjectTag(@NonNull View view) {
        return view.getTag();
    }

    public static Object getObjectTag(@NonNull View view, int key) {
        return view.getTag(key);
    }

    public static float[] getPivot(@NonNull View view) {
        return new float[]{view.getPivotX(), view.getPivotY()};
    }

    public static float[] getScale(@NonNull View view) {
        return new float[]{view.getScaleX(), view.getScaleY()};
    }

    public static int[] getSize(@NonNull View view) {
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }

    public static int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int getStatusBarHeight(@NonNull View view) {
        Rect frame = new Rect();
        DisplayMetrics metrics;
        int width;
        int height;

        view.getWindowVisibleDisplayFrame(frame);
        if (frame.top > 0) {
            return frame.top;
        } else {
            metrics = view.getContext().getResources().getDisplayMetrics();
            width = metrics.widthPixels;
            height = metrics.heightPixels;
            if (frame.bottom > height) {
                return width - frame.bottom;
            } else {
                return height - frame.bottom;
            }
        }
    }

    public static String getStringTag(@NonNull View view) {
        Object tag = view.getTag();

        return tag instanceof String ? (String) tag : null;
    }

    public static String getStringTag(@NonNull View view, int key) {
        Object tag = view.getTag(key);

        return tag instanceof String ? (String) tag : null;
    }

    public static String getText(@NonNull TextView textView) {
        CharSequence charSequence = textView.getText();

        return charSequence != null ? charSequence.toString() : null;
    }

    public static int getTextColor(@NonNull View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getCurrentTextColor();
        } else {
            return Color.TRANSPARENT;
        }
    }

    public static float[] getTranslation(@NonNull View view) {
        return new float[]{view.getTranslationX(), view.getTranslationY()};
    }

    public static ViewInfo getViewInfo(@NonNull View view) {
        return getViewInfo(view, false);
    }

    public static ViewInfo getViewInfo(@NonNull View view, boolean withStatusBarHeight) {
        ViewInfo viewInfo = new ViewInfo();
        int[] position = new int[2];

        view.getLocationInWindow(position);
        viewInfo.x = position[0];
        viewInfo.y = position[1];
        if (Build.VERSION.SDK_INT < 19 || !withStatusBarHeight) {
            viewInfo.y -= getStatusBarHeight(view);
        }
        viewInfo.width = view.getMeasuredWidth();
        viewInfo.height = view.getMeasuredHeight();

        return viewInfo;
    }

    public static boolean hasHardwareMenuKey(@NonNull Context context) {
        return ViewConfiguration.get(context).hasPermanentMenuKey();
    }

    public static void hideInputMethod(@NonNull View view) {
        ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isInputMethodShowing(@NonNull View view) {
        return ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).isActive(view);
    }

    public static boolean isStatusBarTop(Activity activity) {
        Rect frame = new Rect();

        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        return frame.top > 0;
    }

    public static void patchTopPadding(View view) {
        int left;
        int top;
        int right;
        int bottom;

        if (Build.VERSION.SDK_INT >= 19) {
            left = view.getPaddingLeft();
            top = view.getPaddingTop();
            right = view.getPaddingRight();
            bottom = view.getPaddingBottom();
            view.setPadding(left, top + getStatusBarHeight(), right, bottom);
        }
    }

    public static void requestFullScreen(@NonNull Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        int flag = 0;

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (!hasHardwareMenuKey(activity)) {
            flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            flag |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        decorView.setSystemUiVisibility(flag);
    }

    public static void requestNonFullScreen(@NonNull Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();

        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    public static void setCoordinate(@NonNull View view, @NonNull ViewInfo viewInfo) {
        setCoordinate(view, viewInfo.x, viewInfo.y);
    }

    public static void setCoordinate(@NonNull View view, float[] coordinate) {
        setCoordinate(view, coordinate[0], coordinate[1]);
    }

    public static void setCoordinate(@NonNull View view, float x, float y) {
        view.setX(x);
        view.setY(y);
    }

    public static void setEnabled(@NonNull RadioGroup group, boolean enabled) {
        for (int i = 0; i < group.getChildCount(); i++) {
            group.getChildAt(i).setEnabled(enabled);
        }
    }

    public static void setHeight(@NonNull View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();

        params.height = height;
        view.setLayoutParams(params);
    }

    public static void setPaddingBottom(@NonNull View view, int paddingBottom) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), paddingBottom);
    }

    public static void setPivot(@NonNull View view, float pivotX, float pivotY) {
        view.setPivotX(pivotX);
        view.setPivotY(pivotY);
    }

    public static void setPivot(@NonNull View view, float[] pivot) {
        setPivot(view, pivot[0], pivot[1]);
    }

    public static void setScale(@NonNull View view, float scaleX, float scaleY) {
        view.setScaleX(scaleX);
        view.setScaleY(scaleY);
    }

    public static void setScale(@NonNull View view, float[] scale) {
        setScale(view, scale[0], scale[1]);
    }

    public static void setSize(@NonNull View view, @NonNull int[] size) {
        setSize(view, size[0], size[1]);
    }

    public static void setSize(@NonNull View view, @NonNull ViewInfo viewInfo) {
        setSize(view, viewInfo.width, viewInfo.height);
    }

    public static void setSize(@NonNull View view, int width, int height) {
        ViewGroup.LayoutParams params;

        params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    public static void setTextColor(@NonNull View view, int textColor) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(textColor);
        }
    }

    public static void setTranslation(@NonNull View view, float translationX, float translationY) {
        view.setTranslationX(translationX);
        view.setTranslationY(translationY);
    }

    public static void setTranslation(@NonNull View view, float[] translation) {
        setTranslation(view, translation[0], translation[1]);
    }

    public static void setWidth(@NonNull View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();

        params.width = width;
        view.setLayoutParams(params);
    }

    public static void showInputMethod(@NonNull View view) {
        if (view instanceof TextView) {
            view.onTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
            view.onTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
        } else {
            ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
        }
    }
}