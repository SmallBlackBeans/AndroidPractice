package com.example.helloworld.Basic.MemoryLeak;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.helloworld.R;

import java.lang.ref.WeakReference;

public class HandlerLeakActivity extends AppCompatActivity {


    private static final int MESSAGE_SEND = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_leak);
    }


    private void click(View view) {
        startSendMessage();
    }

    private void startSendMessage() {
        MyHandler myHandler = new MyHandler(HandlerLeakActivity.this);
        //避免重复的创建message 内存优化体现
        Message message = Message.obtain();
        message.what = MESSAGE_SEND;
        message.obj = "这是来自韩小醋的消息";
        //延时发送
        myHandler.sendMessageDelayed(message,20000);
    }

    private static class MyHandler extends Handler {


        private WeakReference<HandlerLeakActivity> mWeakReference = null;

        MyHandler(HandlerLeakActivity activity) {
            mWeakReference = new WeakReference<HandlerLeakActivity>(activity);
        }

        //处理发送的消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerLeakActivity activity = mWeakReference.get();
            if (activity == null)return;
            switch (msg.what) {
                case MESSAGE_SEND:
                    String obj = (String) msg.obj;
                    Toast.makeText(activity,obj,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
