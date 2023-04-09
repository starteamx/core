package com.starteam.core.widget.webview.example.webview

import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.starteam.core.widget.webview.BaseDWebView
import com.starteam.core.widget.webview.R
import com.starteam.core.widget.webview.WebViewPool
import com.starteam.core.widget.webview.example.webview.service.JsBridgeInvokeDispatcher

/**
 *
 *     author : guanrunbai
 *     time   : 2023/03/07
 *     desc   :
 *     version: 1.0
 *
 */
class WebActivity : AppCompatActivity() {

    lateinit var mWebView: BaseDWebView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        //需要先init
        mWebView = WebViewPool.getInstance().getWebView(this)
        mWebView.setLifecycleOwner(this)

        findViewById<FrameLayout>(R.id.webViewContainer).addView(
            mWebView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        JsBridgeInvokeDispatcher.getInstance().bindService(this, mWebView)

        mWebView.addJavascriptObject(BaseCommandJsApi(), "")
        mWebView.loadUrl("file:///android_asset/js-call-native.html")
    }

    class BaseCommandJsApi {

        @JavascriptInterface
        fun sendCommand(command: Any): String? {
            //return command.toString()
            return JsBridgeInvokeDispatcher.getInstance().sendCommand(command.toString(), "{}")
        }
    }

    override fun onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        JsBridgeInvokeDispatcher.getInstance().unbindService(this)
    }

}