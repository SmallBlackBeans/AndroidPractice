package com.example.helloworld;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.Basic.AsynTask.AsynTaskActivity;
import com.example.helloworld.Basic.BaiduMap.BaiduMapListActivity;
import com.example.helloworld.Basic.BroadCast.ScreenLockReceiver;
import com.example.helloworld.Basic.Event_Listener.EventListenerActivity;
import com.example.helloworld.Basic.Handler.HandlerActivity;
import com.example.helloworld.Basic.ImageLoad.ImageListActivity;
import com.example.helloworld.Basic.Music.MusicListActivity;
import com.example.helloworld.Basic.Network.NetWorkActivity;
import com.example.helloworld.Basic.Notice.SMSNoticeActivity;
import com.example.helloworld.UI.LifeCycleActivity;
import com.example.helloworld.Basic.datastorage.DataStorageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 基础知识学习
 */
public class BasicActivity extends AppCompatActivity {

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
    @BindView(R.id.btn_network)
    Button mBtnNetWork;
    @BindView(R.id.btn_music)
    Button mBtnMusic;
    @BindView(R.id.btn_notify)
    Button mBtnNotify;
    @BindView(R.id.btn_image)
    Button mBtnImage;
    @BindView(R.id.btn_baidumap)
    Button mBtnBaidumap;
    @BindView(R.id.btn_aysnTask)
    Button mBtnAysnTask;
    private ScreenLockReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        //SqlScoutServer.create(this, getPackageName());
        ButterKnife.bind(this);

        //动态获取存储权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        //动态获取读取权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


        /*
            <receiver android:name=".广播.ScreenLockReceiver">
                <intent-filter>
                    <!--屏幕锁屏与解锁-->
                    <action android:name="android.intent.action.SCREEN_OFF"/>
                    <action android:name="android.intent.action.SCREEN_ON"/>
                </intent-filter>
            </receiver>
        */
        //锁屏和解锁是一个耗时操作，所以监听最好放在这里
        mReceiver = new ScreenLockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @OnClick({R.id.btn_ui, R.id.btn_lifecycle,
            R.id.btn_event, R.id.btn_handler,
            R.id.btn_data, R.id.btn_network,
            R.id.btn_music, R.id.btn_notify,
            R.id.btn_image, R.id.btn_baidumap,
            R.id.btn_aysnTask
    })
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_lifecycle:
                intent = new Intent(BasicActivity.this, LifeCycleActivity.class);
                break;
            case R.id.btn_event:
                intent = new Intent(BasicActivity.this, EventListenerActivity.class);
                break;
            case R.id.btn_handler:
                intent = new Intent(BasicActivity.this, HandlerActivity.class);
                break;
            case R.id.btn_data:
                intent = new Intent(BasicActivity.this, DataStorageActivity.class);
                break;
            case R.id.btn_network:
                intent = new Intent(BasicActivity.this, NetWorkActivity.class);
                break;
            case R.id.btn_music:
                intent = new Intent(this, MusicListActivity.class);
                break;
            case R.id.btn_notify:
                intent = new Intent(this, SMSNoticeActivity.class);
                break;

            case R.id.btn_image:
                intent = new Intent(this, ImageListActivity.class);
                break;

            case R.id.btn_baidumap:
                intent = new Intent(this, BaiduMapListActivity.class);
                break;

            case R.id.btn_aysnTask:
                intent = new Intent(this, AsynTaskActivity.class);
                break;

        }
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
