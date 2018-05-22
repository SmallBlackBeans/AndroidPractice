package com.example.helloworld.Utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * @author hanchenghai
 * @time 2018/5/18 下午2:13
 * @desc 动态检测服务是否开启
 */

public class ServiceStatusUtils {

    public static boolean isServiceRunning(Context context, String clazz) {
        //获取设备的相关服务
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取正在运行的100个服务
        List<ActivityManager.RunningServiceInfo> serviceInfos = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : serviceInfos) {
            if (clazz.equals(info.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
