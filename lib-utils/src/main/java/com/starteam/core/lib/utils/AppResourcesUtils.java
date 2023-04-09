package com.starteam.core.lib.utils;

import android.content.Context;

import com.starteam.core.lib.appcontext.AppContextUtils;

/**
 * author : guanrunbai
 * time   : 2023/01/04
 * desc   :
 * version: 1.0
 */
public final class AppResourcesUtils {

    /**
     * 获取资源字符串
     *
     * @param id      资源id
     * @return
     */
    public static String getString(int id) {
        Context context = AppContextUtils.getApp();
        return context == null ? "" : context.getResources().getString(id);
    }

    /**
     * 获取资源字符串
     *
     * @param context 上下文
     * @param id      资源id
     * @return
     */
    private static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }
}
