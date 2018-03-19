package com.example.helloworld.Basic.Music;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.helloworld.R;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/3/13.
 */

public class MusicService extends Service {

    private MediaPlayer mMediaPlayer;
    private Context mContext;


    //代理
    private class MusicAgent extends Binder implements IMusicInterface {


        @Override
        public void callPlayMusic(ArrayList<String> paths, int position) {
            playMusic(paths, position);
        }

        @Override
        public void callStopMusic() {
            stopMusic();
        }

        @Override
        public void callPauseMusic() {

        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicAgent();
    }


    /**
     * 停止音乐
     */
    private void stopMusic() {
        cancelNotify();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


    private void pauseMusic() {
        mMediaPlayer.pause();
    }


    private void showNotify(String name) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mContext = getApplicationContext();
        Notification noti = new Notification.Builder(mContext)
                .setContentTitle("正在播放:" + name)
                .setContentText("小坏蛋...")
                .setSmallIcon(R.drawable.icon_username)
                .build();
        manager.notify(0, noti);
    }

    private void cancelNotify() {

    }

    private int mCurrentPosition;

    private void playMusic(final ArrayList<String> paths, int position) {
        showNotify(getMusicName(paths, position));
        mCurrentPosition = position % paths.size();
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                //音乐播放完成调用
                @Override
                public void onCompletion(MediaPlayer mp) {
                    SharedPreferences sp = getSharedPreferences("musicMode", MODE_PRIVATE);
                    int mode = sp.getInt("mode", 0);
                    switch (mode) {
                        case 0:

                            break;
                        case 1://单曲循环
                            playMusic(paths, mCurrentPosition);
                            break;
                        case 2://全部循环
                            playMusic(paths, (mCurrentPosition + 1) % paths.size());
                            break;
                    }
                }
            });
        }
        try {
            mMediaPlayer.reset();//先释放资源
            mMediaPlayer.setDataSource(paths.get(mCurrentPosition));//设置数据源
            mMediaPlayer.prepare();//准备数据
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private String getMusicName(ArrayList<String> paths, int position) {
        String filePath = paths.get(position);
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }
}
