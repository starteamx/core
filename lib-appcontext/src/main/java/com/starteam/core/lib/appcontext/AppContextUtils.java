package com.starteam.core.lib.appcontext;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;

/**
 * author : guanrunbai
 * time   : 2023/01/04
 * desc   : 初始化工具类，获取上下文，会自动初始化
 * version: 1.0
 */
public final class AppContextUtils {

    private static Application sApplication;

    public static Application getApp() {
        try {
            if (sApplication == null) {
                sApplication = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            }
        } catch (Exception e) {
            throw new NullPointerException("sApplication is null");
        }
        return sApplication;
    }


    @NonNull
    public static <T> T checkNotNull(@Nullable T arg) {
        return checkNotNull(arg, "Argument must not be null");
    }

    @NonNull
    public static <T> T checkNotNull(@Nullable T arg, @NonNull String message) {
        if (arg == null) {
            throw new NullPointerException(message);
        }
        return arg;
    }


    /**
     * 关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
