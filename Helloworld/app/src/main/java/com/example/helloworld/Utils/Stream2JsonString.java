package com.example.helloworld.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author hanchenghai
 * @time 2018/5/16 下午4:53
 * @desc 将字节流转化为json格式的字符串
 */

public class Stream2JsonString {

    public static String readStream(InputStream is) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            line = reader.readLine();
        }
        return buffer + "";
    }

}
