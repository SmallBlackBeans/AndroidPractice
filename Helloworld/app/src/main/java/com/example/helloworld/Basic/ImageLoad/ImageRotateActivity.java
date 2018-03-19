package com.example.helloworld.Basic.ImageLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageRotateActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_rotate);
        ButterKnife.bind(this);
    }


    private float degrees;

    //顺时针
    public void clockwise(View view) {
        degrees += 45;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
        Paint paint = new Paint();
        //新的画纸
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //把画纸放在画板上
        Canvas canvas = new Canvas(newBmp);
        canvas.drawColor(Color.BLACK);//画板的背景
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, paint);
        //将画纸上的东西渲染到图片上
        mIv.setImageBitmap(newBmp);

    }

    //逆时针
    public void unclockwise(View view) {
        degrees -= 45;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
        Paint paint = new Paint();
        //新的画纸
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //把画纸放在画板上
        Canvas canvas = new Canvas(newBmp);
        canvas.drawColor(Color.BLACK);//画板的背景
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, paint);
        //将画纸上的东西渲染到图片上
        mIv.setImageBitmap(newBmp);
    }
}
