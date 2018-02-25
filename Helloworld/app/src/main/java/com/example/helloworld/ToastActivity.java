package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.utils.ToastUtil;

public class ToastActivity extends AppCompatActivity {


    private Button mBtnNormal, mBtn2, mBtn3, mBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        mBtnNormal = findViewById(R.id.btn_normal);
        mBtn2 = findViewById(R.id.btn_2);
        mBtn3 = findViewById(R.id.btn_3);
        mBtn4 = findViewById(R.id.btn_4);

        OnClickListener onclick = new OnClickListener();
        mBtnNormal.setOnClickListener(onclick);
        mBtn2.setOnClickListener(onclick);
        mBtn3.setOnClickListener(onclick);
        mBtn4.setOnClickListener(onclick);

    }


    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_normal:
                    Toast.makeText(getApplicationContext(), "默认", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_2:
                    Toast toastCenter = Toast.makeText(getApplicationContext(), "居中显示", Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER, 0, 0);
                    toastCenter.show();
                    break;
                case R.id.btn_3:
                    Toast toastCustom = new Toast(getApplicationContext());
                    LayoutInflater layoutInflater = LayoutInflater.from(ToastActivity.this);
                    View inflate = layoutInflater.inflate(R.layout.layout_toast, null);
                    ImageView imageView = inflate.findViewById(R.id.toast_iv);
                    TextView textView = inflate.findViewById(R.id.toast_title);
                    imageView.setImageResource(R.drawable.icon_username);
                    textView.setText("企鹅");
                    toastCustom.setView(inflate);
                    toastCustom.setGravity(Gravity.CENTER, 0, 0);
                    toastCustom.show();
                    break;
                case R.id.btn_4:
                    ToastUtil.showMsg(getApplicationContext(),"包装Toast");
                    break;
            }
        }
    }
}
