package com.harreke.easyapp.network.executor;

import android.support.annotation.NonNull;

import java.io.File;

/**
 * 由 Harreke（harreke@live.cn） 创建于 2015/04/29
 */
public abstract class FileExecutor extends RequestExecutor<File> {
    private File mFile;

    @Override
    public void destroy() {
        super.destroy();
        mFile = null;
    }

    public FileExecutor download(@NonNull File file) {
        mFile = file;

        return this;
    }

    protected File getFile() {
        return mFile;
    }
}