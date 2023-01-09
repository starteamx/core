package com.starteam.core.lib.store.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.starteam.core.lib.appcontext.AppContextUtils
import com.starteam.core.lib.store.ICacheable
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
 *
 *     author : guanrunbai
 *     time   : 2023/01/04
 *     desc   : DataStore存储实现类
 *     version: 1.0
 *
 */
class DataStoreCacheImpl : ICacheable {
    private val Context.dataStoreCache: DataStore<Preferences> by preferencesDataStore(
        name = "dataStoreCache"
    )

    private val cache by lazy {
        AppContextUtils.getApp().dataStoreCache
    }

    init {
        AppContextUtils.getApp().dataStoreCache
    }

    override fun saveInt(key: String, value: Int) {
        runBlocking {
            cache.edit { it[intPreferencesKey(key)] = value }
        }
    }

    override fun readInt(key: String, default: Int): Int {
        var value = default
        runBlocking {
            cache.data.first {
                try {
                    it[intPreferencesKey(key)]?.run {
                        value = this
                    }
                } catch (e: Exception) {

                }
                true
            }
        }
        return value
    }

    override fun saveFloat(key: String, value: Float) {
        runBlocking {
            cache.edit { it[floatPreferencesKey(key)] = value }
        }
    }

    override fun readFloat(key: String, default: Float): Float {
        var value = default
        runBlocking {
            cache.data.first {
                try {
                    it[floatPreferencesKey(key)]?.run {
                        value = this
                    }
                } catch (e: Exception) {

                }
                true
            }
        }
        return value
    }

    override fun saveDouble(key: String, value: Double) {
        runBlocking {
            cache.edit { it[doublePreferencesKey(key)] = value }
        }
    }

    override fun readDouble(key: String, default: Double): Double {
        var value = default
        runBlocking {
            cache.data.first {
                try {
                    it[doublePreferencesKey(key)]?.run {
                        value = this
                    }
                } catch (e: Exception) {

                }
                true
            }
        }
        return value
    }

    override fun saveLong(key: String, value: Long) {
        runBlocking {
            cache.edit { it[longPreferencesKey(key)] = value }
        }
    }

    override fun readLong(key: String, default: Long): Long {
        var value = default
        runBlocking {
            cache.data.first {
                try {
                    it[longPreferencesKey(key)]?.run {
                        value = this
                    }
                } catch (e: Exception) {

                }
                true
            }
        }
        return value
    }

    override fun saveString(key: String, value: String) {
        runBlocking {
            cache.edit { it[stringPreferencesKey(key)] = value }
        }
    }

    override fun readString(key: String, default: String): String {
        var value = default
        runBlocking {
            cache.data.first {
                try {
                    it[stringPreferencesKey(key)]?.run {
                        value = this
                    }
                } catch (e: Exception) {

                }
                true
            }
        }
        return value
    }

    override fun saveBoolean(key: String, value: Boolean) {
        runBlocking {
            cache.edit { it[booleanPreferencesKey(key)] = value }
        }
    }

    override fun readBoolean(key: String, default: Boolean): Boolean {
        var value = default
        runBlocking {
            cache.data.first {
                try {
                    it[booleanPreferencesKey(key)]?.run {
                        value = this
                    }
                } catch (e: Exception) {

                }
                true
            }
        }
        return value
    }

    override fun removeKey(key: String) {
        runBlocking {
            // 由于 DataStore 不支持 remove，所以这里都还原成默认值
            cache.edit {
                it[intPreferencesKey(key)] = 0
                it[floatPreferencesKey(key)] = 0F
                it[doublePreferencesKey(key)] = 0.0
                it[longPreferencesKey(key)] = 0L
                it[booleanPreferencesKey(key)] = false
                // （String 的默认值本应是 null，由于不能为 null，这里还原成 ""）
                it[stringPreferencesKey(key)] = ""
            }
        }
    }

    override fun totalSize(): Long {
        var value = 0L
        runBlocking {
            cache.data.count {
                try {
                    it.asMap().size.run {
                        value = this.toLong()
                    }
                } catch (e: Exception) {
                }
                true
            }
        }
        return value
    }

    override fun clearData() {
        runBlocking {
            cache.edit {
                //将该datastore中的数据全部清除
                it.clear()
            }
        }
    }

    fun remove(key: String) {
        runBlocking {
            // 由于 DataStore 不支持 remove，所以这里都还原成默认值
            cache.edit {
                it[intPreferencesKey(key)] = 0
                it[floatPreferencesKey(key)] = 0F
                it[doublePreferencesKey(key)] = 0.0
                it[longPreferencesKey(key)] = 0L
                it[booleanPreferencesKey(key)] = false
                // （String 的默认值本应是 null，由于不能为 null，这里还原成 ""）
                it[stringPreferencesKey(key)] = ""
            }
        }
    }
}