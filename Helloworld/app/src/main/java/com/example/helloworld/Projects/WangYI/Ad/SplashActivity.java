package com.example.helloworld.Projects.WangYI.Ad;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.helloworld.MainActivity;
import com.example.helloworld.Projects.WangYI.Ad.Bean.AdDetail;
import com.example.helloworld.Projects.WangYI.Ad.Bean.Ads;
import com.example.helloworld.Projects.WangYI.Ad.View.OnTimeClickListener;
import com.example.helloworld.Projects.WangYI.Ad.View.SkipTimeView;
import com.example.helloworld.Projects.WangYI.Ad.service.DownloadService;
import com.example.helloworld.Projects.WangYI.Ad.util.ImageUtil;
import com.example.helloworld.Projects.WangYI.Ad.util.JsonUtil;
import com.example.helloworld.Projects.WangYI.Ad.util.Md5Helper;
import com.example.helloworld.Projects.WangYI.Ad.util.SharePreferenceUtil;
import com.example.helloworld.R;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class SplashActivity extends AppCompatActivity {

    private final OkHttpClient mClient = new OkHttpClient();
    private ImageView mImgAd;
    private SkipTimeView mSkipTime;


    private Ads mAds;
    private AdDetail mAdDetail;//当前展示的广告


    private static final String JSON_CACHE = "json_cache";
    private static final String JSON_CACHE_TIMEOUT = "json_cache_time_out";
    private static final String JSON_SAVE_TIME = "json_save_time";
    private static final String IMAGE_INDEX = "image_index";


    int lenght = 5 * 1000;
    int space = 250;
    int now = 0;
    int total;
    MyHandler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //开启全屏的设置  取消状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //4.4沉浸式

        //View decorView = getWindow().getDecorView();
        //decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVe);

        setContentView(R.layout.activity_splash);
        mImgAd = findViewById(R.id.img_ad);
        mSkipTime = findViewById(R.id.skip_time);


        initView();
        getAds();
        showImage();

    }


    /*获取数据*/
    public void getAds() {
        //先判断是否有缓存
        String cacheJson = SharePreferenceUtil.getString(SplashActivity.this, JSON_CACHE);
        if (TextUtils.isEmpty(cacheJson)) {
            httpRequestAds();
        } else {
            int timeOut = SharePreferenceUtil.getInt(SplashActivity.this, JSON_CACHE_TIMEOUT);
            long lastSaveTime = SharePreferenceUtil.getLong(SplashActivity.this, JSON_SAVE_TIME);
            long currentTime = SystemClock.currentThreadTimeMillis();
            if ((currentTime - lastSaveTime) > timeOut * 60 * 1000) {
                httpRequestAds();
            } else {
                Log.d("hanxiaocu", "getAds: 来自缓存");
                mAds = JsonUtil.parseJson(cacheJson, Ads.class);
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, DownloadService.class);
                intent.putExtra(DownloadService.ADS_DATA, mAds);
                startService(intent);
            }
        }
    }


    public void showImage() {
        String cache = SharePreferenceUtil.getString(this, JSON_CACHE);

        if (!TextUtils.isEmpty(cache)) {

            //当有了广告的时候开始显示倒计时
            mSkipTime.setVisibility(View.VISIBLE);
            mHandler.post(refreshTime);


            //读取上次显示的图片的索引
            int index = SharePreferenceUtil.getInt(this, IMAGE_INDEX);

            mAds = JsonUtil.parseJson(cache, Ads.class);
            if (mAds == null) {
                return;
            }

            List<AdDetail> adDetails = mAds.getAds();
            if (null != adDetails && adDetails.size() > 0) {
                AdDetail adDetail = adDetails.get(index);
                List<String> urls = adDetail.getRes_url();
                if (urls != null && urls.size() > 0) {
                    String url = urls.get(0);
                    if (!TextUtils.isEmpty(url)) {
                        String image_name = Md5Helper.toMD5(url);
                        File image = ImageUtil.getFileByName(image_name);
                        if (image.exists()) {
                            Bitmap bitmap = ImageUtil.getBitmapByFile(image);
                            mImgAd.setImageBitmap(bitmap);
                            mAdDetail = adDetail;
                            index = (index + 1) % urls.size();
                            SharePreferenceUtil.setInt(this, IMAGE_INDEX, index);
                        }
                    }
                }
            }

        } else {
            //没有图片 3秒跳过
            mHandler.postDelayed(NoImageGotoMain, 5000);
        }
    }


    public void httpRequestAds() {
        Log.d("hanxiaocu", "getAds: 来自网络");
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
                mAds = JsonUtil.parseJson(data, Ads.class);
                if (null != mAds) {
                    SharePreferenceUtil.setString(SplashActivity.this, JSON_CACHE, data);
                    SharePreferenceUtil.setInt(SplashActivity.this, JSON_CACHE_TIMEOUT, mAds.getNext_req());
                    SharePreferenceUtil.setLong(SplashActivity.this, JSON_SAVE_TIME, SystemClock.currentThreadTimeMillis());
                    //请求成功
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, DownloadService.class);
                    intent.putExtra(DownloadService.ADS_DATA, mAds);
                    startService(intent);
                } else {
                    //失败
                }

            }
        });
    }

    @OnClick(R.id.img_ad)
    public void onViewClicked() {
        mHandler.sendMessage(new Message());
    }


    Runnable NoImageGotoMain = new Runnable() {
        @Override
        public void run() {
            gotoMain();
        }
    };


    //圆环的倒计时
    Runnable refreshTime = new Runnable() {
        @Override
        public void run() {
            Message message = mHandler.obtainMessage(0);
            message.arg1 = now;
            mHandler.sendMessage(message);
            //频率250毫秒刷一次
            mHandler.postDelayed(this, space);
            now++;
        }
    };


    private void gotoMain() {
//        Intent intent = new Intent();
//        intent.setClass(SplashActivity.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }


    //初始化控件
    private void initView() {
        mSkipTime.setVisibility(View.GONE);
        mSkipTime.setListener(new OnTimeClickListener() {
            @Override
            public void onClickTime(View view) {
                //直接跳转
                mHandler.removeCallbacks(refreshTime);
                gotoMain();
            }
        });
        //刷新的次数
        total = lenght / space;
        //内存泄露
        //Logger->UIThread
        mHandler = new MyHandler(this);

    }


    //静态类切断访问acitivity 内存泄露

    /**
     * 弱引用 jvm无法保证它的存活
     */
    static class MyHandler extends Handler {
        WeakReference<SplashActivity> mWeakReference;

        public MyHandler(SplashActivity activity) {
            mWeakReference = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity activity = mWeakReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case 0: {
                    int now = msg.arg1;
                    if (now < activity.total) {
                        activity.mSkipTime.setProgress(activity.total, now);
                    } else {
                        //this.removeCallbacks(activity.refreshTime);
                        //activity.gotoMain();
                    }
                }
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(refreshTime);
    }
}


