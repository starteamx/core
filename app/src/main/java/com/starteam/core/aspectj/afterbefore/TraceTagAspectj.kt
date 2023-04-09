package com.starteam.core.aspectj.afterbefore

import android.os.Trace
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Before

@Aspect
class TraceTagAspectj {
    @Before("execution(* android.app.Activity+.onCreate(..))")
    fun before(joinPoint: JoinPoint) {
        Trace.beginSection(joinPoint.signature.toString())
    }

    @After("execution(* android.app.Activity+.onCreate(..))")
    fun after() {
        Trace.endSection()
    }
}