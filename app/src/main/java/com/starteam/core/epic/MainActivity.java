package com.starteam.core.epic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.starteam.core.R;
import com.starteam.core.epic.tests.TestCase;
import com.starteam.core.epic.tests.TestManager;
import com.starteam.core.epic.tests.TestSuite;

import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    ExpandableListView listView;

    List<TestSuite> allSuites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "savedInstance:" + savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Epic Test");
        listView = findViewById(R.id.list);
        allSuites = TestManager.getInstance().getAllSuites();
        ExpandableListAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }

    private class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return allSuites.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return allSuites.get(groupPosition).getAllCases().size();
        }

        @Override
        public TestSuite getGroup(int groupPosition) {
            return allSuites.get(groupPosition);
        }

        @Override
        public TestCase getChild(int groupPosition, int childPosition) {
            return allSuites.get(groupPosition).getAllCases().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View parentView = View.inflate(parent.getContext(), R.layout.parent_layout, null);
            TextView t = parentView.findViewById(R.id.text);
            t.setText(getGroup(groupPosition).getName());

            ImageView indicator = parentView.findViewById(R.id.indicator);
            if (isExpanded) {
                indicator.setImageResource(R.drawable.arrow_down);
            } else {
                indicator.setImageResource(R.drawable.arrow_up);
            }
            return parentView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final TestCase child = getChild(groupPosition, childPosition);

            final View childView = View.inflate(parent.getContext(), R.layout.child_layout, null);
            final TextView title = childView.findViewById(R.id.label);
            title.setText(child.getName());

            Button test = childView.findViewById(R.id.test);
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    child.test();
                }
            });

            Button validate = childView.findViewById(R.id.validate);
            validate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean validate = child.validate();
                    final int color = v.getContext().getResources().getColor(validate ?
                            android.R.color.holo_green_light : android.R.color.holo_red_light);
                    childView.setBackgroundColor(color);
                }
            });

            return childView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
