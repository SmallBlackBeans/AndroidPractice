package com.example.helloworld.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.helloworld.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by hanchenghai on 2018/3/5.
 */

public class ImageWrapper extends ImageView {

    private static final int ACTION_LOAD_SUCCESS = 0x001;
    private static final int ACTION_LOAD_FAILURE = 0x002;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ACTION_LOAD_SUCCESS:
                    setImageBitmap((Bitmap) msg.obj);
                    break;
                case ACTION_LOAD_FAILURE:
                    setImageResource(R.drawable.ic_launcher_foreground);
                    break;
            }
        }
    };


    public ImageWrapper(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }


    public void setImageWithUrl(final String urlpath) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                URL url = null;
                Message msg = new Message();
                try {
                    url = new URL(urlpath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    if (conn.getResponseCode() == 200) {
                        msg.what = ACTION_LOAD_SUCCESS;
                        InputStream is = conn.getInputStream();
                        msg.obj = BitmapFactory.decodeStream(is);
                        mHandler.sendMessage(msg);
                    } else {
                        msg.what = ACTION_LOAD_FAILURE;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = ACTION_LOAD_FAILURE;
                    mHandler.sendMessage(msg);
                }
            }
        }.start();

    }
}
