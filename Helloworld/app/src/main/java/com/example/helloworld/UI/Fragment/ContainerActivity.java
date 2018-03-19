package com.example.helloworld.UI.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helloworld.R;

public class ContainerActivity extends AppCompatActivity implements FragmentA.IOnMessageClick {

    private FragmentA aFragment;

    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //实例化
        aFragment = FragmentA.newInstance("你好");
        //添加到Activity
        //getSupportFragmentManager() 获取低版本兼容的FragmentManager 3.0以下
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, aFragment, "tagA")
                .commitAllowingStateLoss();


        mTitle = findViewById(R.id.tv_title);
    }


    //不推荐
    public void setData(String text) {
        mTitle.setText(text);
    }

    @Override
    public void onClick(String message) {
        mTitle.setText(message);
    }
}
