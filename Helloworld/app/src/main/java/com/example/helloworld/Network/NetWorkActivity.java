package com.example.helloworld.Network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.Parse.XmlParseActivity;
import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetWorkActivity extends AppCompatActivity {

    @BindView(R.id.btn_loadImg)
    Button mBtnLoadImg;
    @BindView(R.id.btn_parseNews)
    Button mBtnParseNews;
    @BindView(R.id.btn_parse)
    Button mBtnParse;
    @BindView(R.id.btn_download)
    Button mBtnDownload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_loadImg, R.id.btn_parseNews, R.id.btn_parse,R.id.btn_download})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_loadImg:
                intent = new Intent(NetWorkActivity.this, ImageLookerActivity.class);
                break;
            case R.id.btn_parseNews:
                intent = new Intent(NetWorkActivity.this, NewsListActivity.class);
                break;
            case R.id.btn_parse:
                intent = new Intent(NetWorkActivity.this, XmlParseActivity.class);
                break;
            case R.id.btn_download:
                intent = new Intent(NetWorkActivity.this, MutilThreadDownLoadActivity.class);
                break;
        }
        startActivity(intent);
    }


}
