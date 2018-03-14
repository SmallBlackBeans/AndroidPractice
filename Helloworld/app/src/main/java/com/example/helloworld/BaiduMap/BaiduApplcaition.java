package com.example.helloworld.BaiduMap;

import android.app.Application;

/**
 * Created by hanchenghai on 2018/3/14.
 */

public class BaiduApplcaition extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        //SDKInitializer.initialize(getApplicationContext());
    }
}
