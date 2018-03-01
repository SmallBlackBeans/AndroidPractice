package com.example.helloworld;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.Event_Listener.EventListenerActivity;
import com.example.helloworld.Handler.HandlerActivity;
import com.example.helloworld.Parse.XmlParseActivity;
import com.example.helloworld.datastorage.DataStorageActivity;
//import com.idescout.sql.SqlScoutServer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_ui)
    Button mBtnUi;
    @BindView(R.id.btn_lifecycle)
    Button mBtnLifecycle;
    @BindView(R.id.btn_event)
    Button mBtnEvent;
    @BindView(R.id.btn_handler)
    Button mBtnHandler;
    @BindView(R.id.btn_data)
    Button mBtnData;
    @BindView(R.id.btn_parse)
    Button mBtnParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SqlScoutServer.create(this, getPackageName());
        ButterKnife.bind(this);
        //动态获取存储权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @butterknife.OnClick({R.id.btn_ui, R.id.btn_lifecycle, R.id.btn_event, R.id.btn_handler, R.id.btn_data, R.id.btn_parse})
    public void onViewClicked(View view) {
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
            case R.id.btn_parse:
                intent = new Intent(MainActivity.this, XmlParseActivity.class);
                break;
        }
        startActivity(intent);
    }
}



















