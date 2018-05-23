package com.example.helloworld.Utils;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author hanchenghai
 * @time 2018/5/23 下午2:55
 * @desc 系统进程管理
 */

public class SystemProcessUtils {


    /**
     * 当前运行的进程个数
     *
     * @param context
     * @return
     */
    public static int getRunningProcessCount(Context context) {
        return getRunningProcess(context).size();
    }



    public static List<ActivityManager.RunningAppProcessInfo> getRunningProcess(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        return infos;
    }

    /**
     * 获取当前可用的内存信息
     *
     * @param context
     * @return
     */
    public static long getAvailableMem(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    /**
     * 获取当前总的内存信息
     *
     * @param context
     * @return
     */
    public static long getTotalMem(Context context) {
        /*
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem
        ;*/
        //兼容低版本
        StringBuffer buffer = null;
        try {
            File file = new File("/proc/meminfo/");
            FileInputStream fis = new FileInputStream(file);
            buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = reader.readLine();
            for (char c : line.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    buffer.append(c);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Long.parseLong(buffer.toString()) * 1024;
    }


}
