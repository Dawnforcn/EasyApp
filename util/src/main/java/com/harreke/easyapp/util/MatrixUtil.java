package com.harreke.easyapp.util;

import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 由 Harreke 于 2016/3/1 创建
 */
public class MatrixUtil {
    @SuppressWarnings("unchecked")
    @NonNull
    public static <ITEM> List<ITEM> transform(@NonNull List<ITEM> sourceList, @NonNull Class<ITEM> clazz, int sourceRow, int sourceLine,
            boolean rotateClockwise) {
        int line, row;
        Iterator<ITEM> iterator;
        int sourceListSize = sourceList.size();
        int sourceMatrixSize = sourceLine * sourceRow;
        ITEM[][] sources;
        List<ITEM> targetList;
        ITEM[][] targets;
        int mod;
        int nullCount;

        if (sourceListSize == 0) {
            throw new IllegalArgumentException("List must not be empty!");
        }
        // 判断行与列是否为0
        if (sourceMatrixSize == 0) {
            throw new IllegalArgumentException("Row and line must not be zero!");
        }
        // 将列表拆分成二维数组
        sources = (ITEM[][]) Array.newInstance(clazz, sourceLine, sourceRow);
        iterator = sourceList.iterator();
        for (line = 0; line < sourceLine; line++) {
            for (row = 0; row < sourceRow; row++) {
                if (iterator.hasNext()) {
                    sources[line][row] = iterator.next();
                } else {
                    sources[line][row] = null;
                }
            }
        }
        // 创建目标二维数组
        targets = (ITEM[][]) Array.newInstance(clazz, sourceRow, sourceLine);
        // 通过矩阵操作二维数组，旋转并填充数据
        if (rotateClockwise) {
            // 如果是顺时针旋转
            for (line = 0; line < sourceRow; line++) {
                for (row = 0; row < sourceLine; row++) {
                    targets[line][row] = sources[sourceLine - row - 1][line];
                }
            }
        } else {
            // 如果是逆时针旋转
            for (line = 0; line < sourceRow; line++) {
                for (row = 0; row < sourceLine; row++) {
                    targets[line][row] = sources[row][sourceRow - line - 1];
                }
            }
            // 判断是否需要把矩阵底端的数据提升到顶部，以填充空位
            if (sourceListSize < sourceMatrixSize) {
                for (row = 0; row < sourceLine; row++) {
                    nullCount = 0;
                    for (line = 0; line < sourceRow; line++) {
                        if (targets[line][row] == null) {
                            nullCount++;
                        }
                    }
                    if (nullCount > 0 && nullCount < sourceRow) {
                        mod = sourceRow - nullCount;
                        for (line = 0; line < mod; line++) {
                            targets[line][row] = targets[line + nullCount][row];
                        }
                        for (line = mod; line < sourceRow; line++) {
                            targets[line][row] = null;
                        }
                    }
                }
            }
        }
        // 创建目标列表
        targetList = new ArrayList<>(sourceMatrixSize);
        // 将二维数组合并为列表
        for (line = 0; line < sourceRow; line++) {
            for (row = 0; row < sourceLine; row++) {
                targetList.add(targets[line][row]);
            }
        }

        return targetList;
    }

    @NonNull
    public static <ITEM> List<ITEM> transformMulti(@NonNull List<ITEM> sourceList, @NonNull Class<ITEM> clazz, int sourceRow, int sourceLine,
            boolean rotateClockwise) {
        int sourceListSize = sourceList.size();
        int sourceMatrixSize = sourceLine * sourceRow;
        int i, count;
        int from, end;
        List<ITEM> tempList;
        List<ITEM> targetList;

        if (sourceListSize == 0) {
            throw new IllegalArgumentException("List must not be empty!");
        }
        // 判断行与列是否为0
        if (sourceMatrixSize == 0) {
            throw new IllegalArgumentException("Row and line must not be zero!");
        }
        if (sourceListSize > sourceMatrixSize) {
            targetList = new ArrayList<>(sourceListSize);
            count = sourceListSize / sourceMatrixSize + 1;
            for (i = 0; i < count; i++) {
                from = i * sourceMatrixSize;
                end = (i + 1) * sourceMatrixSize;
                if (end > sourceListSize) {
                    end = sourceListSize;
                }
                tempList = sourceList.subList(from, end);
                targetList.addAll(transform(tempList, clazz, sourceRow, sourceLine, rotateClockwise));
            }
        } else {
            targetList = transform(sourceList, clazz, sourceRow, sourceLine, rotateClockwise);
        }

        return targetList;
    }
}
