package com.example.helloworld.GridView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.helloworld.R;

/**
 * Created by hanchenghai on 2018/2/21.
 */

public class GridViewActivity extends Activity {

    private GridView mGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);


        mGridView = findViewById(R.id.gv);

        mGridView.setAdapter(new MyGridAdapter(this));

    }
}
