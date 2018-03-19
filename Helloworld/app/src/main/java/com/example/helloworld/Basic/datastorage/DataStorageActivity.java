package com.example.helloworld.Basic.datastorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.Basic.SQLite.SqlLiteActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_sqlite)
    Button mBtnSqlite;
    private Button mBtnSharedPreferences, mBtnfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("DataStorage");
        setContentView(R.layout.activity_data_storage);
        ButterKnife.bind(this);
        mBtnSharedPreferences = findViewById(R.id.btn_sharedpreferences);
        mBtnSharedPreferences.setOnClickListener(DataStorageActivity.this);


        mBtnfile = findViewById(R.id.btn_file);
        mBtnfile.setOnClickListener(DataStorageActivity.this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_sharedpreferences:
                intent = new Intent(this, SharedPreferencesActivity.class);
                break;
            case R.id.btn_file:
                intent = new Intent(this, FileSaveActivity.class);
                break;
        }

        startActivity(intent);
    }

    @OnClick(R.id.btn_sqlite)
    public void onViewClicked() {
        Intent intent = new Intent(this, SqlLiteActivity.class);
        startActivity(intent);
    }
}
