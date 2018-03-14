package com.example.helloworld.Application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hanchenghai on 2018/3/14.
 */

public class DemoApplcaition extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //在使用百度地图SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        //内存检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
