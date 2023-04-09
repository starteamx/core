package com.starteam.core.lib.hook.delegate

import android.animation.Animator
import android.animation.ObjectAnimator
import java.lang.reflect.Proxy

/**
 *
 *     author : guanrunbai
 *     time   : 2023/03/17
 *     desc   :
 *     version: 1.0
 *
 */
inline fun <reified T : Any> noOpDelegate(): T {
    val javaClass = T::class.java
    return Proxy.newProxyInstance(javaClass.classLoader, arrayOf(javaClass)) { _, _, _ ->

    } as T
}

class DelegateExt {
}