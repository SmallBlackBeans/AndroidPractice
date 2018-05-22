package com.example.helloworld.Basic.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by hanchenghai on 2018/3/12.
 * 拦截短信
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
            String body = message.getDisplayMessageBody();
            if ("#*location*#".equals(body)) {
                System.out.println("发送经纬度");
                //屏蔽短信，不让用户看到
                abortBroadcast();
            } else if ("#*alarm*#".equals(body)) {
                MediaPlayer player = MediaPlayer.create(context, null);
                //左声道、右声道
                player.setVolume(1.0f, 1.0f);
                player.setLooping(true);
                player.start();
                abortBroadcast();
            }
        }
        //终止继续广播下去 系统就接收不到了
        abortBroadcast();
    }
}
