package com.example.helloworld.datastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSharedPreferences, mBtnfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("DataStorage");
        setContentView(R.layout.activity_data_storage);
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
}
