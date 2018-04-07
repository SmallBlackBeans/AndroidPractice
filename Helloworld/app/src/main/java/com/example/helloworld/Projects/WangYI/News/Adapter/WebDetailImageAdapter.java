package com.example.helloworld.Projects.WangYI.News.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.Projects.WangYI.News.Bean.DetailWebImage;
import com.example.helloworld.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/4/6.
 */

public class WebDetailImageAdapter extends PagerAdapter {


    ArrayList<DetailWebImage> mImages;
    ArrayList<View> mViews;
    Context mContext;
    DisplayImageOptions mOptions;

    public WebDetailImageAdapter(ArrayList<DetailWebImage> images, ArrayList<View> views, Context context) {
        this.mContext = context;
        this.mImages = images;
        this.mViews = views;
        mOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
    }

    @Override
    public int getCount() {
        return mImages.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.photo);
        DetailWebImage image = mImages.get(position);
        ImageLoader.getInstance().displayImage(image.getSrc(), photoView, mOptions);
        //一定不要忘了
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
