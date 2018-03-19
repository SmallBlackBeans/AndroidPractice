package com.example.helloworld.Basic.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class MyService extends Service {

    @Override
    public void onCreate() {
        //服务创建的时候
        Log.d("hanxiaocu", "onCreate:");

    }

    @Override
    public void onDestroy() {
        Log.v("hanxiaocu","onDestroy");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //服务开启的时候
        return super.onStartCommand(intent, flags, startId);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
