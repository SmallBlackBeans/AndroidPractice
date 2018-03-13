package com.example.helloworld.ImageLoad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageListActivity extends AppCompatActivity {

    @BindView(R.id.btn_bigImage)
    Button mBtnBigImage;
    @BindView(R.id.btn_scaleImage)
    Button mBtnScaleImage;
    @BindView(R.id.btn_translate)
    Button mBtnTranslate;
    @BindView(R.id.btn_rotate)
    Button mBtnRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_bigImage, R.id.btn_scaleImage,R.id.btn_translate, R.id.btn_rotate,R.id.btn_reflection})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_bigImage:
                intent = new Intent(this, BigImageLoadActivity.class);
                break;
            case R.id.btn_scaleImage:
                intent = new Intent(this, ImageScaleActivity.class);
                break;
            case R.id.btn_translate:
                intent = new Intent(this, ImageTranslateActivity.class);
                break;
            case R.id.btn_rotate:
                intent = new Intent(this, ImageRotateActivity.class);
                break;
            case R.id.btn_reflection:
                intent = new Intent(this, ImageReflectionActivity.class);
                break;
        }
        startActivity(intent);
    }


}
