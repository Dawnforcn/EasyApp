package com.harreke.easyapp.util;

import java.util.List;

/**
 * 由 Harreke（harreke@live.cn） 创建于 2015/03/18
 */
public class ListUtil {
    public static <T> T getRandomItem(List<T> list) {
        return isEmpty(list) ? null : list.get((int) (Math.random() * list.size()));
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }
}