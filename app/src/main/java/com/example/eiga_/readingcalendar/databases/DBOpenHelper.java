package com.example.eiga_.readingcalendar.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    // バージョン
    private static  final int DATABASE_VERSION = 1;
    // データベース名
    private static final String DATABASE_NAME = "calenderDB.db";
    private static final String CALENDER_TABLE_NAME = "calenders";
    private static final String CALENDER_CREATE_SQL = "CREATE TABLE calenders ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "day TEXT NOT NULL,"
            + "plan_title TEXT,"
            + "plan_type TEXT,"
            + "start_time TEXT,"
            + "end_time TEXT,"
            + "use_time INTEGER,"
            + "income INTEGER,"
            + "spending INTEGER,"
            + "myset_plan_id INTEGER,"
            + "reading_data_id INTEGER,"
            + "created_at DEFAULT CURRENT_TIMESTAMP,"
            + "updated_at"
            + ");";
    private static final String PRESET_PLAN_TABLE_NAME = "preset_plans";
    private static final String PRESET_PLAN_CREATE_SQL = "CREATE TABLE " + PRESET_PLAN_TABLE_NAME + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "plan_title TEXT,"
            + "plan_type TEXT,"
            + "start_time TEXT,"
            + "end_time TEXT,"
            + "use_time INTEGER,"
            + "income INTEGER,"
            + "spending INTEGER,"
            + "memo TEXT,"
            + "created_at DEFAULT CURRENT_TIMESTAMP,"
            + "updated_at"
            + ");";
    private static final String READING_DATA_TABLE_NAME = "reading_datas";
    private static final String READING_DATA_CREATE_SQL = "CREATE TABLE reading_datas ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "data_title TEXT,"
            + "data_url TEXT,"
            + "data_days TEXT,"
            + "created_at DEFAULT CURRENT_TIMESTAMP,"
            + "updated_at"
            + ");";

    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(CALENDER_CREATE_SQL);
        db.execSQL(PRESET_PLAN_CREATE_SQL);
        db.execSQL(READING_DATA_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデート
        db.execSQL("DROP TABLE IF EXISTS " + CALENDER_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + PRESET_PLAN_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + READING_DATA_TABLE_NAME + ";");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
