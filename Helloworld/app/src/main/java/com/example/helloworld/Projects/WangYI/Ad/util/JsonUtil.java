package com.example.helloworld.Projects.WangYI.Ad.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by hanchenghai on 2018/3/19.
 */

public class JsonUtil {

    static Gson mGson;

    public static <T> T parseJson(String json, Class<T> tClass) {
        Log.i("解析",json);
        if (mGson == null) {
            mGson = new Gson();
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return mGson.fromJson(json,tClass);
    }

}
