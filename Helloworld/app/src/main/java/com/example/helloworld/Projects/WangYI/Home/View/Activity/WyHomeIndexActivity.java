package com.example.helloworld.Projects.WangYI.Home.View.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TabHost;

import com.example.helloworld.Projects.WangYI.Home.View.Fragment.MineFragment;
import com.example.helloworld.Projects.WangYI.Home.View.Fragment.NewsFragment;
import com.example.helloworld.Projects.WangYI.Home.View.Fragment.ReadingFragment;
import com.example.helloworld.Projects.WangYI.Home.View.Fragment.TopicFragment;
import com.example.helloworld.Projects.WangYI.Home.View.Fragment.VedioFragment;
import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WyHomeIndexActivity extends AppCompatActivity {

    @BindView(R.id.tab_host)
    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wy_home_index);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        //初始化
        mTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        TabHost.TabSpec one = mTabHost.newTabSpec("1");//标识符
        one.setIndicator("one");//内容
        mTabHost.addTab(one,NewsFragment.class,null);

        TabHost.TabSpec two = mTabHost.newTabSpec("2");
        one.setIndicator("two");
        mTabHost.addTab(two,ReadingFragment.class,null);

        TabHost.TabSpec three = mTabHost.newTabSpec("3");
        one.setIndicator("three");
        mTabHost.addTab(three,VedioFragment.class,null);

        TabHost.TabSpec four = mTabHost.newTabSpec("4");
        one.setIndicator("four");
        mTabHost.addTab(three,TopicFragment.class,null);

        TabHost.TabSpec five = mTabHost.newTabSpec("5");
        one.setIndicator("five");
        mTabHost.addTab(three,MineFragment.class,null);




        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("hanxiaocu","tag = " + tabId);
            }
        });

    }

}
