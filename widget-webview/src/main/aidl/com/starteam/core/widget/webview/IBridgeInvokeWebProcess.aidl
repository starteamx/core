// IBridgeInvokeWebProcess.aidl
package com.starteam.core.widget.webview;

// Declare any non-default types here with import statements

interface IBridgeInvokeWebProcess {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleBridgeCallback(String callback, String params);
}