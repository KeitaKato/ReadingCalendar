package com.example.eiga_.readingcalendar.databases;

import android.content.Context;
import android.database.Cursor;

import com.example.eiga_.readingcalendar.data.PlanData;

import java.util.List;

public class RedingDataDBModel extends DBModelBase {

    final String READING_DATA_TABLE_NAME = "reading_datas";

    RedingDataDBModel(Context context) {
        super(context);
    }

    public String searchData(String column, String keyword) {
        Cursor cursor = null;
        try {
            //SQL文
            String sql = "SELECT * FROM " + READING_DATA_TABLE_NAME + " WHERE ? = ?";
            //SQL文実行
            String[] bindStr = new String[]{column, keyword};

            cursor = db.rawQuery(sql, bindStr);
            return readCursor(cursor);
        } finally {
            if( cursor != null) {
                cursor.close();
            }
        }
    }
    String readCursor(Cursor cursor) {
        //カーソル開始位置を先頭にする
        cursor.moveToFirst();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= cursor.getCount(); i++) {
            //SQL文の結果から、必要な値を取り出す。
            sb.append(cursor.getString(1));//処理
            cursor.moveToNext();
        }
        return sb.toString();
    }

    List<PlanData> readCursorAll(Cursor cursor) {
        return null;
    }

    public void insertData(String dataTitle, String dataUrl, String dataDays) {
        String sql = "INSERT INTO " + READING_DATA_TABLE_NAME
                + "(data_title, data_url, data_days) values(?,?,?);";
        String[] bindStr = new String[] {
                dataTitle,
                dataUrl,
                dataDays,
        };
        super.executeSql(sql,bindStr);
    }

    public void updateData(String column, String keyword, String dataTitle, String dataUrl, String dataDays) {
        String sql = "UPDATE " + READING_DATA_TABLE_NAME
                + " SET data_title = ?, data_url = ?, data_days = ? "
                + "WHERE ? = ? ;";
        String[] bindStr = new String[] {
                dataTitle,
                dataUrl,
                dataDays,
                column,
                keyword
        };
        super.executeSql(sql,bindStr);
    }

    void deleteData(String column, String keyword) {
        String sql = "DELETE FROM " + READING_DATA_TABLE_NAME
                + " WHERE ? = ?;";
        String[] bindStr = new String[]{
                column,
                keyword
        };

        super.executeSql(sql,bindStr);
    }
}
