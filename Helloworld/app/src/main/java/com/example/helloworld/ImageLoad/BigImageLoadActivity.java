package com.example.helloworld.ImageLoad;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BigImageLoadActivity extends AppCompatActivity {

    @BindView(R.id.iv_bigImage)
    ImageView mIvBigImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image_load);
        ButterKnife.bind(this);
        //https://www.jianshu.com/p/b4a8b3d4f587

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //动态获取存储权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            //动态获取读取权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }


    }

    public void loadImage(View view) {
//    	1.知道屏幕的大小   WindowManager窗口管理器   获取屏幕的大小
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//    	display 获取当前的显示
        Display display = manager.getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        int screenW =display.getWidth();
        int screenH = display.getHeight();


//    	2.知道图片的大小  BitmapFactory.decodeFile(pathName)还是要去加载图片  现在只想知道图片的大小
        String pathName = getFilesDir().getAbsolutePath() + "/largeimage.jpg";
        BitmapFactory.Options opts = new BitmapFactory.Options();
//    	告诉系统 我们只想获取图片的头信息  而不加载整张图片
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
        int width = opts.outWidth;
        int heigth = opts.outHeight;


//    	3.进行等比例缩放
//    	计算比例
        float scaleW = width * 1.0f / screenW;
        float scaleH = heigth * 1.0f / screenH;
        float resultScale = 0;
        if (scaleW > scaleH && scaleW > 1) {
            resultScale = scaleW;
        }
        if (scaleH > scaleW && scaleH > 1) {
            resultScale = scaleH;
        }

        //4.显示图片
        opts.inJustDecodeBounds = false;
        // 设置缩放的值
        opts.inSampleSize = (int) resultScale;
        Bitmap newBmp = BitmapFactory.decodeFile(pathName, opts);
        mIvBigImage.setImageBitmap(newBmp);
    }
}
