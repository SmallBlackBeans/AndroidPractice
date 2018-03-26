package com.example.helloworld.Projects.WangYI.Utils;

import com.example.helloworld.Projects.WangYI.Services.WYHttpResponse;

import okhttp3.OkHttpClient;

/**
 * Created by hanchenghai on 2018/3/26.
 */

public class WYHttpUtil {


    static WYHttpUtil sUtil;
    static OkHttpClient sClient;

    private WYHttpUtil() {
        sClient = new OkHttpClient();
    }


    public static WYHttpUtil getInstance() {
        if (sUtil == null) {
            synchronized (WYHttpUtil.class) {
                if (sUtil == null) {
                    sUtil = new WYHttpUtil();
                }
            }
        }
        return sUtil;
    }


    public void getData(String url, WYHttpResponse response) {

    }







}
