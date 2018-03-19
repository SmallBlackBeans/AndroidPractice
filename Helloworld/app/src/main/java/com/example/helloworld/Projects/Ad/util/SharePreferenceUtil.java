package com.example.helloworld.Projects.Ad.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hanchenghai on 2018/3/20.
 */

public class SharePreferenceUtil {
    public static final String HANXIAOCU_FILE_NAME = "hanxiaocu_cache";

    public static void saveJson(Context context, String title, String content) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(title, content);
        //editor.commit();//立马写入
        editor.apply();//空闲时写
    }

    public static String getJson(Context context, String title) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(title,"");
    }


    public static void saveJsonTime(Context context, String title, int time) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(title, time);
        //editor.commit();//立马写入
        editor.apply();//空闲时写
    }

    public static int getSaveJsonTime(Context context, String title) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(title,0);
    }


}
