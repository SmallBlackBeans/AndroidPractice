package com.example.helloworld.utils;

import android.content.Context;
import android.util.Xml;

import com.example.helloworld.Network.NewsBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

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
                } else if (xmlPullParser.getName().equals("password")) {
                    result += ("password=" + xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }

        return result;
    }


    public static ArrayList<NewsBean> parseNews(InputStream is) throws Exception {
        ArrayList<NewsBean> results = new ArrayList<>();
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        int eventType = parser.getEventType();
        NewsBean bean = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.getName().equals("item")) {
                    bean = new NewsBean();
                } else if (parser.getName().equals("title")) {
                    bean.setTitle(parser.nextText());
                } else if (parser.getName().equals("description")) {
                    bean.setDescription(parser.nextText());
                } else if (parser.getName().equals("image")) {
                    bean.setImage(parser.nextText());
                } else if (parser.getName().equals("type")) {
                    bean.setType(Integer.parseInt(parser.nextText()));
                } else if (parser.getName().equals("comment")) {
                    bean.setComment(Integer.parseInt(parser.nextText()));
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (parser.getName().equals("item")) {
                    results.add(bean);
                }
            }
            eventType = parser.next();
        }
        return results;
    }

}
