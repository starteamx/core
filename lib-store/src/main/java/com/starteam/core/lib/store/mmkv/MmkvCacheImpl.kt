package com.starteam.core.lib.store.mmkv

import android.util.Log
import com.starteam.core.lib.store.ICacheable
import com.starteam.core.lib.store.config.CacheInitHelper
import com.tencent.mmkv.MMKV
import java.io.File

/**
 *
 *     author : guanrunbai
 *     time   : 2023/01/04
 *     desc   : Mmkv存储实现类
 *     version: 1.0
 *
 */
class MmkvCacheImpl(builder: Builder) : ICacheable {

    private var mmkv: MMKV = MMKV.mmkvWithID(builder.fileName) ?: MMKV.defaultMMKV()!!
    private var fileName: String? = builder.fileName

    class Builder {
        var fileName: String? = null
        fun setFileId(name: String): Builder {
            fileName = name
            return this
        }

        fun build(): MmkvCacheImpl {
            return MmkvCacheImpl(this)
        }
    }


    override fun saveInt(key: String, value: Int) {
        mmkv.encode(key, value)
    }

    override fun readInt(key: String, default: Int): Int {
        return mmkv.decodeInt(key, default)
    }

    override fun saveFloat(key: String, value: Float) {
        mmkv.encode(key, value)
    }

    override fun readFloat(key: String, default: Float): Float {
        return mmkv.decodeFloat(key, default)
    }

    override fun saveDouble(key: String, value: Double) {
        mmkv.encode(key, value)
    }

    override fun readDouble(key: String, default: Double): Double {
        return mmkv.decodeDouble(key, default)
    }

    override fun saveLong(key: String, value: Long) {
        mmkv.encode(key, value)
    }

    override fun readLong(key: String, default: Long): Long {
        return mmkv.decodeLong(key, default)
    }

    override fun saveString(key: String, value: String) {
        mmkv.encode(key, value)
    }

    override fun readString(key: String, default: String): String {
        return mmkv.decodeString(key, default) ?: default
    }

    override fun saveBoolean(key: String, value: Boolean) {
        mmkv.encode(key, value)
    }

    override fun readBoolean(key: String, default: Boolean): Boolean {
        return mmkv.decodeBool(key, default)
    }

    override fun removeKey(key: String) {
        mmkv.removeValueForKey(key)
    }

    override fun totalSize(): Long {
        return mmkv.totalSize()
    }

    override fun clearData() {
        fileName?.let {
            File(CacheInitHelper.getMmkvPath(), it).apply {
                if (exists()) {
                    Log.d("DiskCacheImpl","before fileSize:" +
                            "${this.length() / 1024}K,path:${this.absolutePath}")
                }
            }
        }
        mmkv.clearAll()
        fileName?.let {
            File(CacheInitHelper.getMmkvPath(), it).apply {
                if (exists()) {
                    Log.d("DiskCacheImpl","after fileSize:" +
                            "${this.length() / 1024}K,path:${this.absolutePath}")
                }
            }
        }
    }
}