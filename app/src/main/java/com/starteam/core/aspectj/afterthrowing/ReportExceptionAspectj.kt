package com.starteam.core.aspectj.afterthrowing

import android.util.Log
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.AfterThrowing

@Aspect
class ReportExceptionAspectj {
    @AfterThrowing(pointcut = "call(* *..*.testAfterThrowing())", throwing = "throwable") // "throwable"必须和下面参数名称一样
    fun reportException(throwable: Throwable) {
        Log.e("weilu", "throwable--->$throwable")
    }
}