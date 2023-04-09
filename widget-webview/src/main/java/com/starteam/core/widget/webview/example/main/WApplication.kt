package com.starteam.core.widget.webview.example.main

/**
 *
 *     author : guanrunbai
 *     time   : 2023/03/07
 *     desc   :
 *     version: 1.0
 *
 */
class WApplication {
        /*AppProcessUtils.isMainProcess(this).takeIf { it }?.let {
            initWebViewPool()
       }*/
    //java.lang.RuntimeException: Using WebView from more than one process at once with the same data directory is not supported.
        /*private fun initWebViewPool() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val packageName = this.packageName
                val processName = ProcessUtils.getCurrentProcessName()
                if (packageName != processName) {
                    WebView.setDataDirectorySuffix(packageName)
                }
            }

            // 用广播提前拉起 :web进程
            val intent = Intent(this, WebViewInitBoastcast::class.java)
            sendBroadcast(intent)
        }*/



}