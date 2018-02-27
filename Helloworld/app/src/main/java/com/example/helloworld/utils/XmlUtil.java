package com.example.helloworld.utils;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by hanchenghai on 2018/2/27.
 */

public class XmlUtil {

    /**
     * @param c
     * @return
     * @throws Exception
     */
    public static String parseXmlFile(Context c) throws Exception {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        FileInputStream inputStream = c.openFileInput("xxx.xml");
        xmlPullParser.setInput(inputStream, "utf-8");

        String result = "";

        //0 文档开始  1 文档结束  2 标签开始 3标签结束  4 text
        int eventType = xmlPullParser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (xmlPullParser.getName().equals("username")) {
                    result += ("username=" + xmlPullParser.nextText());
                }else if (xmlPullParser.getName().equals("password")) {
                    result += ("password=" + xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }

        return result;
    }

}
