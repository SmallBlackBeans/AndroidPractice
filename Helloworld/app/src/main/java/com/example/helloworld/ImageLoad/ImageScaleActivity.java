package com.example.helloworld.ImageLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageScaleActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView mIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);
        ButterKnife.bind(this);
    }

    //缩小
    public void scaleDown(View v) {
//		现在有了一张图片 应该把这张图片变小
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
//		开始去画一张小图片 这张图片是原来图片的一半
//		1.买个画笔
        Paint paint = new Paint();
//		2.买空的画纸 显示图片 知道画纸的大小
//		android.graphics.Bitmap.Config  APLHA_8  ARGB_4444  ARGB_8888    RGB_565
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth() / 2, bitmap.getHeight() / 2, Bitmap.Config.ARGB_8888);
//		3.画板 将画纸放上去
        Canvas canvas = new Canvas(newBmp);
//		4.针对画板作画
//		bitmap需要画的内容
//		matrix  矩阵-->描述线性代数-->处理几何图形
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        canvas.drawBitmap(bitmap, matrix, paint);
//		将画好的画设置到图片控件里面
        mIv.setImageBitmap(newBmp);
    }

    //	放大
    public void scaleUp(View v) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);

//		1.买个画笔
        Paint paint = new Paint();
//		2.买空的画纸 显示图片 知道画纸的大小
//		android.graphics.Bitmap.Config  APLHA_8  ARGB_4444  ARGB_8888    RGB_565
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth() * 2, bitmap.getHeight() * 2, Bitmap.Config.ARGB_8888);
//		3.画板 将画纸放上去
        Canvas canvas = new Canvas(newBmp);
//		4.针对画板作画
//		bitmap需要画的内容
//		matrix  矩阵-->描述线性代数-->处理几何图形
        Matrix matrix = new Matrix();
        matrix.setScale(2f, 2f);
        canvas.drawBitmap(bitmap, matrix, paint);
//		将画好的画设置到图片控件里面
        mIv.setImageBitmap(newBmp);
    }



}
