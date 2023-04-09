package com.starteam.core.aspectj

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.starteam.core.R
import com.starteam.core.aspectj.tracking.TrackEvent
import com.starteam.core.aspectj.tracking.TrackParameter
import com.starteam.core.aspectj.withincode.Person


class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        test()
        findViewById<TextView>(R.id.tv).setOnClickListener(this)
    }

    private fun test() {
//        testAfterThrowing();
        testAfterReturning()
        testAround()
        testWithInCode()
    }

    private fun testWithInCode() {
        val person = Person()
        person.setAge("1111")
        Log.e("weilu", "age--->" + person.age)
    }

    private fun testAfterThrowing() {
        val textView: TextView? = null
        textView!!.text = "testAfterThrowing"
    }

    private fun testAfterReturning(): TextView? {
        return findViewById(R.id.tv) as? TextView
    }

    private fun testAround() {
        val textView: TextView? = null
        textView!!.text = "testAround"
    }

    @TrackEvent(eventName = "点击按钮", eventId = "100")
    private fun trackMethod(@TrackParameter("uid") uid: Int, name: String) {
        Log.e("uid", "age--->$name")
    }

    override fun onClick(v: View) {
        if (v.id == R.id.tv) {
            Log.e("weilu", "点击事件执行")
            trackMethod(10, "weilu")
        }
    }
}