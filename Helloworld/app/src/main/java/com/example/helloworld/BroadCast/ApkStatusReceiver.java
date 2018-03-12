package com.example.helloworld.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class ApkStatusReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            Log.v("hanxiaocu","ACTION_PACKAGE_ADDED");
        }else if(action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            Log.v("hanxiaocu","ACTION_PACKAGE_REMOVED");
        }

    }
}
