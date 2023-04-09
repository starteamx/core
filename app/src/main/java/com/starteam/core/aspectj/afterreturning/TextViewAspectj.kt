package com.starteam.core.aspectj.afterreturning

import android.util.Log
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.AfterReturning
import android.widget.TextView

@Aspect
class TextViewAspectj {
    @AfterReturning(pointcut = "execution(* *..*.testAfterReturning())", returning = "textView")
    fun getTextView(textView: TextView) {
        Log.d("weilu", "text--->" + textView.text.toString())
    }
}