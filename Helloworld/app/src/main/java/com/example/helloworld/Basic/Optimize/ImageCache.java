package com.example.helloworld.Basic.Optimize;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by hanchenghai on 2018/3/14.
 */

public class ImageCache {

    private final LruCache<String, Bitmap> mLruCache;

    private ImageCache() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        //数量上限一般是内存的1/8
        mLruCache = new LruCache<String, Bitmap>((int) (maxMemory / 8)) {
            //指定缓存中每个对象占用的内存大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }


    private static ImageCache sImageCache = null;

    public ImageCache getInstance() {
        if (sImageCache == null) {
            synchronized (ImageCache.class) {
                if (sImageCache == null) {
                    sImageCache = new ImageCache();
                }
            }
        }
        return sImageCache;
    }


    public Bitmap getBitmap(String key) {
        Bitmap bitmap = null;
        bitmap = mLruCache.get(key);
        if (bitmap != null) {
            return bitmap;
        }
        //从网络获取或者本地加载
        mLruCache.put(key,bitmap);
        return bitmap;
    }
}
