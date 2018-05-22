package com.example.helloworld.Beans;

import android.graphics.drawable.Drawable;

/**
 * @author hanchenghai
 * @time 2018/5/22 下午2:55
 * @desc
 */

public class AppInfo {
    private String appName;
    private String packageName;
    private Drawable icon;
    private boolean isRom;//是否安装在内存
    private long size;
    private boolean isUser;//系统软件和用户软件

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isRom() {
        return isRom;
    }

    public void setRom(boolean rom) {
        isRom = rom;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public AppInfo(String appName, String packageName, Drawable icon, boolean isRom, boolean isUser, long size) {
        this.appName = appName;
        this.packageName = packageName;
        this.icon = icon;
        this.isRom = isRom;
        this.size = size;
        this.isUser = isUser;
    }
}
