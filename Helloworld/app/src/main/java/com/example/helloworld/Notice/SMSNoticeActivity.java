package com.example.helloworld.Notice;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.R;

public class SMSNoticeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsnotice);
    }


    //过期移除
    public void showNotificationlow(View view) {
        //获取系统服务
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification
                = new Notification(R.drawable.icon_username, "音乐播放器：月亮之上", System.currentTimeMillis());
        Intent intent = new Intent(this, SMSNoticeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        notification.setLatestEventInfo(this,"音乐播放器",
//                "xxx", pendingIntent
//        );
        manager.notify();
    }


    public void showNotificationhigh(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("正在播放..")
                .setContentText("小坏蛋")
                .setSmallIcon(R.drawable.icon_username)
                .build();
        manager.notify(0,noti);

    }

    public void cancelNotification(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancelAll();
    }
}
