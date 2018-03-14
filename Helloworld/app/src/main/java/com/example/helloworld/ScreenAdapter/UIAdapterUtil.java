package com.example.helloworld.ScreenAdapter;

import android.content.Context;

/**
 * Created by hanchenghai on 2018/3/14.
 */

public class UIAdapterUtil {

    //3:4:6:8:12
    /*
        ldpi   1dp = 0.75px  320 * 240  160dp = 120px
        mdpi   1dp = 1px     480 * 320  160dp = 160px
        hdpi   1dp = 1.5px   800 * 480  160dp = 240px
        xhdpi  1dp = 2px     1280 * 720 160dp = 320px-360px 180dp = 360px
        xxhdpi 1dp = 3dp     1920 * 1080 160dp = 480px < 540px
        xxxhdpi                                  640px
    */



    /*
    dpi sqrt((w^2 + h^2)) / 斜对角线英寸
    如 1080 * 1920
    2203px / 5寸 = 440dpi 属于xxhdpi
    */

    //代码中默认单位不是dp 是px
    public static int dp2px(Context context, int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}
