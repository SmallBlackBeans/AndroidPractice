package com.example.helloworld.Projects.WangYI.News.View.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.helloworld.Projects.WangYI.News.View.Fragment.MineFragment;
import com.example.helloworld.Projects.WangYI.News.View.Fragment.NewsFragment;
import com.example.helloworld.Projects.WangYI.News.View.Fragment.ReadingFragment;
import com.example.helloworld.Projects.WangYI.News.View.Fragment.TopicFragment;
import com.example.helloworld.Projects.WangYI.News.View.Fragment.VedioFragment;
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

        //初始化 绑定某个fragment容器
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);

        String[] titles = getResources().getStringArray(R.array.tab_titles);

        int[] icons = new int[]{
                R.drawable.tab_news_selector,
                R.drawable.tab_reading_selector,
                R.drawable.tab_video_selector,
                R.drawable.tab_topic_selector,
                R.drawable.tab_mine_selector
        };

        Class[] classes = new Class[]{
                NewsFragment.class,
                ReadingFragment.class,
                VedioFragment.class,
                TopicFragment.class,
                MineFragment.class
        };

        for (int i = 0; i < titles.length; i++) {
            TabHost.TabSpec tmp = mTabHost.newTabSpec("" + i);//标识符
            //tmp.setIndicator("one");//内容
            tmp.setIndicator(getTabBarItemView(this,titles[i],icons[i]));
            mTabHost.addTab(tmp, classes[i], null);
        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("hanxiaocu", "tag = " + tabId);
            }
        });
        //设置默认选中
        //mTabHost.setCurrentTabByTag("1");

    }

    private View getTabBarItemView(Context context, String title, int icon) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tabbar_item, null);
        ImageView tab_icon = view.findViewById(R.id.tab_item_image);
        TextView tab_title = view.findViewById(R.id.tab_item_title);
        tab_title.setText(title);
        tab_icon.setImageResource(icon);
        return view;
    }

}
