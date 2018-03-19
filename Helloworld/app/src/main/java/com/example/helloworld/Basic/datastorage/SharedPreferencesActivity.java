package com.example.helloworld.Basic.datastorage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.R;
/**
 * 轻量级存储 - 内部存储
 * 默认存储 /data/data/<applicationId>/shared_prefs
 */
public class SharedPreferencesActivity extends AppCompatActivity {
    private Button mBtnSave, mBtnShow;
    private TextView mTvShow;
    private EditText mEtContent;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SharedPreferences");
        setContentView(R.layout.activity_file_save);


        mBtnSave = findViewById(R.id.btn_save);
        mBtnShow = findViewById(R.id.btn_read);
        mTvShow = findViewById(R.id.tv_show);
        mEtContent = findViewById(R.id.et_content);

        mSharedPreferences = this.getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditor.putString("name", mEtContent.getText().toString());
                mEditor.apply();
            }
        });

        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvShow.setText(mSharedPreferences.getString("name","缺省值"));
            }
        });


    }

}
