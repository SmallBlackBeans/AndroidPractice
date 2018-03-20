package com.example.helloworld.Projects.Ad.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hanchenghai on 2018/3/20.
 */

public class SharePreferenceUtil {
    public static final String HANXIAOCU_FILE_NAME = "hanxiaocu_cache";

    public static void setString(Context context, String title, String content) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(title, content);
        //editor.commit();//立马写入
        editor.apply();//空闲时写
    }

    public static String getString(Context context, String title) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(title,"");
    }

    
    public static void setInt(Context context, String title, int i) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(title, i);
        //editor.commit();//立马写入
        editor.apply();//空闲时写
    }

    public static int getInt(Context context, String title) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(title,0);
    }


    public static void setLong(Context context, String title, Long time) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(title, time);
        //editor.commit();//立马写入
        editor.apply();//空闲时写
    }

    public static long getLong(Context context, String title) {
        SharedPreferences preferences = context.getSharedPreferences(HANXIAOCU_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getLong(title,0);
    }


}
