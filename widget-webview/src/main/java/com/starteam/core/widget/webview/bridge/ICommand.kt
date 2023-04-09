package com.starteam.core.widget.webview.bridge

/**
 *
 *     author : guanrunbai
 *     time   : 2023/03/07
 *     desc   :
 *     version: 1.0
 *
 */
interface ICommand {
    val commandName: String
    fun execute(): String
}