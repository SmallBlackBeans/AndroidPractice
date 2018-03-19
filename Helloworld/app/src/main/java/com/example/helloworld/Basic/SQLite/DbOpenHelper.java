package com.example.helloworld.Basic.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by hanchenghai on 2018/2/28.
 */

public class DbOpenHelper extends SQLiteOpenHelper implements BaseColumns {



    //数据库版本
    public static final int DB_VERSION = 1;
    // 数据库名称
    public static final String DB_NAME = "contact.sqlite";


    public static final String TABLE_NAME = "contactinfo";
    public static final String _USERNAME = "username";
    public static final String _PHONE = "phone";

    /**
     * @param context
     */
    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //创建数据库后会调用，在这里创建数据库表
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("hanxiaocu", "onCreate: ");
        db.execSQL("create table " + TABLE_NAME
                + "("
                + _ID + " integer primary key autoincrement,"
                + _USERNAME + " text," + _PHONE + " text)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("hanxiaocu", "onUpgrade: ");
    }
}
