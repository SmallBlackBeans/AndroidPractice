package com.example.helloworld.SingleInstance;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hanchenghai on 2018/3/14.
 */

public class ToastManager {
    private Context mContext;

    private ToastManager(Context context) {
        mContext = context;
    }

    private static ToastManager mManager = null;

    //懒汉式 单例
    public static ToastManager getInstance(Context context) {
        if (mManager == null) {
            synchronized (ToastManager.class) {
                if (mManager == null) {
                    mManager = new ToastManager(context);
                }
            }
        }
        return mManager;
    }


    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
