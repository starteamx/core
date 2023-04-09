// IBridgeInvokeMainProcess.aidl
package com.starteam.core.widget.webview;

// Declare any non-default types here with import statements
import com.starteam.core.widget.webview.IBridgeInvokeWebProcess;

interface IBridgeInvokeMainProcess {

    String handleWebViewBridgeInvoke(String command,String params);

    void setIBridgeInvokeWebProcessCallback(IBridgeInvokeWebProcess bridgeCallback);
}