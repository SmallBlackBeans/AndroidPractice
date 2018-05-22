package com.example.helloworld.Basic.Service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.helloworld.R;


/**
 * @author hanchenghai
 * @time 2018/5/22 上午9:33
 * @desc
 */

public class RocketService extends Service {
    private WindowManager mWM;
    private int mStartX;
    private int mStartY;
    private ImageView rocketImage;
    private WindowManager.LayoutParams mParams;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mWM.updateViewLayout(rocketImage, mParams);
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        rocketImage = new ImageView(this);
        rocketImage.setBackgroundResource(R.drawable.rocket);

        AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
        rocketAnimation.start();

        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        final WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;

        //指明当前WindowManager 显示的控件是什么
        params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        params.setTitle("Toast");
        ;
        //去掉不可触摸的属性
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.LEFT + Gravity.TOP;//设置默认显示位置为左上角

        mWM.addView(rocketImage, params);

        rocketImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下的坐标
                        mStartX = (int) event.getRawX();
                        mStartY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int) event.getRawX();
                        int newY = (int) event.getRawY();
                        int dx = newX - mStartX;
                        int dy = newY - mStartY;
                        //通知视图移动
                        mWM.updateViewLayout(rocketImage, mParams);
                        params.x += dx;
                        params.y += dy;
                        if (params.x < 0) params.x = 0;
                        if (params.y < 0) params.y = 0;
                        if (params.x > mWM.getDefaultDisplay().getWidth() - v.getWidth()) {
                            params.x = mWM.getDefaultDisplay().getWidth() - v.getWidth();
                        }
                        if (params.y > mWM.getDefaultDisplay().getHeight() - v.getHeight()) {
                            params.y = mWM.getDefaultDisplay().getHeight() - v.getHeight();
                        }

                        //初始化开始坐标
                        mStartX = (int) event.getRawX();
                        mStartY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (params.x > 20 && params.x < 300 && params.y > 400) {
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    for (int i = 0; i < 15; i++) {
                                        SystemClock.sleep(50);
                                        params.y -= i * 5;
                                        mHandler.sendEmptyMessage(0);
                                    }
                                }
                            }.start();
                        }
                        break;
                }
                return true;
            }
        });

    }
}
