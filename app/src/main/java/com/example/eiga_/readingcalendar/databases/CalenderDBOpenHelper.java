package com.example.eiga_.readingcalendar.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CalenderDBOpenHelper extends SQLiteOpenHelper {

    // バージョン
    private static  final int DATABASE_VERSION = 1;
    // データベース名
    private static final String DATABASE_NAME = "calenderDB.db";
    private static final String CALENDER_TABLE_NAME = "calenders";
    private static final String CALENDER_CREATE_SQL = "CREATE TABLE calenders ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "day TEXT NOT NULL,"
            + "plan_title TEXT,"
            + "plan_type TEXT,"
            + "time_zone TEXT,"
            + "income INTEGER,"
            + "spending INTEGER,"
            + "reading_data INTEGER"
            + ");";
    private static final String MYSET_PLAN_TABLE_NAME = "myset_plans";
    private static final String MYSET_PLAN_CREATE_SQL = "";
    private static final String READING_DATA_TABLE_NAME = "reading_datas";
    private static final String READING_DATA_CREATE_SQL = "";

    CalenderDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(CALENDER_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデート
        db.execSQL("DROP TABLE IF EXISTS " + CALENDER_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + MYSET_PLAN_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + READING_DATA_TABLE_NAME + ";");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
