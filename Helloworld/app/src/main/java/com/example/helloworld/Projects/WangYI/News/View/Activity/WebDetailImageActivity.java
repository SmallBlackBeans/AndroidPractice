package com.example.helloworld.Projects.WangYI.News.View.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.Projects.WangYI.News.Adapter.WebDetailImageAdapter;
import com.example.helloworld.Projects.WangYI.News.Bean.DetailWebImage;
import com.example.helloworld.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.Serializable;
import java.util.ArrayList;

public class WebDetailImageActivity extends AppCompatActivity {


    ViewPager viewpager;
    ArrayList<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_detail_image);

        mViews = new ArrayList<>();
        viewpager = findViewById(R.id.viewpager);
        ArrayList<DetailWebImage> images = (ArrayList<DetailWebImage>) getIntent().getSerializableExtra("images");
        if (images != null) {
            for (DetailWebImage image : images) {
                View view = View.inflate(this, R.layout.item_detail_img, null);
                mViews.add(view);
            }
            WebDetailImageAdapter mAdapter = new WebDetailImageAdapter(images,mViews, this);
            viewpager.setAdapter(mAdapter);
        }
    }
}
