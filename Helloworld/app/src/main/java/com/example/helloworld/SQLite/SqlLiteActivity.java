package com.example.helloworld.SQLite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SqlLiteActivity extends AppCompatActivity {

    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.btn_update)
    Button mBtnUpdate;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_select)
    Button mBtnSelect;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    private ContactDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);
        ButterKnife.bind(this);

//        //文件的创建
//        File file = new File("xxx.text");
//        try {
//            //下面两种都会自动创建文件
//            file.createNewFile();
//            FileOutputStream fos = new FileOutputStream(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        DbOpenHelper helper = new DbOpenHelper(this);
        //创建数据库文件
        SQLiteDatabase db = helper.getWritableDatabase();

        mDAO = new ContactDAO(this);
    }

    @OnClick({R.id.btn_add, R.id.btn_update, R.id.btn_delete, R.id.btn_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            
            case R.id.btn_add:
                mDAO.insertContact("hanxiaocu","1234552324");
                break;
            case R.id.btn_update:
                mDAO.updateContact("hanxiaocu","1111111");
                break;
            case R.id.btn_delete:
                mDAO.deleteContact("hanxiaocu");
                break;
            case R.id.btn_select:
                mDAO.queryContact("1111111");
                break;
        }
    }
}
