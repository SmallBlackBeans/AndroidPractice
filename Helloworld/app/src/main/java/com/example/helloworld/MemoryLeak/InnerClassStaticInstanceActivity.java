package com.example.helloworld.MemoryLeak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.R;

public class InnerClassStaticInstanceActivity extends AppCompatActivity {


    //静态实例不会跟着外部类的关闭而销毁， static 会延长生命时长
    public static User sUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class_static_instance);
    }


    private void click(View view) {
        sUser = new User();
    }


    //非静态的内部类,隐式持有外部类
    private class User {
        User() {

        }
    }




}
