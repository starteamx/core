package com.starteam.core.aspectj.tracking

import android.util.Log
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.ProceedingJoinPoint
import org.json.JSONObject
import com.starteam.core.aspectj.tracking.TrackParameter
import org.aspectj.lang.reflect.MethodSignature

@Aspect
class TrackEventAspectj {
    @Around("execution(@com.starteam.core.aspectj.tracking.TrackEvent * *(..))")
    @Throws(Throwable::class)
    fun trackEvent(joinPoint: ProceedingJoinPoint) {
        val signature = joinPoint.signature as MethodSignature

        // 获取方法上的注解
        val trackEvent = signature.method.getAnnotation(
            TrackEvent::class.java
        )
        val eventName: String = trackEvent.eventName
        val eventId: String = trackEvent.eventId
        val params = JSONObject()
        params.put("eventName", eventName)
        params.put("eventId", eventId)

        // 获取方法参数的注解
        val parameterAnnotations = signature.method.parameterAnnotations
        if (parameterAnnotations.size != 0) {
            var i = 0
            for (parameterAnnotation in parameterAnnotations) {
                for (annotation in parameterAnnotation) {
                    if (annotation is TrackParameter) {
                        // 获取key value
                        val key = annotation.value
                        params.put(key, joinPoint.args[i++])
                    }
                }
            }
        }

        // 上报
        Log.e("weilu", "上报数据---->$params")
        try {
            joinPoint.proceed()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
    }
}