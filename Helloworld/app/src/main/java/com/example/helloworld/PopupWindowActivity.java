package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopupWindowActivity extends AppCompatActivity {


    private Button mBtnPop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mBtnPop = findViewById(R.id.btn_pop);
        mBtnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View contentView = getLayoutInflater().inflate(R.layout.layout_pop,null);
                PopupWindow pop = new PopupWindow(contentView,view.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                pop.setOutsideTouchable(true);
                pop.setFocusable(true);//避免重复弹出
                pop.setAnimationStyle(1);
                pop.showAsDropDown(mBtnPop);
            }
        });
    }
}
