package com.example.helloworld;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.Event_Listener.EventListenerActivity;
import com.example.helloworld.GridView.GridViewActivity;
import com.example.helloworld.Handler.HandlerActivity;
import com.example.helloworld.ListView.ListViewActivity;
import com.example.helloworld.RecycleView.RecyclerViewActivity;
import com.example.helloworld.WebView.WebViewActivity;
import com.example.helloworld.datastorage.DataStorageActivity;

public class MainActivity extends AppCompatActivity {


    private Button mBtnUI;

    private Button mBtnLifeCycle;

    private Button mBtnEvent;

    private Button mBtnHandler;

    private Button mBtnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnUI = findViewById(R.id.btn_ui);
        mBtnLifeCycle = findViewById(R.id.btn_lifecycle);
        mBtnEvent = findViewById(R.id.btn_event);
        mBtnHandler = findViewById(R.id.btn_handler);
        mBtnData = findViewById(R.id.btn_data);
        setListeners();

        //动态获取存储权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void setListeners() {
        OnClick onclick = new OnClick();
        mBtnUI.setOnClickListener(onclick);
        mBtnLifeCycle.setOnClickListener(onclick);
        mBtnEvent.setOnClickListener(onclick);
        mBtnHandler.setOnClickListener(onclick);
        mBtnData.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_ui:
                    intent = new Intent(MainActivity.this, UIActivity.class);
                    break;

                case R.id.btn_lifecycle:
                    intent = new Intent(MainActivity.this, LifeCycleActivity.class);
                    break;
                case R.id.btn_event:
                    intent = new Intent(MainActivity.this, EventListenerActivity.class);
                    break;

                case R.id.btn_handler:
                    intent = new Intent(MainActivity.this, HandlerActivity.class);
                    break;
                case R.id.btn_data:
                    intent = new Intent(MainActivity.this, DataStorageActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}



















