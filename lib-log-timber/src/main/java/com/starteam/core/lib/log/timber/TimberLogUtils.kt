package com.starteam.core.lib.log.timber

import android.content.Context
import com.starteam.cor.lib.log.ILogReporter
import com.starteam.cor.lib.log.ILogger

/**
 *
 *     author : guanrunbai
 *     time   : 2023/01/10
 *     desc   :
 *     version: 1.0
 *
 */
object TimberLogUtils {
    lateinit var timberLogger: TimberLogger

    fun initTimberLogger(context: Context, debugMode: Boolean, logReporters: Set<ILogReporter>) {
        logReporters.forEach {
            it.init(context)
        }
        timberLogger = TimberLogger(TimberLogReporterTree(logReporters))
        timberLogger.setup(debugMode)
    }

    fun getILogger(): ILogger {
        return timberLogger
    }

}