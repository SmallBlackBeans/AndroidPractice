package com.example.helloworld.Projects.WangYI.Services;

import android.text.TextUtils;

import com.example.helloworld.Projects.WangYI.Ad.util.JsonUtil;

/**
 * Created by hanchenghai on 2018/3/26.
 */

public abstract class WYHttpResponse<T> {
    //响应类型
    Class<T> tClass;


    public WYHttpResponse(Class<T> t) {
        this.tClass = t;
    }


    public abstract void onError(String msg);

    public abstract void onSuccess(T t);


    public void parse(String json) {

        if (TextUtils.isEmpty(json)) {
            onError("链接网络失败");
            return;
        }
        if (tClass == String.class) {
            onSuccess((T) json);
            return;
        }

            T result = JsonUtil.parseJson(json, tClass);
            if (result != null) {
                onSuccess(result);
            }else  {
                onError("数据解析失败");
            }

    }

}
