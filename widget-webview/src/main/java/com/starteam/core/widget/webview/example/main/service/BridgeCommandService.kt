package com.starteam.core.widget.webview.example.main.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.starteam.core.widget.webview.example.main.service.BridgeCommandHandler

class BridgeCommandService: Service() {
    override fun onBind(intent: Intent?): IBinder {
        return BridgeCommandHandler.getInstance()
    }
}