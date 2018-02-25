package com.example.helloworld.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hanchenghai on 2018/2/23.
 */

public class ToastUtil {
    private static Toast mToast;

    public static void showMsg(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

}
