package com.example.helloworld;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        RadioGroup sexGroup = findViewById(R.id.rg_2);
        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int chedkId) {
                if (chedkId == R.id.man_2) {
                    Toast.makeText(RadioButtonActivity.this, "男", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RadioButtonActivity.this, "女", 0).show();
                }
            }
        });

    }
}
