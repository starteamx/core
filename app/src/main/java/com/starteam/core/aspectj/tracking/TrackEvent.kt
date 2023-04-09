package com.starteam.core.aspectj.tracking

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(
    RetentionPolicy.RUNTIME
)
annotation class TrackEvent(
    /**
     * 事件名称
     */
    val eventName: String = "",
    /**
     * 事件id
     */
    val eventId: String = ""
)