package com.starteam.core.service.image.glide.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : guanrunbai
 * time   : 2023/01/10
 * desc   : 本库用到的工具类
 * version: 1.0
 */
public final class ImageGlideServiceUtils {

    /**
     * 判断SDCard是否挂载（判断是否有sd卡）
     * Environment.MEDIA_MOUNTED,表示SDCard已经挂载
     * Environment.getExternalStorageState()，获得当前SDCard的挂载状态
     */
    public static boolean isMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获得SDCard 的路径,storage/sdcard
     *
     * @return 路径
     */
    public static String getSDCardPath() {
        String path = null;
        if (isMounted()) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return path;
    }

    /**
     * 获取外置SD卡路径
     * @return 应该就一条记录或空
     */
    public static ArrayList<String> getExtSDCardPath() {
        ArrayList<String> lResult = new ArrayList<>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec("mount");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception ignored) {
        }
        return lResult;
    }

    /**
     * 扫描路径图片显示在多媒体相册中
     * @param context               上下文
     * @param filePath              文件路径
     */
    public static void scannerImage(Context context, String filePath) {
        // 通知系统多媒体扫描该文件，否则会导致拍摄出来的图片或者视频没有及时显示到相册中，而需要通过重启手机才能看到
        MediaScannerConnection.scanFile(context, new String[]{filePath},
                null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

    /**
     * 获取 SD 卡路径
     * @return SD 卡路径
     */
    @SuppressWarnings("TryWithIdenticalCatches")
    public static List<String> getSDCardPaths(Context context) {
        StorageManager storageManager = (StorageManager) context.getApplicationContext()
                .getSystemService(Context.STORAGE_SERVICE);
        List<String> paths = new ArrayList<>();
        try {
            Method getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths");
            getVolumePathsMethod.setAccessible(true);
            Object invoke = getVolumePathsMethod.invoke(storageManager);
            paths = Arrays.asList((String[]) invoke);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return paths;
    }

    /**
     * 判断 SD 卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable(Context context) {
        return !getSDCardPaths(context).isEmpty();
    }
}
