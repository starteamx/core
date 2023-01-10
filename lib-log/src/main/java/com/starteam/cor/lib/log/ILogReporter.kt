package com.starteam.cor.lib.log

import android.content.Context

/**
 *
 *     author : guanrunbai
 *     time   : 2023/01/10
 *     desc   : 日志打印类
 *     version: 1.0
 *
 */
interface ILogReporter {
    fun init(context: Context)

    fun log(priority: Int, tag: String?, message: String, t: Throwable?)

    fun isLoggable(tag: String?, priority: Int): Boolean
}