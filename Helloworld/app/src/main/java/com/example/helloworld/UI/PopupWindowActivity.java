package com.example.helloworld.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.helloworld.R;

public class PopupWindowActivity extends AppCompatActivity {


    private Button mBtnPop;
    private PopupWindow mPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mBtnPop = findViewById(R.id.btn_pop);
        mBtnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View contentView = getLayoutInflater().inflate(R.layout.layout_pop,null);
                mPop = new PopupWindow(contentView,view.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                mPop.setOutsideTouchable(true);
                mPop.setFocusable(true);//避免重复弹出
                mPop.setAnimationStyle(1);
                mPop.showAsDropDown(mBtnPop);
            }
        });
    }


    @Override
    protected void onDestroy() {
        if (mPop != null) {
            mPop.dismiss();
            mPop = null;
        }
        super.onDestroy();
    }
}
