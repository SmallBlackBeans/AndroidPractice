package com.example.helloworld.Projects.WangYI.News.Adapter;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.Projects.WangYI.News.Bean.Banner;
import com.example.helloworld.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/4/1.
 */

public class BannerApapter extends PagerAdapter {

    ArrayList<View> mViews;
    ArrayList<Banner> mBanners;
    DisplayImageOptions mOptions;

    int size;


    public BannerApapter(ArrayList<View> views, ArrayList<Banner> banners) {
        mViews = views;
        mBanners = banners;
        size = views.size();
        //建造者模式-> 创建一个复杂的对象
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher_foreground)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true).cacheOnDisk(true).build();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int remaind = position % size;
        View view = mViews.get(remaind);
        ImageView imageView = view.findViewById(R.id.image);
        Banner banner = mBanners.get(remaind);
        ImageLoader.getInstance().displayImage(banner.getImgsrc(), imageView, mOptions);




        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
