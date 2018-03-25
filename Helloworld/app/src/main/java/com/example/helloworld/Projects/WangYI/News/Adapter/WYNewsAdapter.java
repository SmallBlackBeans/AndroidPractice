package com.example.helloworld.Projects.WangYI.News.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.helloworld.Projects.WangYI.News.Bean.FragmentInfo;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/3/25.
 */

public class WYNewsAdapter extends FragmentStatePagerAdapter {

    ArrayList<FragmentInfo> mFragments;

    public WYNewsAdapter(FragmentManager fm, ArrayList<FragmentInfo> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
