package com.example.helloworld.Basic.BroadCast;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.example.helloworld.Utils.SPUtils;

/**
 * @author hanchenghai
 * @time 2018/5/17 下午6:18
 * @desc 监听手机重启的广播 只要手机重启，这个广播就会被激活
 */

public class BootcompleteReceiver extends BroadcastReceiver {
    private static String SIM_NUM = "simnum";

    @Override
    public void onReceive(Context context, Intent intent) {
        //检测sim卡序列号是否变化了
        String oldSim = SPUtils.getString(context, SIM_NUM);
        @SuppressLint("ServiceCast") TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELECOM_SERVICE);
        @SuppressLint("MissingPermission") String newSim = manager.getSimSerialNumber() + "";
        if (oldSim.equals(newSim)) {

        } else {

        }
        //如果变化给安全号码发送一条短信
    }
}
