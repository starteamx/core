package com.starteam.core.aspectj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.starteam.core.R;
import com.starteam.core.aspectj.tracking.TrackEvent;
import com.starteam.core.aspectj.tracking.TrackParameter;
import com.starteam.core.aspectj.withincode.Person;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        test();

        findViewById(R.id.tv).setOnClickListener(this);
    }

    private void test() {
//        testAfterThrowing();
        testAfterReturning();
        testAround();
        testWithInCode();
    }

    private void testWithInCode() {
        Person person = new Person();
        person.setAge(100);
        Log.e("weilu", "age--->" + person.getAge());
    }

    private void testAfterThrowing() {
        TextView textView = null;
        textView.setText("testAfterThrowing");
    }

    private TextView testAfterReturning() {
        return findViewById(R.id.tv);
    }

    private void testAround() {
        TextView textView = null;
        textView.setText("testAround");
    }

    @TrackEvent(eventName = "点击按钮", eventId = "100")
    private void trackMethod(@TrackParameter("uid") int uid, String name) {
        /*Intent intent = new Intent(this, KotlinActivity.class);
        intent.putExtra("uid", uid);
        intent.putExtra("name", name);
        startActivity(intent);*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv) {
            Log.e("weilu", "点击事件执行");
            trackMethod(10, "weilu");
        }
    }
}