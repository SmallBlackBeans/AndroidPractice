package com.example.helloworld.Projects.WangYI.Utils;

import com.example.helloworld.Projects.WangYI.Services.WYHttpResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hanchenghai on 2018/3/26.
 */

public class WYHttpUtil {


    static WYHttpUtil sUtil;
    static OkHttpClient sClient;

    private WYHttpUtil() {
        sClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
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


    public void getData(String url, final WYHttpResponse wyResponse) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        sClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                wyResponse.onError("连接服务器失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) {
                    //请求失败
                }

                String data = response.body().string();

                wyResponse.parse(data);

            }
        });
    }


}
