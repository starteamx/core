package com.starteam.core.aspectj.cflow

import android.util.Log
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.ProceedingJoinPoint

@Aspect
class TimingAspect {
    @Around("execution(* *(..)) && cflow(execution(* com.starteam.core.aspectj.MainActivity.test(..)))")
    @Throws(Throwable::class)
    fun measureExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val startTime = System.currentTimeMillis()
        val result:Any? = joinPoint.proceed()
        val endTime = System.currentTimeMillis()
        Log.e("weilu", joinPoint.signature.toString() + " -> " + (endTime - startTime) + " ms")
        return result
    }
}