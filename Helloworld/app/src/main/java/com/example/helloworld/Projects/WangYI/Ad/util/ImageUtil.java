package com.example.helloworld.Projects.WangYI.Ad.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.helloworld.Projects.WangYI.Ad.Constant;

import java.io.File;

/**
 * Created by hanchenghai on 2018/3/20.
 */

public class ImageUtil {

    public static boolean checkImageIsAreadyCached(String cache_name) {
        File image = getFileByName(cache_name);
        if (image.exists()) {
            Bitmap bitmap = getBitmapByFile(image);
            if (bitmap != null) {
                return true;
            }
        }
        return false;
    }

    public static File getFileByName(String md5name) {
        File SD = Environment.getExternalStorageDirectory();
        File cacheFile = new File(SD, Constant.DOWNLOAD_CACHE);
        File image = new File(cacheFile, md5name + ".jpg");
        return image;
    }


    public static Bitmap getBitmapByFile(File file) {
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap;
    }

}
