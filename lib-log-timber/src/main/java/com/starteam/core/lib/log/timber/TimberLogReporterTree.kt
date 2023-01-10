package com.starteam.core.lib.log.timber

import com.starteam.cor.lib.log.ILogReporter
import timber.log.Timber

/**
 *
 *     author : guanrunbai
 *     time   : 2023/01/10
 *     desc   :
 *     version: 1.0
 *
 */
class TimberLogReporterTree(val logReporters: Set<ILogReporter>) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        logReporters.forEach {
            if (it.isLoggable(tag, priority))
            {
                it.log(priority, tag, message, t)
            }
        }
    }
}