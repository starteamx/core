package com.starteam.core.widget.webview

import android.content.Context
import android.content.MutableContextWrapper
import android.util.Log
import com.starteam.core.widget.webview.core.BaseWebChromeClient
import com.starteam.core.widget.webview.core.BaseWebViewClient
import java.util.*

class WebViewPool private constructor() {

    companion object {

        private const val TAG = "WebViewPool"

        @Volatile
        private var instance: WebViewPool? = null

        fun getInstance(): WebViewPool {
            return instance ?: synchronized(this) {
                instance ?: WebViewPool().also { instance = it }
            }
        }
    }

    private val sPool = Stack<BaseDWebView>()
    private val lock = byteArrayOf()
    private var maxSize = 1

    /**
     * 设置 webview 池容量
     */
    fun setMaxPoolSize(size: Int) {
        synchronized(lock) { maxSize = size }
    }

    /**
     * 初始化webview 放在list中
     */
    fun init(context: Context, initSize: Int = maxSize) {
        for (i in 0 until initSize) {
            val view = BaseDWebView(MutableContextWrapper(context))
            view.setCustomWebChromeClient(BaseWebChromeClient())
            view.setCustomWebViewClient(BaseWebViewClient())
            sPool.push(view)
        }
    }

    /**
     * 获取webview
     */
    fun getWebView(context: Context): BaseDWebView {
        synchronized(lock) {
            val webView: BaseDWebView
            if (sPool.size > 0) {
                webView = sPool.pop()
                Log.d(TAG, "getWebView from pool")
            } else {
                webView = BaseDWebView(MutableContextWrapper(context))
                Log.d(TAG, "getWebView from create")
            }

            val contextWrapper = webView.context as MutableContextWrapper
            contextWrapper.baseContext = context

            webView.setCustomWebChromeClient(BaseWebChromeClient())
            webView.setCustomWebViewClient(BaseWebViewClient())
            return webView
        }
    }

    /**
     * 回收 WebView
     */
    fun recycle(webView: BaseDWebView) {
        // 释放资源
        webView.release()

        // 根据池容量判断是否销毁 【也可以增加其他条件 如手机低内存等等】
        val contextWrapper = webView.context as MutableContextWrapper
        contextWrapper.baseContext = webView.context.applicationContext
        synchronized(lock) {
            if (sPool.size < maxSize) {
                sPool.push(webView)
            } else {
                webView.destroy()
            }
        }
    }
}