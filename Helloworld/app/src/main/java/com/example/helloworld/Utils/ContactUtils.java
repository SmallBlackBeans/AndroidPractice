package com.example.helloworld.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanchenghai
 * @time 2018/5/17 下午4:33
 * @desc 获取联系人信息
 */

public class ContactUtils {
    public static List<ContactInfo> readContact(Context context) {
        List<ContactInfo> list = new ArrayList<>();
        //获取解析器
        ContentResolver resolver = context.getContentResolver();
        //获取查询的Uri 先获取contact表 id,通过id 查询对应的数据
        Uri dataUri = Uri.parse("contact://com.android.contacts/data");
        Uri uri = Uri.parse("contact://com.android.contacts/raw_contacts");
        //通过路径获取到数据库数据
        Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            if (!TextUtils.isEmpty(id)) {
                ContactInfo info = new ContactInfo();
                Cursor dataCursor = resolver.query(dataUri, new String[]{"mimetype", "data1"}, "raw_contact_id=?", new String[]{id}, null, null);
                while (dataCursor.moveToNext()) {
                    String mimetype = dataCursor.getString(0);
                    if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                        String data1 = dataCursor.getString(1);
                        info.phone = data1;
                    }else  if("vnd.android.cursor.item/name".equals(mimetype)){
                        String data1 = dataCursor.getString(1);
                        info.name = data1;
                    }
                }
                list.add(info);
            }
        }
        return list;
    }


   public static class ContactInfo {
        String name;
        String phone;
    }
}
