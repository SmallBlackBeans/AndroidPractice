package com.example.helloworld.Projects.WangYI.News.Bean;

import android.support.v4.app.Fragment;

/**
 * Created by hanchenghai on 2018/3/25.
 */

public class FragmentInfo {
    Fragment mFragment;
    String title;


    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FragmentInfo(Fragment fragment, String title) {
        mFragment = fragment;
        this.title = title;
    }

    @Override
    public String toString() {
        return "FragmentInfo{" +
                "mFragment=" + mFragment +
                ", title='" + title + '\'' +
                '}';
    }
}
