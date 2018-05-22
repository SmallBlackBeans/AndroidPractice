package com.example.helloworld.Utils;

/**
 * Created by hanchenghai on 2018/5/16.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.example.helloworld.Utils.Constant;

/**
 * 用来管理sp
 */
public class SPUtils {
    public static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(Constant.SPNAME, Context.MODE_PRIVATE);
    }

    public static boolean getBoolean(Context context, String key) {
        return getSp(context).getBoolean(key, false);
    }


    public static void putBoolean(Context context, String key, boolean state) {
        SharedPreferences sp = getSp(context);
        sp.edit().putBoolean(key,state).commit();
    }

    public static String getString(Context context, String key) {
        return getSp(context).getString(key,"");
    }


    public static void putString(Context context, String key, String string) {
        SharedPreferences sp = getSp(context);
        sp.edit().putString(key,string).commit();
    }


}

