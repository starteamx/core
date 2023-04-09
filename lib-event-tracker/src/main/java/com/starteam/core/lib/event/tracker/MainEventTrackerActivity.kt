package com.starteam.core.lib.event.tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI

class MainEventTrackerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_event_tracker)

        SensorsDataAPI.sharedInstance().track("sdddsdf")
    }
}