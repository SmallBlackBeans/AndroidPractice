package com.example.helloworld.Projects.Ad.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.helloworld.Projects.Ad.Bean.AdDetail;
import com.example.helloworld.Projects.Ad.Bean.Ads;
import com.example.helloworld.Projects.Ad.Constant;
import com.example.helloworld.Projects.Ad.util.Md5Helper;

import org.xutils.common.util.MD5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by hanchenghai on 2018/3/19.
 */

public class DownloadService extends IntentService {

    public static final String ADS_DATA = "ads";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Ads ads = (Ads) intent.getSerializableExtra(ADS_DATA);
        for (AdDetail detail : ads.getAds()) {
            List<String> imgs = detail.getRes_url();
            if (null != imgs) {
                String img = imgs.get(0);
                Log.d("url", img);
                if (!TextUtils.isEmpty(img)) {
                    //下载图片
                    //先转成md5
                    String md5Cache_name = Md5Helper.toMD5(img);

                    //先判断本地有没有
                    if (!checkImageIsAreadyCached(md5Cache_name)) {
                        Bitmap bitmap = downLoadImage(img);
                        if (null != bitmap) {
                            saveToCache(bitmap, md5Cache_name);
                        }
                    } else {

                    }

                }
            }
        }
    }

    private Bitmap downLoadImage(String url) {
        try {
            URL uri = new URL(url);
            URLConnection connection = uri.openConnection();
//            //BitMap 大图片处理
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveToCache(Bitmap bitmap, String md5_name) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File SD = Environment.getExternalStorageDirectory();
            File cacheFile = new File(SD, Constant.DOWNLOAD_CACHE);
            if (!cacheFile.exists()) {
                cacheFile.mkdirs();
            }

            File image = new File(cacheFile, md5_name + ".jpg");
            if (image.exists()) {
                return;
            }

            try {
                FileOutputStream outputStream = new FileOutputStream(image);
                //压缩到SD卡
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream);
                outputStream.flush();
                outputStream.close();
                Log.d("hanxiaocu", "done");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }


        } else {
            File cacheDir = getCacheDir();
        }
    }

    private boolean checkImageIsAreadyCached(String cache_name) {
        File SD = Environment.getExternalStorageDirectory();
        File cacheFile = new File(SD, cache_name + ".jpg");
        if (cacheFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());
            if (bitmap != null) {
                return true;
            }
        }
        return false;
    }
}
