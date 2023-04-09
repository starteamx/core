package com.starteam.core.widget.webview.example.main.command

import android.widget.Toast
import com.starteam.core.lib.appcontext.AppContextUtils
import com.starteam.core.widget.webview.bridge.ICommand

/**
 *
 *     author : guanrunbai
 *     time   : 2023/03/07
 *     desc   :
 *     version: 1.0
 *
 */
class ToastCommand : ICommand {
    override val commandName: String
        get() = "ToastCommand"

    override fun execute(): String {
        Toast.makeText(AppContextUtils.getApp(), "toast command", Toast.LENGTH_SHORT).show()
        return ""
    }
}