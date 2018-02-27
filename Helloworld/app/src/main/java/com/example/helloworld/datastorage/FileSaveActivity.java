package com.example.helloworld.datastorage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * 文件存储
 */
public class FileSaveActivity extends AppCompatActivity {
    private Button mBtnSave, mBtnShow;
    private TextView mTvShow;
    private EditText mEtContent;

    private final String mFileName = "test.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("File Save");
        setContentView(R.layout.activity_shared_preferences);


        mBtnSave = findViewById(R.id.btn_save);
        mBtnShow = findViewById(R.id.btn_read);
        mTvShow = findViewById(R.id.tv_show);
        mEtContent = findViewById(R.id.et_content);


        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(mEtContent.getText().toString().trim());
            }
        });

        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvShow.setText(read());
            }
        });


    }

    //存储数据
    private void save(String content) {
        FileOutputStream fileOutputStream = null;
        try {
            //1内部存储
            {
                // fileOutputStream = openFileOutput(mFileName, MODE_PRIVATE);
            }

            //2外部存储
            {

                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {//安装了SD卡

                }

                File dir = new File(Environment.getExternalStorageDirectory(), "hanxiaocu");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File file = new File(dir, mFileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
            }

            fileOutputStream.write(content.getBytes());


            {//3
                File dir = new File(Environment.getExternalStorageDirectory(), "hanxiaocu");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File file = new File(dir, mFileName);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                writer.write(content);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取数据
    private String read() {
        FileInputStream fileInputStream = null;
        try {
            //内部存储
            {
                fileInputStream = openFileInput(mFileName);
            }

            //外部存储
            {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hanxiaocu", mFileName);
                fileInputStream = new FileInputStream(file);
            }

            byte[] buff = new byte[1024];
            StringBuffer sb = new StringBuffer("");
            int len = 0;
            while ((len = fileInputStream.read(buff)) > 0) {
                sb.append(new String(buff, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
