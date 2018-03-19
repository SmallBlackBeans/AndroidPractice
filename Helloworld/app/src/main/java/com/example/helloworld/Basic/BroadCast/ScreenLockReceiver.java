package com.example.helloworld.Basic.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class ScreenLockReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.v("hanxiaocu","锁屏");

        }else if(action.equals(Intent.ACTION_SCREEN_ON)) {}
            Log.v("hanxiaocu","解锁");
    }
}
