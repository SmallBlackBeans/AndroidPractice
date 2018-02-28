package com.example.helloworld.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by hanchenghai on 2018/2/28.
 */

public class ContactDAO {

    private DbOpenHelper mHelper;

    public ContactDAO(Context c) {
        mHelper = new DbOpenHelper(c);
    }

    public void insertContact(String username, String phone) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        db.execSQL("insert into contactinfo(" + DbOpenHelper._USERNAME + "," + DbOpenHelper._PHONE + ") values(?,?)", new String[]{username, phone});

    }


    public void updateContact(String username, String phone) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("update contactinfo set phone=? where username=?;", new String[]{phone, username});
    }

    public void deleteContact(String username) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from contactinfo where username=?", new String[]{username});
    }


    public String queryContact(String phone) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select username from contactinfo where phone=?", new String[]{phone});
        boolean b = cursor.moveToNext();
        String user = "";
        while (b) {
            user = cursor.getString(0);
            Log.d(TAG, "queryContact: " + user);
            b = cursor.moveToNext();
        }
        return user;
    }

}
