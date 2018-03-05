package com.example.helloworld.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hanchenghai on 2018/3/5.
 */

public class NetworkUtil {


    /**
     * @param urlPath
     * @return
     * @throws Exception
     */
    public static InputStream getResource(String urlPath) throws Exception {
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (conn.getResponseCode() == 200) {
            return conn.getInputStream();
        }
        return null;
    }

}
