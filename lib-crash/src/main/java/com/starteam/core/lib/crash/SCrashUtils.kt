package com.starteam.core.lib.crash

import android.content.Context
import xcrash.XCrash
import xcrash.XCrash.InitParameters
import java.io.File

/**
 *
 *     author : guanrunbai
 *     time   : 2023/03/17
 *     desc   :
 *     version: 1.0
 *
 */
object SCrashUtils {

    private const val crashDirName = "crash"

    //todo---后期需要增加文件压缩上传功能
    //todo---文件本地分享功能

    fun init(context: Context) {
        //javacrash,nativecrash,anr文件最多保存各两个
        val crashInitParam = InitParameters()
            .setLogDir("${context.filesDir.absolutePath}${File.separator}$crashDirName")
            .setJavaLogCountMax(2)
            .setJavaDumpFds(false)
            .setJavaDumpNetworkInfo(false)
            .setJavaDumpAllThreads(false)
            .setJavaCallback(null)
            .setNativeLogCountMax(2)
            .setNativeDumpFds(false)
            .setNativeDumpNetwork(false)
            .setNativeDumpMap(false)
            .setNativeDumpAllThreads(false)
            .setNativeDumpElfHash(false)
            .setNativeCallback(null)
            .setAnrLogCountMax(2)
            .setAnrDumpFds(false)
            .setAnrDumpNetwork(false)
            .setAnrCallback(null)

        XCrash.init(context, crashInitParam)
    }
}