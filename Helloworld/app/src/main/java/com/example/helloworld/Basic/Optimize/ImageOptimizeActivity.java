package com.example.helloworld.Basic.Optimize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 1.注意特殊对象的释放
 * 2.对象池的使用 Message.Obtian()
 * 3.缓存 lru算法 最近最少使用
 * 4.UI过渡绘制 时间 层级
 * 5.复用styles ids
 * 6.include xml文件 merge 合并为父标签
 * 7.viewSub标签 不会占用空间，  只在显示的时候才会加载  延时加载的作用
 *
 *
 *
 *
 *
 *
 *
 *
 * measure 计算 layout 布局 draw 绘制
 *
 *
 *
 *
 */


public class ImageOptimizeActivity extends AppCompatActivity {


    @BindView(R.id.iv)
    ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_optimize);
        ButterKnife.bind(this);

        Bitmap bitmap = getScaledBitmap(540, 540);
        mIv.setImageBitmap(bitmap);
        bitmap.recycle();




    }

    private Bitmap  getScaledBitmap(int targetW, int targetH) {

        String path = Environment.getExternalStorageDirectory() + "/xxx.jpg";


        BitmapFactory.Options options = new BitmapFactory.Options();


        //边界压缩 只是读取图片的宽高
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);


        int iw = options.outWidth;
        int ih = options.outHeight;


        int heightScale = ih / targetH;
        int widthScale = iw / targetW;

        int maxScale = Math.max(heightScale, widthScale);
        options.inSampleSize = maxScale;

        options.inJustDecodeBounds = false;
        //首选色彩配置 数值越大 质量越好，内存占用越高
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }






}
