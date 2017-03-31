package com.harreke.easyapp.util;

/**
 * 由Harreke于2016/1/29创建
 */
public class ColorUtil {
    public static int plus(int color1, int color2) {
        int color1A = (color1 >> 24) & 0xff;
        int color1R = (color1 >> 16) & 0xff;
        int color1G = (color1 >> 8) & 0xff;
        int color1B = color1 & 0xff;

        int color2A = (color2 >> 24) & 0xff;
        int color2R = (color2 >> 16) & 0xff;
        int color2G = (color2 >> 8) & 0xff;
        int color2B = color2 & 0xff;

        return (color1A + color2A) << 24 | (color1R + color2R) << 16 | (color1G + color2G) << 8 | (color1B + color2B);
    }
}