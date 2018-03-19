package com.example.helloworld.Basic.MemoryLeak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.R;
import com.example.helloworld.Basic.SingleInstance.ToastManager;

public class ContextLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_leak);
    }


    public void click(View view) {
        //会造成引用
        //ToastManager.getInstance(ContextLeakActivity.this).showToast("你好吗");
        ToastManager.getInstance(getApplicationContext()).showToast("你好吗");
    }
}
