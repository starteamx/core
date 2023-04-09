package com.starteam.core.widget.statusbar.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * author : guanrunbai
 * time   : 2023/01/16
 * desc   :
 * version: 1.0
 */
public final class CommonStatusBarUtils {

    /**
     * 获取状态栏高度
     * @param context       context
     * @return              状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
