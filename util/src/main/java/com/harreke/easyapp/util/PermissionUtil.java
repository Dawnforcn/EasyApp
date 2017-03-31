package com.harreke.easyapp.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 由Harreke于2016/2/18创建
 */
public class PermissionUtil {
    /**
     检查Activity所属Package自带的权限（Manifest文件中注册的）是否已授权

     @param activity Activity
     @param requestCode 回调代码，{@link Activity#onRequestPermissionsResult(int, String[], int[])}

     @return 是否有权限未授权，需要向系统申请
     */
    public static boolean checkAndRequestPermission(@NonNull Activity activity, int requestCode) {
        String[] requestPermissions;
        List<String> deniedPermissionList = new ArrayList<>();
        PackageInfo info;

        try {
            info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS);
            requestPermissions = info.requestedPermissions;
            for (String requestPermission : requestPermissions) {
                if (ActivityCompat.checkSelfPermission(activity, requestPermission) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissionList.add(requestPermission);
                }
            }
            if (deniedPermissionList.size() > 0) {
                ActivityCompat.requestPermissions(activity, deniedPermissionList.toArray(new String[deniedPermissionList.size()]), requestCode);

                return false;
            }
        } catch (PackageManager.NameNotFoundException ignored) {
            return false;
        }

        return true;
    }

    /**
     检查授权结果中指定的权限是否已全部获得

     @param requiredPermissions 需要检查的权限
     @param resultPermissions 用户操作返回的授权列表
     @param grantResults 用户操作返回的授权结果

     @return 指定的权限是否已全部获得
     */
    public static boolean checkPermissionResult(@NonNull String[] requiredPermissions, @NonNull String[] resultPermissions, @NonNull int[] grantResults) {
        int index;

        for (String requiredPermission : requiredPermissions) {
            index = Arrays.binarySearch(resultPermissions, requiredPermission);
            if (index >= 0) {
                if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     检查App是否有权限读写外部存储器

     @return 是否有权限未授权，需要向系统申请
     */
    public static boolean checkPermissionsResultForExternalStorage(@NonNull String[] permissions, @NonNull int[] grantResults) {
        String[] requiredPermissions;

        if (Build.VERSION.SDK_INT >= 16) {
            requiredPermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        } else {
            requiredPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }

        return checkPermissionResult(requiredPermissions, permissions, grantResults);
    }

    /**
     检查Activity所属Package某一权限（Manifest文件中注册的）是否已授权

     @param activity Activity
     @param requestCode 回调代码，{@link Activity#onRequestPermissionsResult(int, String[], int[])}

     @return 是否有权限未授权，需要向系统申请
     */
    public boolean checkAndRequestPermission(@NonNull Activity activity, @NonNull String permission, int requestCode) {
        boolean granted = ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;

        if (granted) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);

            return false;
        }
    }
}