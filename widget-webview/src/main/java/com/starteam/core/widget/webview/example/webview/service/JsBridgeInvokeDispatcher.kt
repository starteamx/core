package com.starteam.core.widget.webview.example.webview.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.starteam.core.lib.appcontext.AppContextUtils
import com.starteam.core.lib.gson.GsonUtils
import com.starteam.core.widget.webview.BaseDWebView
import com.starteam.core.widget.webview.IBridgeInvokeMainProcess
import com.starteam.core.widget.webview.IBridgeInvokeWebProcess
import com.starteam.core.widget.webview.example.main.service.BridgeCommandService
import com.starteam.core.widget.webview.utils.WebViewProcessUtils


class JsBridgeInvokeDispatcher : ServiceConnection {
    private var mWebView: BaseDWebView? = null

    companion object {
        private const val TAG = "BridgeInvokeDispatcher"

        @Volatile
        private var sInstance: JsBridgeInvokeDispatcher? = null

        fun getInstance(): JsBridgeInvokeDispatcher {
            if (sInstance == null) {
                synchronized(this) {
                    if (sInstance == null) {
                        sInstance = JsBridgeInvokeDispatcher()
                    }
                }
            }
            return sInstance!!
        }
    }

    private var iBridgeInvokeMainProcess: IBridgeInvokeMainProcess? = null

    fun bindService(context: Context, webView: BaseDWebView) {
        Log.d(TAG, "bindService()")
        if (iBridgeInvokeMainProcess == null) {
            val i = Intent(context, BridgeCommandService::class.java)
            context.bindService(i, this, Context.BIND_AUTO_CREATE)
        }

        mWebView = webView
    }

    fun unbindService(context: Context) {
        Log.d(TAG, "unbindService()")
        iBridgeInvokeMainProcess = null
        context.unbindService(this)
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        iBridgeInvokeMainProcess = IBridgeInvokeMainProcess.Stub.asInterface(service)
        initCallJsHandler()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        iBridgeInvokeMainProcess = null
    }

    fun sendCommand(command: String, paramsJson: String):String? {
        Log.d(TAG, "sendCommand() message: $command params $paramsJson")
        return iBridgeInvokeMainProcess?.handleWebViewBridgeInvoke(command, paramsJson)
    }

    private fun initCallJsHandler() {
        val callback = object : IBridgeInvokeWebProcess.Stub() {
            override fun handleBridgeCallback(callback: String, params: String) {
                Log.e(TAG, "当前进程: ${WebViewProcessUtils.getCurrentProcessName(AppContextUtils.getApp())}")
                mWebView?.callHandler(callback, GsonUtils.fromJson(params, GsonUtils.getArrayType(String::class.java)))
            }
        }
        iBridgeInvokeMainProcess?.setIBridgeInvokeWebProcessCallback(callback)
    }
}