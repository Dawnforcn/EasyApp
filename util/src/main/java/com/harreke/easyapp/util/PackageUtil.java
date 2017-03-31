package com.harreke.easyapp.util;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 由huoqisheng于2016/7/7创建
 */
public class PackageUtil {
    /**
     附件目录
     */
    public static final String DIR_ASSETS = "assets";
    /**
     数据库目录
     */
    //    public static final String DIR_DATABASES = "databases";
    /**
     缓存目录
     */
    public static final String DIR_TEMPS = "temps";
    public static String AssetsDir = null;
    /**
     判断附件目录是否可用
     */
    public static boolean AssetsEnabled = false;
    /**
     app内部临时目录
     */
    public static String CacheDir = null;
    /**
     app外部临时目录
     */
    @Nullable
    public static String ExternalCacheDir = null;
    /**
     app外部文件目录
     */
    @Nullable
    public static String ExternalFilesDir = null;
    /**
     外部存储卡目录
     */
    @Nullable
    public static String ExternalStorageDir = null;
    public static String ExternalStoragePackageDir = null;
    /**
     app内部文件目录
     */
    public static String FilesDir;
    public static String PackageName = null;
    public static String TempsDir = null;
    /**
     判断缓存目录是否可用
     */
    public static boolean TempsEnabled = false;
    private static WeakReference<Application> mApplicationRef;

    /**
     计算附件目录大小

     @return 附件目录大小，单位为字节
     */
    public static int calculateAssetsSize() {
        return DirUtil.calculateDirSize(AssetsDir);
    }

    /**
     计算缓存目录大小

     @return 缓存目录大小，单位为字节
     */
    public static int calculateCacheSize() {
        return DirUtil.calculateDirSize(CacheDir);
    }

    /**
     计算外部缓存目录大小

     @return 外部缓存目录大小，单位为字节
     */
    public static int calculateExternalCacheSize() {
        return DirUtil.calculateDirSize(ExternalCacheDir);
    }

    /**
     计算外部文件目录大小

     @return 外部文件录大小，单位为字节
     */
    public static int calculateExternalFilesSize() {
        return DirUtil.calculateDirSize(ExternalFilesDir);
    }

    /**
     计算文件目录大小

     @return 文件目录大小，单位为字节
     */
    public static int calculateFilesSize() {
        return DirUtil.calculateDirSize(FilesDir);
    }

    /**
     计算临时目录大小

     @return 临时目录大小，单位为字节
     */
    public static int calculateTempsSize() {
        return DirUtil.calculateDirSize(TempsDir);
    }

    /**
     将指定附件拷贝至设备内存

     @param assetName 附件文件名

     附件文件名需要使用相对路径，比如”logo.png“，”pic/home.png“
     @param targetPath 目标路径

     目标路径需要使用相对路径（相对于app临时目录下的assets文件夹），该路径必须存在

     @return 是否拷贝成功
     */
    public static boolean copyAsset(@NonNull String assetName, @NonNull String targetPath) {
        Application application = getApplication();
        if (application == null) {
            return false;
        }
        boolean success = false;
        if (AssetsEnabled && assetName.length() > 0) {
            File file = new File(FilesDir + "/" + DIR_ASSETS + "/" + targetPath + (targetPath.length() == 0 ? "" : "/") + assetName);
            try {
                if (!file.exists()) {
                    FileUtil.copyFile(application.getAssets().open(assetName), new FileOutputStream(file));
                    success = true;
                }
            } catch (IOException e) {
                Logger.e("Copy assets " + file.getAbsolutePath() + "  error!");
            }
        }

        return success;
    }

    /**
     在app内部临时目录中创建指定目录

     @param dir 目录名称

     目录名称需要使用相对路径，比如”pic“

     @return 是否创建成功
     */
    public static boolean createCacheDir(String dir) {
        File file;
        boolean success = true;

        file = new File(CacheDir + "/" + dir);
        if (!file.exists()) {
            if (!file.mkdir()) {
                success = false;
                Logger.e("Cannot create cache " + file.getAbsolutePath() + " directory!");
            }
        } else if (!file.isDirectory()) {
            success = false;
            Logger.e("Cannot access cache " + file.getAbsolutePath() + " as directory!");
        }

        return success;
    }

    /**
     在app外部临时目录中创建指定目录

     @param dir 目录名称

     目录名称需要使用相对路径，比如”pic“

     @return 是否创建成功
     */
    public static boolean createExternalCacheDir(String dir) {
        File file;
        boolean success = true;

        if (ExternalCacheDir != null) {
            file = new File(ExternalCacheDir + "/" + dir);
            if (!file.exists()) {
                if (!file.mkdir()) {
                    success = false;
                    Logger.e("Cannot create external cache " + file.getAbsolutePath() + " directory!");
                }
            } else if (!file.isDirectory()) {
                success = false;
                Logger.e("Cannot access external cache " + file.getAbsolutePath() + " as directory!");
            }
        } else {
            success = false;
        }

        return success;
    }

    /**
     在app外部文件目录中创建指定目录

     @param dir 目录名称

     目录名称需要使用相对路径，比如”pic“

     @return 是否创建成功
     */
    public static boolean createExternalFilesDir(String dir) {
        File file;
        boolean success = true;

        if (ExternalFilesDir != null) {
            file = new File(ExternalFilesDir + "/" + dir);
            if (!file.exists()) {
                if (!file.mkdir()) {
                    success = false;
                    Logger.e("Cannot create external files" + file.getAbsolutePath() + " directory!");
                }
            } else if (!file.isDirectory()) {
                success = false;
                Logger.e("Cannot access external files" + file.getAbsolutePath() + " directory!");
            }
        } else {
            success = false;
        }

        return success;
    }

    public static boolean createExternalStorageDir(String dir) {
        File file;
        boolean success = true;

        file = new File(ExternalStorageDir + "/" + dir);
        if (!file.exists()) {
            if (!file.mkdir()) {
                success = false;
                Logger.e("Cannot create external storage " + file.getAbsolutePath() + " directory!");
            }
        } else if (!file.isDirectory()) {
            success = false;
            Logger.e("Cannot access external storage " + file.getAbsolutePath() + " directory!");
        }

        return success;
    }

    /**
     在app内部文件目录中创建指定目录

     @param dir 目录名称

     目录名称需要使用相对路径，比如”pic“

     @return 是否创建成功
     */
    public static boolean createFilesDir(String dir) {
        File file;
        boolean success = true;

        file = new File(FilesDir + "/" + dir);
        if (!file.exists()) {
            if (!file.mkdir()) {
                success = false;
                Logger.e("Cannot create files " + file.getAbsolutePath() + " directory!");
            }
        } else if (!file.isDirectory()) {
            success = false;
            Logger.e("Cannot access files " + file.getAbsolutePath() + " directory!");
        }

        return success;
    }

    private static Application getApplication() {
        return mApplicationRef == null ? null : mApplicationRef.get();
    }

    public static String getVersion() {
        Application application = getApplication();
        if (application == null) {
            return "";
        }
        PackageManager manager = application.getPackageManager();
        String version;
        try {
            PackageInfo info = manager.getPackageInfo(application.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            version = "";
        }

        return version;
    }

    public static void init(@NonNull Application application) {
        File file;

        PackageName = application.getPackageName();
        CacheDir = application.getCacheDir().getAbsolutePath();
        FilesDir = application.getFilesDir().getAbsolutePath();
        AssetsEnabled = createFilesDir(DIR_ASSETS);
        if (AssetsEnabled) {
            AssetsDir = FilesDir + "/" + DIR_ASSETS;
        }
        TempsEnabled = createCacheDir(DIR_TEMPS);
        if (TempsEnabled) {
            TempsDir = CacheDir + "/" + DIR_TEMPS;
        }

        file = application.getExternalCacheDir();
        if (file != null) {
            ExternalCacheDir = file.getAbsolutePath();
        }
        file = application.getExternalFilesDir(null);
        if (file != null) {
            ExternalFilesDir = file.getAbsolutePath();
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ExternalStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        if (ExternalStorageDir != null) {
            ExternalStoragePackageDir = ExternalStorageDir + "/" + PackageName;
        }
        createExternalStorageDir(PackageName);

        mApplicationRef = new WeakReference<>(application);
    }

    private static Bundle readMetaData() {
        Application application = getApplication();
        if (application == null) {
            return null;
        }
        Bundle bundle;
        try {
            ApplicationInfo info = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);
            bundle = info.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            bundle = null;
        }

        return bundle;
    }

    public static int readMetaDataInt(@NonNull String key) {
        Bundle metaData = readMetaData();

        if (metaData != null) {
            return metaData.getInt(key, 0);
        } else {
            return 0;
        }
    }

    public static String readMetaDataString(@NonNull String key) {
        Bundle metaData = readMetaData();

        if (metaData != null) {
            return metaData.getString(key);
        } else {
            return null;
        }
    }
}
