package com.starteam.core.widget.webview.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;
import java.util.List;

/**
 * author : guanrunbai
 * time   : 2023/03/06
 * desc   :
 * version: 1.0
 */
public class WebViewProcessUtils {

    private static String currentProcessName;

    /**
     * 获取当前进程名
     * 我们优先通过 Application.getProcessName() 方法获取进程名。
     * 如果获取失败，我们再反射ActivityThread.currentProcessName()获取进程名
     * 如果失败，我们才通过常规方法ActivityManager来获取进程名
     * @return                      当前进程名
     */
    @Nullable
    public static String getCurrentProcessName(@NonNull Context context) {
        if (!TextUtils.isEmpty(currentProcessName)) {
            return currentProcessName;
        }
        //1)通过Application的API获取当前进程名
        currentProcessName = getCurrentProcessNameByApplication();
        if (!TextUtils.isEmpty(currentProcessName)) {
            return currentProcessName;
        }
        //2)通过反射ActivityThread获取当前进程名
        currentProcessName = getCurrentProcessNameByActivityThread();
        if (!TextUtils.isEmpty(currentProcessName)) {
            return currentProcessName;
        }
        //3)通过ActivityManager获取当前进程名
        currentProcessName = getCurrentProcessNameByActivityManager(context);
        return currentProcessName;
    }

    /**
     * 通过Application新的API获取进程名，无需反射，无需IPC，效率最高。
     * 这不就是我们想要的API吗！但是这个方法只有在android9【也就是aip28】之后的系统才能调用。
     */
    public static String getCurrentProcessNameByApplication() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //Application.getProcessName()方法直接返回当前进程名。
            //这个方法只有在android9【也就是aip28】之后的系统才能调用
            return Application.getProcessName();
        }
        return null;
    }

    /**
     * 通过反射ActivityThread获取进程名，避免了ipc
     */
    public static String getCurrentProcessNameByActivityThread() {
        String processName = null;
        try {
            //ActivityThread.currentProcessName()方法居然是public static的
            //ActivityThread类是hide的，app无法直接调用。
            //继续翻源码，看看这个方法是什么时候新增的。发现这个方法在android4.3.1上就已经有了这个方法了。
            //在android4.0.4上没有找到currentProcessName()方法。
            //那么意味着，我们是不是可以反射调用 ActivityThread.currentProcessName()
            ClassLoader classLoader = Application.class.getClassLoader();
            @SuppressLint("PrivateApi")
            final Method declaredMethod = Class.forName("android.app.ActivityThread",
                            false, classLoader)
                    .getDeclaredMethod("currentProcessName", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            final Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                processName = (String) invoke;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return processName;
    }

    /**
     * 通过ActivityManager 获取进程名，需要IPC通信
     * 1。ActivityManager.getRunningAppProcesses() 方法需要跨进程通信，效率不高。
     *    需要 和 系统进程的 ActivityManagerService 通信。必然会导致该方法调用耗时。
     * 2。拿到RunningAppProcessInfo的列表之后，还需要遍历一遍找到与当前进程的信息。
     * 3。ActivityManager.getRunningAppProcesses()有可能调用失败，返回null，也可能 AIDL 调用失败。
     *    当然ActivityManager.getRunningAppProcesses()调用失败是极低的概率。
     *    当你的APP用户量达到一定的数量级别时，一定会有用户遇到ActivityManager.getRunningAppProcesses()调用失败的情况。
     */
    public static String getCurrentProcessNameByActivityManager(@NonNull Context context) {
        if (context == null) {
            return null;
        }
        //指的是Process的id。每个进程都有一个独立的id，可以通过pid来区分不同的进程。
        int pid = android.os.Process.myPid( );
        ActivityManager am = (ActivityManager) context.getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            //获取当前正在运行的进程
            List<ActivityManager.RunningAppProcessInfo> runningAppList = am.getRunningAppProcesses();
            if (runningAppList != null) {
                for (ActivityManager.RunningAppProcessInfo processInfo : runningAppList) {
                    //相应的RunningServiceInfo的pid
                    if (processInfo.pid == pid) {
                        return processInfo.processName;
                    }
                }
            }
        }
        return null;
    }

}
