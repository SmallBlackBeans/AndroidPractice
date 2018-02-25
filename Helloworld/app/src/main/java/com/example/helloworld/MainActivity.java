package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.GridView.GridViewActivity;
import com.example.helloworld.ListView.ListViewActivity;
import com.example.helloworld.RecycleView.RecyclerViewActivity;
import com.example.helloworld.WebView.WebViewActivity;

public class MainActivity extends AppCompatActivity {


    private Button mBtnUI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnUI = findViewById(R.id.btn_ui);
        setListeners();
    }

    private void setListeners() {
        OnClick onclick = new OnClick();
        mBtnUI.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_ui:
                    intent = new Intent(MainActivity.this, UIActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}



















