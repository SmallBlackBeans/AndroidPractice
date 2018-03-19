package com.example.helloworld.Basic.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.helloworld.Basic.Service.BootService;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, BootService.class);
        context.startActivity(service);
    }
}
