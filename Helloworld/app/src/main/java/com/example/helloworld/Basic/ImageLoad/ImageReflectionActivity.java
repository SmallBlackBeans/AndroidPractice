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


/**
 * 倒影和镜面
 */
public class ImageReflectionActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_reflection);
        ButterKnife.bind(this);
    }

    //镜面
    public void mirrorClick(View view) {
        //先获取资源
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);


        //准备画笔
        Paint paint = new Paint();

        //准备画纸
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);


        //准备画板 把新的画纸放在上面
        Canvas canvas = new Canvas(newBmp);
        canvas.drawColor(Color.BLACK);

        //矩阵变换类
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        matrix.postTranslate(bitmap.getWidth(), 0);

        canvas.drawBitmap(bitmap, matrix, paint);

        mIv.setImageBitmap(newBmp);
    }

    //倒影
    public void reflection(View view) {
        //先获取资源
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);


        //准备画笔
        Paint paint = new Paint();

        //准备画纸
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);


        //准备画板 把新的画纸放在上面
        Canvas canvas = new Canvas(newBmp);
        canvas.drawColor(Color.BLACK);

        //矩阵变换类
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        matrix.postTranslate(0, bitmap.getHeight());

        canvas.drawBitmap(bitmap, matrix, paint);

        mIv.setImageBitmap(newBmp);
    }
}
