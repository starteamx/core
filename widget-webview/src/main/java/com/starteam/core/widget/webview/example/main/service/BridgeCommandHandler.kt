package com.starteam.core.widget.webview.example.main.service

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.starteam.core.lib.appcontext.AppContextUtils
import com.starteam.core.lib.gson.GsonUtils
import com.starteam.core.widget.webview.IBridgeInvokeMainProcess
import com.starteam.core.widget.webview.IBridgeInvokeWebProcess
import com.starteam.core.widget.webview.bridge.ICommand
import com.starteam.core.widget.webview.utils.WebViewProcessUtils


class BridgeCommandHandler : IBridgeInvokeMainProcess.Stub() {

    private var bridgeCallback: IBridgeInvokeWebProcess? = null

    companion object {
        private const val TAG = "BridgeCommandHandler"

        @Volatile
        private var sInstance: BridgeCommandHandler? = null

        fun getInstance(): BridgeCommandHandler {
            if (sInstance == null) {
                synchronized(this) {
                    if (sInstance == null) {
                        Log.e(
                            "BridgeCommandHandler",
                            "初始化 当前进程：${WebViewProcessUtils.getCurrentProcessName(AppContextUtils.getApp())}"
                        )
                        sInstance = BridgeCommandHandler()
                    }
                }
            }
            return sInstance!!
        }
    }

    // 用于切线程
    private val mHandle = Handler(Looper.getMainLooper())
    override fun handleWebViewBridgeInvoke(command: String?, params: String?): String {
        val commandExe = GsonUtils.fromJson(
            params,
            Class.forName("com.starteam.core.widget.webview.example.main.command.$command")
        ) as ICommand
        mHandle.post {
            commandExe.execute()
        }
        return ""
    }

    override fun setIBridgeInvokeWebProcessCallback(bridgeCallback: IBridgeInvokeWebProcess?) {
        this.bridgeCallback = bridgeCallback
    }

    /*
        private val mCommandMap: ArrayMap<String, IBridgeCommand> by lazy { JsBridgeUtil.autoRegist() }

        // 暴露给外部方法 分发调用
        override fun handleBridgeInvoke(command: String?, params: String?, bridgeCallback: IBridgeInvokeWebProcess?) {
            LogUtils.e(TAG, "handleBridgeInvoke()", "当前进程：${ProcessUtils.getCurrentProcessName()}")
            // map 中存在命令 则执行
            if (mCommandMap.contains(command)) {
                mHandle.post { // 切换到主线程 获取命令 执行
                    mCommandMap[command]!!.exec(parseParams(params), bridgeCallback)
                }
            } else {
                LogUtils.e(TAG, "handleBridgeInvoke()", "command[${command}] is not register!")
            }
        }

        private fun parseParams(params: String?): JsonObject? {
            if (params.isNullOrEmpty()) {
                return null
            }
            return GsonUtils.fromJson(params, JsonObject::class.java)
        }*/

}