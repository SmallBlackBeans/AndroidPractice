package com.example.helloworld.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by hanchenghai on 2018/2/26.
 */

public class EventButton extends android.support.v7.widget.AppCompatButton {


    public EventButton(Context context) {
        super(context);
    }

    public EventButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("EventButton", "----dispatchTouchEvent-----");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("EventButton", "-----onTouchEvent------");
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
