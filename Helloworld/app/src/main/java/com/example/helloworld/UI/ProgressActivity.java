package com.example.helloworld.UI;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.helloworld.R;
import com.example.helloworld.Utils.ToastUtil;

public class ProgressActivity extends AppCompatActivity {

    private ProgressBar mPb3;

    private Button mBtn;

    private Button mBtnPgDialog1, mBtnPgDialog2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mPb3 = findViewById(R.id.progress3);
        mPb3.setProgress(40);

        mBtn = findViewById(R.id.btn_mock);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.sendEmptyMessage(0);
            }
        });


        mBtnPgDialog1 = findViewById(R.id.btn_progress_dialog1);
        mBtnPgDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(ProgressActivity.this);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("加载中");
                progressDialog.show();
            }
        });
        mBtnPgDialog2 = findViewById(R.id.btn_progress_dialog2);
        mBtnPgDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(ProgressActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("正在下载...");
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "棒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        
                    }
                });
                progressDialog.show();
            }
        });


    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            int progress = mPb3.getProgress();
            if (progress < 100) {
                mHandler.postDelayed(mRunnable, 500);
            }else  {
                ToastUtil.showMsg(ProgressActivity.this,"加载完成");
            }
            return false;
        }
    });

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mPb3.setProgress(mPb3.getProgress() + 5);
            mHandler.sendEmptyMessage(0);
        }
    };
}
