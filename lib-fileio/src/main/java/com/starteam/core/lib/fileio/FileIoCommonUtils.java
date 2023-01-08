package com.starteam.core.lib.fileio;

import java.io.File;

/**
 * author : guanrunbai
 * time   : 2023/01/05
 * desc   :
 * version: 1.0
 */
class FileIoCommonUtils {

    /**
     * 判断文件是否创建，如果没有创建，则新建
     * @param file                                  file文件
     * @return
     */
    public static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }
}
