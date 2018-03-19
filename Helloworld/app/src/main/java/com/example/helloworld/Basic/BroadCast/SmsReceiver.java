package com.example.helloworld.Basic.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        for (Object obj: pdus) {
            byte[] buff = (byte[]) obj;
            SmsMessage message = SmsMessage.createFromPdu(buff);
            //获取发信人
            String address = message.getDisplayOriginatingAddress();
            
            //获取短信内容
            String smsContent = message.getDisplayMessageBody();
        }

        //终止继续广播下去 系统就接收不到了
        abortBroadcast();
    }
}
