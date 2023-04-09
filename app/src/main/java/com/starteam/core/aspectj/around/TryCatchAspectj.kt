package com.starteam.core.aspectj.around

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Pointcut
import java.lang.Exception

@Aspect
class TryCatchAspectj {
    @Pointcut("execution(* *..*.testAround())")
    fun methodTryCatch() {
    }

    @Around("methodTryCatch()")
    @Throws(Throwable::class)
    fun aroundTryJoinPoint(joinPoint: ProceedingJoinPoint) {
        try {
            joinPoint.proceed() // <- 调用原代码
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}