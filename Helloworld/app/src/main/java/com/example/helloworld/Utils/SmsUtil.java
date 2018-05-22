package com.example.helloworld.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

/**
 * @author hanchenghai
 * @time 2018/5/22 下午12:20
 * @desc
 */

public class SmsUtil {


    /**
     * 短信备份
     *
     * @return
     */
    public static boolean smsBackUp(Context context, String filename) {
        try {
            //查询数据库
            /*获取内容解析器*/
            ContentResolver resolver = context.getContentResolver();
            Uri uri = Uri.parse("content://sms/");
            //保存文件到本地的一个文件夹路径
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            //文件写入流
            FileOutputStream fos = null;

            fos = new FileOutputStream(file);

            //得到一个序列化器
            XmlSerializer serializer = Xml.newSerializer();
            //设置输出流
            serializer.setOutput(fos, "utf-8");
            serializer.startDocument("utf-8", true);

            serializer.startTag(null, "info");
            Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
            while (cursor.moveToNext()) {
                serializer.startTag(null, "sms");

                serializer.startTag(null, "address");
                String address = cursor.getString(0);
                serializer.text(address);
                serializer.endTag(null, "address");

                serializer.startTag(null, "date");
                String date = cursor.getString(1);
                serializer.text(date);
                serializer.endTag(null, "date");

                serializer.startTag(null, "type");
                String type = cursor.getString(2);
                serializer.text(type);
                serializer.endTag(null, "type");

                serializer.startTag(null, "body");
                String body = cursor.getString(3);
                serializer.text(body);
                serializer.endTag(null, "body");

                serializer.endTag(null, "sms");
            }
            serializer.endTag(null, "info");
            serializer.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
