package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {


    private Button mBtnTextView, mBtnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RadioGroup sexGroup = (RadioGroup)findViewById(R.id.sex_rg);
//        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
////
//            public void onCheckedChanged(RadioGroup radioGroup, int chedkId) {
//                if(chedkId == R.id.sex_man) {
//                    Toast.makeText(MainActivity.this, "男", 0).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "女", 0).show();
//                }
//            }
//        });

        mBtnTextView = findViewById(R.id.btn_textview);
        mBtnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳转到TextView 演示界面
                Intent intent = new Intent(MainActivity.this, TextViewActivity.class);
                startActivity(intent);

            }
        });

        mBtnButton = findViewById(R.id.btn_button);
        mBtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ButtonActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void  myClick(View v) {
        Toast.makeText(this,"click", Toast.LENGTH_LONG).show();
    }

}

















