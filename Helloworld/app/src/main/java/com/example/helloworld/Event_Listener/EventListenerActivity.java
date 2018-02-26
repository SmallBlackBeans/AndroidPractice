package com.example.helloworld.Event_Listener;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.utils.ToastUtil;
import com.example.helloworld.widget.EventButton;

import java.util.logging.Logger;

/*
* 事件源
* 事件
* 事件监听者
*/
public class EventListenerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnEvent, mBtnTouch;

    private EventButton mBtnMyEvent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_listener);

        mBtnEvent = findViewById(R.id.btn_event);
        mBtnTouch = findViewById(R.id.btn_touch);
        //内部类
        mBtnEvent.setOnClickListener(new OnClickListener());

        //匿名内部类
        mBtnTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        //通过事件源所在的类实现
        mBtnEvent.setOnClickListener(EventListenerActivity.this);
        //外部类

        //布局设置:click




        mBtnMyEvent = findViewById(R.id.btn_custom);
        mBtnMyEvent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("EventButton", "----dispatchTouchEvent-----");
                return false;
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("Activity", "-----onTouchEvent------");
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_event:
                ToastUtil.showMsg(EventListenerActivity.this, "Click...");
                break;
        }
    }


    //内部类实现
    class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_event:
                    ToastUtil.showMsg(EventListenerActivity.this, "Click...");
                    break;
            }
        }
    }

}




