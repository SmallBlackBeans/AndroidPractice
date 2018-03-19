package com.example.helloworld.Basic.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hanchenghai on 2018/2/28.
 */

public class ContactGoogleDAO {

    private DbOpenHelper mHelper;

    public ContactGoogleDAO(Context c) {
        mHelper = new DbOpenHelper(c);
    }

    public boolean insertContact(String username, String phone) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper._USERNAME, username);
        values.put(DbOpenHelper._PHONE, phone);

        long rowId = db.insert(DbOpenHelper.TABLE_NAME, null, values);
        return rowId != -1;
    }


    public boolean updateContact(String username, String phone) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper._PHONE, phone);
        int updateRows = db.update(DbOpenHelper.TABLE_NAME, values, DbOpenHelper._USERNAME + "=?", new String[]{username});
        return updateRows > 0;
    }

    public boolean deleteContact(String username) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int deleteRows = db.delete(DbOpenHelper.TABLE_NAME, DbOpenHelper._USERNAME + "=?", new String[]{username});
        return deleteRows > 0;
    }


    public String queryContact(String searchPhone) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String result = "";
        Cursor query = db.query(DbOpenHelper.TABLE_NAME, new String[]{DbOpenHelper._USERNAME, DbOpenHelper._PHONE}, "phone=?", new String[]{searchPhone}, null, null, null);
        while (query.moveToNext()) {
            int usernameColumnIndex = query.getColumnIndex(DbOpenHelper._USERNAME);
            String username = query.getString(usernameColumnIndex);
            int phoneIndex = query.getColumnIndex(DbOpenHelper._PHONE);
            String phone = query.getString(phoneIndex);

            result += (username + "  " + phone + " \n");
        }
        return result;
    }

}
