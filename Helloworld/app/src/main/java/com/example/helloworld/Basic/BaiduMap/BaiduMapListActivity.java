package com.example.helloworld.Basic.BaiduMap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.helloworld.R;

public class BaiduMapListActivity extends AppCompatActivity {



    String[] strs = new String[]{"地图标注","定位"};
    Class[] classes = new Class[]{MarkerActivity.class,LocationActivity.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map_list);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, strs);
        listView.setAdapter(stringArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),classes[position]);
                startActivity(intent);
            }
        });
    }

}
