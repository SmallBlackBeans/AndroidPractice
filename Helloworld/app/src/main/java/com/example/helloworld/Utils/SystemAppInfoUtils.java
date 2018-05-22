package com.example.helloworld.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.helloworld.Beans.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanchenghai
 * @time 2018/5/22 下午2:54
 * @desc 获取所有的安装的app信息
 */

public class SystemAppInfoUtils {

    public static List<AppInfo> getAllAppInfo(Context context) {
        List<AppInfo> infos = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo app : packages) {
            String packageName = app.packageName;
            String name = app.applicationInfo.name;
            Drawable icon = app.applicationInfo.loadIcon(pm);
            String path = app.applicationInfo.sourceDir;
            File file = new File(path);
            long size = file.length();
            int flags = app.applicationInfo.flags;
            //与系统程序的标签进行与操作，如果为0表示是应用程序，否则是系统程序
            boolean isUser = true;
            if ((flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                isUser = false;
            }
            boolean isRom = false;
            if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == 0) {
                //安装在内存中
                isRom = true;
            }
            AppInfo info = new AppInfo(name, packageName, icon, isRom, isUser, size);
            infos.add(info);
        }
        return infos;
    }
}
