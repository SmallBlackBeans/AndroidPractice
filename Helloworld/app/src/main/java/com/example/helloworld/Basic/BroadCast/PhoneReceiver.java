package com.example.helloworld.Basic.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class PhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("hanxiaocu","电话外部le");
    }
}
