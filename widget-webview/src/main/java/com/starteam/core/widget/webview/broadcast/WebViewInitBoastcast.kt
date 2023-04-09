package com.starteam.core.widget.webview.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.starteam.core.widget.webview.WebViewPool
import kotlin.math.min

class WebViewInitBoastcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("WebViewInitBoastcast", "initWebViewPool")
        initWebViewPool(context)
    }

    private fun initWebViewPool(context: Context) {
        // 根据手机 CPU 核心数（或者手机内存）设置缓存池容量
        WebViewPool.getInstance().setMaxPoolSize(min(Runtime.getRuntime().availableProcessors(), 3))
        WebViewPool.getInstance().init(context)
    }
}