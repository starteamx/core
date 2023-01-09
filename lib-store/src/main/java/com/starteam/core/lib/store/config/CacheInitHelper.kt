package com.starteam.core.lib.store.config

import android.app.Application
import android.content.Context
import android.util.Log
import com.starteam.core.lib.store.StoreToolHelper
import com.tencent.mmkv.MMKV
import java.io.File

/**
 *
 *     author : guanrunbai
 *     time   : 2023/01/04
 *     desc   : 初始化配置
 *     version: 1.0
 *
 */
object CacheInitHelper {

    private var config: CacheConfig? = null
    private var mmkvPath: String? = null
    private var filePath: String? = null
    private var externalFilePath: String? = null
    private var maxLruSize: Int? = null

    @Synchronized
    fun init(context: Application, config: CacheConfig?) {
        if (config == null) {
            CacheInitHelper.config = CacheConfig.newBuilder().build()
        }
        StoreToolHelper.instance.setApp(context)
        CacheInitHelper.config = config
        val logFile = config?.logDir
        val extraFile = config?.extraLogDir
        filePath = if (logFile == null) {
            val cacheFiles = getCachePath(context)
            cacheFiles + File.separator + "Cache"
            //路径：/data/user/0/你的包名/cache/Cache
        } else {
            logFile
        }
        externalFilePath = if (extraFile == null) {
            val externalCacheFiles = getExternalCachePath(context)
            externalCacheFiles + File.separator + "Cache"
            //路径：/storage/emulated/0/Android/data/你的包名/cache/Cache
        } else {
            logFile
        }
        maxLruSize = config?.maxCacheSize
        Log.d("CacheHelper : ", "file path : $filePath")
        //初始化腾讯mmkv
        mmkvPath = filePath + File.separator + "mmkv"
        //路径：/storage/emulated/0/Android/data/你的包名/cache/Cache/mmkv
        Log.d("CacheHelper : ", "mmkv path : $mmkvPath")
        MMKV.initialize(context, mmkvPath)
    }

    /**
     * 获取mmkv设置的缓存路径，建议放到机身内存缓存文件
     * 内部存储，举个例子：
     * file:data/user/0/包名/files
     * cache:/data/user/0/包名/cache
     */
    fun getMmkvPath(): String {
        if (mmkvPath == null || mmkvPath?.length == 0) {
            throw NullPointerException("please init store lib at fist")
        }
        return mmkvPath as String
    }

    /**
     * 获取该库机身内存文件的总路径
     * 内部存储，举个例子：
     * file:data/user/0/包名/files
     * cache:/data/user/0/包名/cache
     */
    fun getBaseCachePath(): String {
        if (filePath == null || filePath?.length == 0) {
            throw NullPointerException("please init store lib at fist")
        }
        return filePath as String
    }

    /**
     * 获取该库机身外部存储总路径
     * 外部存储根目录，举个例子
     * files:/storage/emulated/0/Android/data/包名/files
     * cache:/storage/emulated/0/Android/data/包名/cache
     */
    fun getExternalCachePath(): String {
        if (externalFilePath == null || externalFilePath?.length == 0) {
            throw NullPointerException("please init store lib at fist")
        }
        return externalFilePath as String
    }

    /**
     * 获取最大lru缓存大小
     */
    fun getMaxLruSize(): Int {
        if (maxLruSize == null) {
            maxLruSize = 10000
        }
        return maxLruSize as Int
    }


    /**
     * 机身内存缓存文件
     * cache-->存放缓存文件
     * code_cache-->存放运行时代码优化等产生的缓存
     * databases-->存放数据库文件
     * files-->存放一般文件
     * lib-->存放App依赖的so库 是软链接，指向/data/app/ 某个子目录下
     * shared_prefs-->存放SharedPreferences 文件
     *
     * 内部存储，举个例子：
     * cache:/data/user/0/包名/cache
     */
    private fun getCachePath(context: Context): String? {
        val cacheDir: File = context.cacheDir
        return if (cacheDir.exists()) {
            cacheDir.absolutePath
        } else null
    }

    /**
     * 机身外部存储，/storage/emulated/0/
     * App外部私有目录
     * /sdcard/Android/data/包名
     * cache-->存放缓存文件
     * files-->存放一般文件
     *
     * 外部存储根目录，举个例子
     * files:/storage/emulated/0/Android/data/包名/files
     * cache:/storage/emulated/0/Android/data/包名/cache
     */
    private fun getExternalCachePath(context: Context): String? {
        val cacheDir = context.externalCacheDir
        return if (cacheDir != null && cacheDir.exists()) {
            cacheDir.absolutePath
        } else null
    }
}