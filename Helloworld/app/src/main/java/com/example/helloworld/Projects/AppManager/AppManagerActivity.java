package com.example.helloworld.Projects.AppManager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloworld.R;

import java.io.File;


import butterknife.BindView;
import butterknife.ButterKnife;

public class AppManagerActivity extends AppCompatActivity {


    @BindView(R.id.tv_rom)
    TextView mTvRom;
    @BindView(R.id.tv_sd)
    TextView mTvSd;
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    @BindView(R.id.lv_app_infos)
    ListView mLvAppInfos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        ButterKnife.bind(this);


        
        initView();
        initData();

    }



    private void initData() {



    }

    private void initView() {
        File romFile = Environment.getDataDirectory();
        long romFree = romFile.getFreeSpace();
        mTvRom.setText("内存可用："+ Formatter.formatFileSize(this,romFree));

        File sdFile = Environment.getExternalStorageDirectory();
        long sdFree = sdFile.getFreeSpace();
        mTvSd.setText("SD可用："+Formatter.formatFileSize(this,sdFree));
        mLlLoading.setVisibility(View.VISIBLE);
    }



    class AppManagerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

}
