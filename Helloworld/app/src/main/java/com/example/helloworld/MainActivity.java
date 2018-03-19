package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.idescout.sql.SqlScoutServer;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_ui)
    Button mBtnUi;
    @BindView(R.id.btn_basic)
    Button mBtnBasic;
    @BindView(R.id.btn_project)
    Button mBtnProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick({R.id.btn_ui, R.id.btn_basic, R.id.btn_project})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_ui:
                intent = new Intent(MainActivity.this, UIActivity.class);
                break;
            case R.id.btn_basic:
                intent = new Intent(MainActivity.this, BasicActivity.class);
                break;
            case R.id.btn_project:
                intent = new Intent(MainActivity.this, ProjectDemoActivity.class);
                break;
        }
        startActivity(intent);
    }
}



















