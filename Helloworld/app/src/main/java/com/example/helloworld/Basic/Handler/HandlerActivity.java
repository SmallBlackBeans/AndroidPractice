package com.example.helloworld.Basic.Handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.helloworld.R;
import com.example.helloworld.utils.ToastUtil;


/**
 * 1.切换线程
 * 2.定时
 * 3.循环
 */
public class HandlerActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        //某时做某事
//        mHandler = new Handler();
        //发送消息
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(HandlerActivity.this, ButtonActivity.class);
//                startActivity(intent);
//            }
//        }, 3000);

        //处理消息  在哪创建就和哪个线程绑定，这里是绑定的主线程
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        ToastUtil.showMsg(HandlerActivity.this,"线程通信成功");
                        break;
                }
            }
        };


        new Thread() {
            @Override
            public void run() {
                super.run();
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        }.start();



        new Thread() {
            @Override
            public void run() {
                super.run();
                //子线程创建Handler
                Looper.prepare();
                Handler handler = new Handler();
                Looper.loop();
            }
        }.start();



        HandlerThread thread = new HandlerThread("i am a handler");
        thread.start();
        mHandler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        mHandler.postDelayed(runloop,2 * 1000);
    }


    //循环
    Runnable runloop = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(runloop,1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runloop);
    }
}



