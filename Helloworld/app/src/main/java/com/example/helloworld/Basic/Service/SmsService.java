package com.example.helloworld.Basic.Service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author hanchenghai
 * @time 2018/5/21 上午10:00
 * @desc
 */

public class SmsService extends Service {

    private InnerSmsBroadReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //1 添加一个广播接收者
        receiver = new InnerSmsBroadReceiver();
        //2 初始化广播的事件过滤器
        IntentFilter filter = new IntentFilter();
        //3 添加拦截事件
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //4 设置优先级
        filter.setPriority(1000);
        // 注册广播接收
        registerReceiver(receiver,filter);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }


    //在服务中声明一个广播 可以控制广播的生命周期
    class InnerSmsBroadReceiver extends BroadcastReceiver {

        /**
         * 接收短信
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }


}
