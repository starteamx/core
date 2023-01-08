package com.starteam.core.lib.mediastore;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * author : guanrunbai
 * time   : 2023/01/05
 * desc   :
 * version: 1.0
 */
class MediastoreUtils {
    /**
     * 使用字符流读取流数据到新的file文件中
     *
     * @param is      io流
     * @param newFile 文件
     * @return
     */
    public static boolean writeFileFromIS1(final InputStream is, final File newFile) {
        boolean isSuccess;
        if (is == null || newFile == null) {
            Log.e("FileIOUtils", "create file <" + newFile + "> failed.");
            return false;
        }
        OutputStream os = null;
        int sBufferSize = 1024 * 100;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            os = new BufferedOutputStream(fileOutputStream, sBufferSize);
            byte[] data = new byte[sBufferSize];
            for (int len; (len = is.read(data)) != -1; ) {
                os.write(data, 0, len);
            }
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }
}
