package com.example.helloworld.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class SDCardReceiver extends BroadcastReceiver {
    private static final String TAG = "TAG";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
            //SD卡安装事件
            Log.d(TAG, "onReceive: SD卡安装事件");
        } else if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
            //SD卡卸载事件
            Log.d(TAG, "onReceive: SD卡卸载事件");
        }

    }
}
