package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.Projects.WangYI.Ad.View.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectDemoActivity extends AppCompatActivity {


    @BindView(R.id.btn_wangyi)
    Button mBtnWangyi;
    @BindView(R.id.btn_appManager)
    Button mBtnAppManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_demo);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_wangyi, R.id.btn_appManager})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_wangyi:
                intent = new Intent(this, SplashActivity.class);
                break;
            case R.id.btn_appManager:
                intent = new Intent(this, SplashActivity.class);
                break;
        }
        startActivity(intent);
    }
}
