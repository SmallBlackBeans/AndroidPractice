package com.example.helloworld.Basic.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.helloworld.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 */
public class ImageLookerActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.btn_last)
    Button mBtnLast;
    @BindView(R.id.btn_next)
    Button mBtnNext;

    private ArrayList<String> mImagePaths = new ArrayList<>();
    private int mCurrentImageIndex;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("hanxiaocu", "handleMessage: " + Thread.currentThread().getName());
            mIv.setImageBitmap((Bitmap) msg.obj);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_looker);
        ButterKnife.bind(this);
        new Thread() {//开个子线程
            @Override
            public void run() {
                super.run();
                loadImageUrls();
            }
        }.start();
    }


    private void getCurrentImage(int currentIndex) {
        new Thread() {
            @Override
            public void run() {
                try {
                    //创建Url
                    String imageUrl = mImagePaths.get(mCurrentImageIndex);
                    //如果已经缓存过
                    File file = new File(getFilesDir(), getImageName(imageUrl));

                    if (file.exists() && file.length() > 0) {
                        final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        Message msg = new Message();
                        msg.obj = bitmap;
                        mHandler.sendMessage(msg);
                        return;
                    }
                    URL url = new URL(imageUrl);
                    //打开一个链接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = connection.getInputStream();

                        //
                        final Bitmap bitmap = BitmapFactory.decodeStream(is);
                        //写入缓存
                        FileOutputStream os = openFileOutput(getImageName(imageUrl), MODE_PRIVATE);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mIv.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 加载所有的图片的Url
     */
    private void loadImageUrls() {
        try {
            //创建Url
            URL url = new URL(getImageUrls());
            //打开一个链接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //拿到输入流
                InputStream is = connection.getInputStream();
                //读取字符串 使用字符流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    //读取数据
                    mImagePaths.add(line);
                }
                for (int i = 0; i < mImagePaths.size(); i++) {
                    Log.d("============", "loadImageUrls: " + mImagePaths.get(i));
                }

                getCurrentImage(mCurrentImageIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getImageName(String imageUrl) {
        int indexOf = imageUrl.lastIndexOf("/");
        return imageUrl.substring(indexOf + 1);
    }

    @OnClick({R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_last: {
                mCurrentImageIndex--;
                if (mCurrentImageIndex < 0) {
                    mCurrentImageIndex = mImagePaths.size() - 1;
                }
                getCurrentImage(mCurrentImageIndex);
            }
            break;
            case R.id.btn_next: {
                mCurrentImageIndex++;
                if (mCurrentImageIndex > mImagePaths.size() - 1) {
                    mCurrentImageIndex = 0;
                }
                getCurrentImage(mCurrentImageIndex);
            }
            break;
        }
    }

    private String getImageUrls() {
        return "http://192.168.0.58:8090/AndroidDemo/img/photo.txt";
    }
}
