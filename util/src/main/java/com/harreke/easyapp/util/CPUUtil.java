package com.harreke.easyapp.util;

import com.orhanobut.logger.Logger;

/**
 * 由Harreke于2016/3/30创建
 */
public class CPUUtil {
    public static void showCpuArchitecture() {
        Logger.e("CPU Info:\n" + FileUtil.readTxt("/proc/cpuinfo"));
    }
}