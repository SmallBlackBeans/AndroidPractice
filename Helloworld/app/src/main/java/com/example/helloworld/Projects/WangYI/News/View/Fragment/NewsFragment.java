package com.example.helloworld.Projects.WangYI.News.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.helloworld.Basic.Network.NewsAdapter;
import com.example.helloworld.Projects.WangYI.News.Adapter.WYNewsAdapter;
import com.example.helloworld.Projects.WangYI.News.Bean.FragmentInfo;
import com.example.helloworld.Projects.WangYI.News.View.Fragment.News_Inner.HotFragment;
import com.example.helloworld.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/3/22.
 */

public class NewsFragment extends Fragment {


    ArrayList<FragmentInfo> pages;
    WYNewsAdapter mNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);//attachToRoot 是否自动添加到父容器
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pages = new ArrayList<>();

        FrameLayout frameLayout = getActivity().findViewById(R.id.inner_tabs);
        frameLayout.addView(View.inflate(getActivity(), R.layout.fragment_inner_tab, null));


        SmartTabLayout smartTabLayout = getActivity().findViewById(R.id.news_viewpager_tab);
        ViewPager viewPager = getActivity().findViewById(R.id.viewPaper);



        String[] titles = getResources().getStringArray(R.array.news_titles);
        for (int i = 0; i < titles.length; i++) {
            FragmentInfo info;
            if (i == 0) {
                info = new FragmentInfo(new HotFragment(), titles[i]);
            } else {
                info = new FragmentInfo(new EmptyFragment(), titles[i]);
            }
            pages.add(info);
        }

        mNewsAdapter = new WYNewsAdapter(getFragmentManager(),pages);
        viewPager.setAdapter(mNewsAdapter);
        smartTabLayout.setViewPager(viewPager);
    }
}
