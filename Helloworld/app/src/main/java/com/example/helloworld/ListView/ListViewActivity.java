package com.example.helloworld.ListView;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.helloworld.R;

public class ListViewActivity extends Activity {

    private ListView mlv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mlv1 = findViewById(R.id.lv_1);
        mlv1.setAdapter(new MyListAdapter(ListViewActivity.this));
        mlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this,"点我干嘛" + i, Toast.LENGTH_SHORT).show();
                //跳转详情
            }
        });

        mlv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this,"点我干嘛" + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
