package com.example.helloworld.Music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/3/13.
 */

public class MusicListAdapter extends BaseAdapter {
    private ArrayList<String> mDatas;

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
            tv = convertView.findViewById(android.R.id.text1);
            convertView.setTag(tv);
        }else  {
            tv = (TextView) convertView.getTag();
        }
        tv.setText(getMusicName(mDatas.get(position)));
        return convertView;
    }

    public void setDatas(ArrayList<String> musicFilePath) {
        mDatas = musicFilePath;
    }



    private String getMusicName(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }



    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
