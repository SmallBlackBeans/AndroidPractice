package com.example.helloworld.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author hanchenghai
 * @time 2018/5/17 下午7:57
 * @desc
 */

public class MD5Utils {

    public static String string2Md5(String str) {
        MessageDigest digest = null;
        StringBuffer buffer = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            buffer = new StringBuffer();
            byte[] bytes = digest.digest(str.getBytes());
            for (byte b : bytes) {
                String bStr = Integer.toHexString(b & 0xff);
                if (bStr.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(bStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        return buffer.toString();

    }

}
