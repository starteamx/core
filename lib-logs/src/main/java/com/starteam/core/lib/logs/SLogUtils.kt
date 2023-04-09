package com.starteam.core.lib.logs

import android.content.Context
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.printer.AndroidPrinter
import com.elvishew.xlog.printer.file.FilePrinter
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy2
import com.elvishew.xlog.printer.file.naming.ChangelessFileNameGenerator
import java.io.File

/**
 *
 *     author : guanrunbai
 *     time   : 2023/03/15
 *     desc   :
 *     version: 1.0
 *
 */
object SLogUtils {
    private const val logDirName = "log"
    private const val logFileName = "log.txt"
    private const val TAG = "AppLog"
    private const val VERTICAL = "|"
    private const val TAG_FORMAT = "%s.%s(L:%d)"

    /**
     * 根据堆栈生成TAG
     * @return TAG|className.methodName(L:lineNumber)
     */
    private fun generateTag(caller: StackTraceElement): String {
        var tag: String = TAG_FORMAT
        var callerClazzName = caller.className
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
        tag = String.format(tag, callerClazzName, caller.methodName, Integer.valueOf(caller.lineNumber))
        return StringBuilder().append(TAG).append(VERTICAL).append(tag).toString()
    }

    /**
     * 获取堆栈
     * @param n
     * n=0		VMStack
     * n=1		Thread
     * n=3		CurrentStack
     * n=4		CallerStack
     * ...
     * @return
     */
    private fun getStackTraceElement(n: Int): StackTraceElement {
        return Thread.currentThread().stackTrace[n]
    }

    /**
     * 获取调用方的堆栈TAG
     * @return
     */
    private fun getCallerStackLogTag(): String {
        return generateTag(getStackTraceElement(5))
    }

    //todo--后期可增加基于Logan的文件FilePrinter,File日志上传功能（通过Slog进行功能代理）
    //todo--后台增加日志文件压缩分享功能

    fun init(context: Context, isDebugMode: Boolean) {
        val lorDir = "${context.filesDir.absolutePath}${File.separator}$logDirName"
        //代码日志文件最多保存两个3M文件，自动处理文件大小跟数量
        val filePrinter = FilePrinter.Builder(lorDir).flattener(XFileFlatterer())
            .fileNameGenerator(ChangelessFileNameGenerator(logFileName))
            .backupStrategy(FileSizeBackupStrategy2(3 * 1024 * 1024, 1)).build()
        //日志级别v<d<i<w<e
        if (!isDebugMode) {
            //生产环境,日志保存在文件,打印级别i(包含)以上
            val logConfig =
                LogConfiguration.Builder().logLevel(LogLevel.INFO).tag("App-LOG").enableBorder().enableStackTrace(1)
                    .build()
            XLog.init(logConfig, filePrinter)
        } else {
            //测试环境，日志打印，保存文件，打印所有级别
            val logConfig =
                LogConfiguration.Builder().logLevel(LogLevel.ALL).tag("App-LOG").enableBorder().enableStackTrace(1)
                    .build()
            val androidPrinter = AndroidPrinter(true)
            XLog.init(logConfig, androidPrinter, filePrinter)
        }
    }

    // -----------------------------------Log.v
    /**
     * Log.v
     * @param msg
     */
    fun v(msg: String) {
        XLog.v(msg)
    }

    fun v(vararg args: Any) {
        XLog.v(args)
    }

    fun v(format: String, vararg args: Any) {
        XLog.v(format, args)
    }

    fun v(msg: String, t: Throwable) {
        XLog.v(msg, t)
    }

    fun v(t: Throwable) {
        XLog.v(t)
    }

    // -----------------------------------Log.d
    /**
     * Log.d
     * @param msg
     */
    fun d(msg: String) {
        XLog.d(msg)
    }

    fun d(vararg args: Any) {
        XLog.d(args)
    }

    fun d(format: String, vararg args: Any) {
        XLog.d(format, args)
    }

    fun d(msg: String, t: Throwable) {
        XLog.d(msg, t)
    }

    fun d(t: Throwable) {
        XLog.d(t)
    }


    // -----------------------------------Log.i
    /**
     * Log.i
     * @param msg
     */
    fun i(msg: String) {
        XLog.i(msg)
    }

    fun i(vararg args: Any) {
        XLog.i(args)
    }

    fun i(format: String, vararg args: Any) {
        XLog.i(format, args)
    }

    fun i(msg: String, t: Throwable) {
        XLog.i(msg, t)
    }

    fun i(t: Throwable) {
        XLog.i(t)
    }


    // -----------------------------------Log.w
    /**
     * Log.w
     * @param msg
     */
    fun w(msg: String) {
        XLog.w(msg)
    }

    fun w(vararg args: Any) {
        XLog.w(args)
    }

    fun w(format: String, vararg args: Any) {
        XLog.w(format, args)
    }

    fun w(msg: String, t: Throwable) {
        XLog.w(msg, t)
    }

    fun w(t: Throwable) {
        XLog.w(t)
    }

    // -----------------------------------Log.e

    /**
     * Log.e
     * @param msg
     */
    fun e(msg: String) {
        XLog.e(msg)
    }

    fun e(vararg args: Any) {
        XLog.e(args)
    }

    fun e(format: String, vararg args: Any) {
        XLog.e(format, args)
    }

    fun e(msg: String, t: Throwable) {
        XLog.e(msg, t)
    }

    fun e(t: Throwable) {
        XLog.e(t)
    }

}