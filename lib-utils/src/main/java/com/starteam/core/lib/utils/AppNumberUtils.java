package com.starteam.core.lib.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * author : guanrunbai
 * time   : 2023/01/04
 * desc   : num格式转化工具类
 * version: 1.0
 */
public final class AppNumberUtils {

    public static int convertToInt(String intStr, int defValue) {
        if (TextUtils.isEmpty(intStr)){
            return defValue;
        }
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }

    public static long convertToLong(String longStr, long defValue) {
        if (TextUtils.isEmpty(longStr)){
            return defValue;
        }
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }

    public static float convertToFloat(String fStr, float defValue) {
        if (TextUtils.isEmpty(fStr)){
            return defValue;
        }
        try {
            return Float.parseFloat(fStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }

    public static double convertToDouble(String dStr, double defValue) {
        if (TextUtils.isEmpty(dStr)){
            return defValue;
        }
        try {
            return Double.parseDouble(dStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }


    public static Integer convertToInteger(String intStr) {
        if (TextUtils.isEmpty(intStr)){
            return null;
        }
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Long convertToLong(String longStr) {
        if (TextUtils.isEmpty(longStr)){
            return null;
        }
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Float convertToFloat(String fStr) {
        if (TextUtils.isEmpty(fStr)){
            return null;
        }
        try {
            return Float.parseFloat(fStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Double convertToDouble(String dStr) {
        if (TextUtils.isEmpty(dStr)){
            return null;
        }
        try {
            return Double.parseDouble(dStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * Double类型保留指定位数的小数，返回double类型（四舍五入）
     * newScale 为指定的位数
     */
    public static double formatDouble(double d, int newScale) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
