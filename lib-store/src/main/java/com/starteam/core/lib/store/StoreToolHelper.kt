package com.starteam.core.lib.store

import android.app.Application
import com.starteam.core.lib.store.datastore.DataStoreCacheImpl
import com.starteam.core.lib.store.mmkv.MmkvCacheImpl

/**
 *
 *     author : guanrunbai
 *     time   : 2023/01/04
 *     desc   :
 *     version: 1.0
 *
 */
class StoreToolHelper {

    private var sApplication: Application? = null
    private var storeCache: BaseDataCache? = null
    private var mmkvCache: BaseDataCache? = null


    companion object {

        private var INSTANCE: StoreToolHelper? = null

        /**
         * 获取单例模式对象
         */
        @JvmStatic
        val instance: StoreToolHelper
            get() {
                if (INSTANCE == null) {
                    synchronized(StoreToolHelper::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE = StoreToolHelper()
                        }
                    }
                }
                return INSTANCE!!
            }
    }

    fun setApp(app: Application?) {
        if (app == null) {
            throw NullPointerException("Argument 'app' of type Application (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it")
        } else {
            sApplication = app
        }
    }

    val app: Application?
        get() = if (sApplication != null) {
            sApplication
        } else {
            throw NullPointerException("u should init first")
        }

    fun getStoreCache(): BaseDataCache {
        if (storeCache == null) {
            storeCache = BaseDataCache()
            storeCache?.setCacheImpl(DataStoreCacheImpl())
        }
        return storeCache as BaseDataCache
    }

    fun getMmkvDiskCache(): BaseDataCache {
        if (mmkvCache == null) {
            mmkvCache = BaseDataCache()
            val builder = MmkvCacheImpl.Builder()
            builder.fileName = "Mmkv"
            val diskCacheImpl = builder.build()
            mmkvCache?.setCacheImpl(diskCacheImpl)
        }
        return mmkvCache as BaseDataCache
    }
}