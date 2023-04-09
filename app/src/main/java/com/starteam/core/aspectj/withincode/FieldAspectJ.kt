package com.starteam.core.aspectj.withincode

import android.util.Log
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Pointcut

@Aspect
class FieldAspectJ {
    @Pointcut("!withincode(com.starteam.core.aspectj.withincode.Person.new())")
    fun invokePerson() {
    }

    @Around("set(int com.starteam.core.aspectj.withincode.Person.age) && invokePerson()")
    @Throws(Throwable::class)
    fun aroundFieldSet(joinPoint: ProceedingJoinPoint) {
        Log.e("weilu", "around->" + joinPoint.target.toString() + "#" + joinPoint.signature.name)
    }
}