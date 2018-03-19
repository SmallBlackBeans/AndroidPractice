package com.example.helloworld.Basic.Network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.helloworld.R;
import com.example.helloworld.utils.NetworkUtil;
import com.example.helloworld.utils.XmlUtil;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView mLv;

    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        mAdapter = new NewsAdapter();
        mLv.setAdapter(mAdapter);

        new Thread() {
            @Override
            public void run() {
                super.run();
                getNetWorlResource();
            }
        }.start();
    }

    private void getNetWorlResource() {
        try {
            String urlPath = "http://192.168.0.34:8090/AndroidDemo/news.xml";
            InputStream is = NetworkUtil.getResource(urlPath);
            if (is != null) {
                ArrayList<NewsBean> newsBeans = XmlUtil.parseNews(is);
                mAdapter.setDatas(newsBeans);
                //mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
