package com.example.helloworld.ImageLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageTranslateActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_translate);
        ButterKnife.bind(this);
    }


    private float mCurrentX = 0;

    public void moveRight(View view) {
        mCurrentX += 20;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
        Paint paint = new Paint();
        //新的画纸
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //把画纸放在画板上
        Canvas canvas = new Canvas(newBmp);
        canvas.drawColor(Color.BLACK);//画板的背景
        Matrix matrix = new Matrix();
        matrix.setTranslate(mCurrentX, 0);
        canvas.drawBitmap(bitmap, matrix, paint);

        //将画纸上的东西渲染到图片上
        mIv.setImageBitmap(newBmp);
    }

    public void moveLeft(View view) {
        mCurrentX -= 20;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
        Paint paint = new Paint();
        //新的画纸
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //把画纸放在画板上
        Canvas canvas = new Canvas(newBmp);
        canvas.drawColor(Color.BLACK);//画板的背景
        Matrix matrix = new Matrix();
        matrix.setTranslate(mCurrentX, 0);
        canvas.drawBitmap(bitmap, matrix, paint);

        //将画纸上的东西渲染到图片上
        mIv.setImageBitmap(newBmp);
    }

}
