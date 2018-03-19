package com.example.helloworld.Basic.Network;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.widget.ImageWrapper;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/3/5.
 */

public class NewsAdapter extends BaseAdapter {


    private ArrayList<NewsBean> mNewsBeans;

    @Override
    public int getCount() {
        return mNewsBeans != null ? mNewsBeans.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        public ImageWrapper mImageView;
        public TextView mTitle;
        public TextView mDes;
        public TextView mType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.layout_news_item, null);

            holder = new ViewHolder();
            holder.mImageView = convertView.findViewById(R.id.news_iv);
            holder.mTitle = convertView.findViewById(R.id.news_title);
            holder.mDes = convertView.findViewById(R.id.news_des);
            holder.mType = convertView.findViewById(R.id.news_type);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsBean bean = mNewsBeans.get(position);
        holder.mImageView.setImageWithUrl(bean.getImage());
        holder.mTitle.setText(bean.getTitle());
        holder.mDes.setText(bean.getDescription());
        initTypeText(holder, bean);

        return convertView;
    }

    private void initTypeText(ViewHolder holder, NewsBean bean) {
        if (bean.getType() == 1) {
            holder.mType.setText("评论" + bean.getType());
        } else if (bean.getType() == 2) {
            holder.mType.setText("专题" + bean.getType());
        } else if (bean.getType() == 3) {
            holder.mType.setText("科技" + bean.getType());
        }
    }

    public void setDatas(ArrayList<NewsBean> newsBeans) {

        mNewsBeans = newsBeans;
    }
}
