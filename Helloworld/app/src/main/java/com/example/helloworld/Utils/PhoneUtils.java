package com.example.helloworld.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * @author hanchenghai
 * @time 2018/5/17 下午6:13
 * @desc 手机信息获取
 */

public class PhoneUtils {

    @SuppressLint("MissingPermission")
    static public String getsimSerialNumber(Activity activity){
        TelephonyManager manager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getSimSerialNumber();
    }
}
