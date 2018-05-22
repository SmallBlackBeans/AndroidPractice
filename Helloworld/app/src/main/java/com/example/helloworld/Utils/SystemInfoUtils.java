package com.example.helloworld.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by hanchenghai on 2018/5/16.
 */

public class SystemInfoUtils {


    /**
     * 获取包名的版本名称
     *
     * @param context
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static String getVersionName(Context context) throws PackageManager.NameNotFoundException {
        return getPackageInfo(context).versionName;
    }


    /**
     * 获取版本号
     *
     * @param context
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static int getVersionNum(Context context) throws PackageManager.NameNotFoundException {
        return getPackageInfo(context).versionCode;
    }

    /**
     * 获取包信息
     *
     * @param context
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static PackageInfo getPackageInfo(Context context) throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
        return info;
    }
}
