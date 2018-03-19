package com.example.helloworld.UI.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewActivity extends Activity {

    private ListView mlv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mlv1 = findViewById(R.id.lv_1);
//
//        //1
//        mlv1.setAdapter(new MyListAdapter(ListViewActivity.this));

        //2
//        ArrayList<String> arrayList = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            arrayList.add("niaho" + i);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
//        mlv1.setAdapter(adapter);


        //3
        ArrayList<HashMap<String, Object>> datas = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 7; i++) {
            //代表每一项的数据
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("文字", "测试" + i);
            data.put("图片", R.drawable.icon_username);
            datas.add(data);
        }
        SimpleAdapter simpleAdapter =
                new SimpleAdapter(this,
                        datas,
                        R.layout.lv_item_layout,
                        new String[]{"文字", "图片"},
                        new int[]{R.id.tv, R.id.iv}
                );
        mlv1.setAdapter(simpleAdapter);

        mlv1.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


        mlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "点我干嘛" + i, Toast.LENGTH_SHORT).show();
                //跳转详情
            }
        });

        mlv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "点我干嘛" + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

}
