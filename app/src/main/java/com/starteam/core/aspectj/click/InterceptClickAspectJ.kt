package com.starteam.core.aspectj.click

import android.util.Log
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.ProceedingJoinPoint
import com.starteam.core.aspectj.click.InterceptClickAspectJ

@Aspect
class InterceptClickAspectJ {
    // 最后一次点击的时间
    private var lastTime = 0L
    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    @Throws(Throwable::class)
    fun clickIntercept(joinPoint: ProceedingJoinPoint) {
        // 大于间隔时间可点击
        if (System.currentTimeMillis() - lastTime >= INTERVAL) {
            // 记录点击时间
            lastTime = System.currentTimeMillis()
            // 执行点击事件
            joinPoint.proceed()
        } else {
            Log.e("weilu", "重复点击")
        }
    }

    @Around("execution(* *..lambda$*(android.view.View))")
    @Throws(Throwable::class)
    fun clickInterceptLambda(joinPoint: ProceedingJoinPoint) {
        // 大于间隔时间可点击
        if (System.currentTimeMillis() - lastTime >= INTERVAL) {
            // 记录点击时间
            lastTime = System.currentTimeMillis()
            // 执行点击事件
            joinPoint.proceed()
        } else {
            Log.e("weilu", "重复点击")
        }
    }

    companion object {
        // 点击间隔时长
        private const val INTERVAL = 300L
    }
}