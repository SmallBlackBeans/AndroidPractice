package com.example.helloworld.Projects.Ad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.helloworld.Projects.Ad.Bean.Ads;
import com.example.helloworld.Projects.Ad.service.DownloadService;
import com.example.helloworld.Projects.Ad.util.JsonUtil;
import com.example.helloworld.Projects.Ad.util.SharePreferenceUtil;
import com.example.helloworld.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.img_ad)
    ImageView mImgAd;
    private final OkHttpClient mClient = new OkHttpClient();
    private static final String JSON_CACHE = "json_cache";
    private static final String JSON_CACHE_TIMEOUT = "json_cache_time_out";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //4.4沉浸式
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        String json = SharePreferenceUtil.getJson(SplashActivity.this, JSON_CACHE);
        int time = SharePreferenceUtil.getSaveJsonTime(SplashActivity.this, JSON_CACHE_TIMEOUT);
        getAds();
    }


    public void getAds() {
        final Request request = new Request.Builder()
                .url(Constant.SPLASH_URL)
                .build();


        //开启一个异步请求
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               if (!response.isSuccessful()) {//请求失败

               }
               String data = response.body().string();
                Ads ads = JsonUtil.parseJson(data, Ads.class);
                if (null != ads) {
                    SharePreferenceUtil.saveJson(SplashActivity.this,JSON_CACHE,data);
                    SharePreferenceUtil.saveJsonTime(SplashActivity.this,JSON_CACHE_TIMEOUT,ads.getNext_req());
                    //请求成功
                    Log.i("hanxiaocu",ads.toString());
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, DownloadService.class);
                    intent.putExtra(DownloadService.ADS_DATA,ads);
                    startService(intent);
                }else {
                    //失败
                }

            }
        });
    }

}
