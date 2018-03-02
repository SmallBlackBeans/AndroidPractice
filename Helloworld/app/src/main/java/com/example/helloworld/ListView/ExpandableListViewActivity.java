package com.example.helloworld.ListView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandableListViewActivity extends AppCompatActivity {


    @BindView(R.id.expLv)
    ExpandableListView mExpLv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        ButterKnife.bind(this);



        ArrayList<String> groupNames =  new ArrayList<>();
        for (int i =0; i < 30; i ++) {
            groupNames.add("组" + i);
        }
        HashMap<String,ArrayList<String>> childNames = new HashMap<>();
        for (int i = 0; i < groupNames.size(); i ++) {
            ArrayList<String> children = new ArrayList<>();
            for (int j =0; j < 5; j ++) {
                children.add("成员" + j);
            }
            childNames.put(groupNames.get(i),children);
        }



        MyExpandableAdapter adapter = new MyExpandableAdapter(groupNames,childNames);
        mExpLv.setAdapter(adapter);

        //设置折叠监听
        mExpLv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        mExpLv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        mExpLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });


        mExpLv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });


    }
}
