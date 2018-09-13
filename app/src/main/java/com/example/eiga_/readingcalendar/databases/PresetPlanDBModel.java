package com.example.eiga_.readingcalendar.databases;

import android.content.Context;
import android.database.Cursor;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;

import com.example.eiga_.readingcalendar.data.PlanData;

import java.util.ArrayList;
import java.util.List;

public class PresetPlanDBModel extends DBModelBase {

    private final String PRESET_PLAN_TABLE_NAME = "preset_plans";

    public PresetPlanDBModel(Context context) {
        super(context);
    }

    @Override
    public String searchData(String column, String keyword) {
        Cursor cursor = null;
        try {
            //SQL文
            String sql = "SELECT * FROM " + PRESET_PLAN_TABLE_NAME + " WHERE ? = ?";
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

    public List<PlanData> searchDataAll() {
        Cursor cursor = null;
        try {
            //SQL
            String sql = "SELECT * FROM " + PRESET_PLAN_TABLE_NAME;
            //SQL実行
            cursor = db.rawQuery(sql, null);
            return readCursorAll(cursor);
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
    }

    public Cursor getSearchDataCursor() {
            // SQL
            String sql = "SELECT * FROM " + PRESET_PLAN_TABLE_NAME;
            // SQL実行
            Cursor cursor = db.rawQuery(sql, null);

            return cursor;
    }

    @Override
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

    @Override
    List<PlanData> readCursorAll (Cursor cursor) {
        // カーソル位置を先頭に
        cursor.moveToFirst();
        // 返すlistを生成。
        List<PlanData> planDataList = new ArrayList<>();
            while (cursor.moveToNext()) {
                // PlanDataオブジェクトを生成。
                PlanData planData = new PlanData();
                // 各データを格納
                planData.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                planData.setTitle(cursor.getString(cursor.getColumnIndex("plan_title")));
                planData.setStartTime(cursor.getString(cursor.getColumnIndex("start_time")));
                planData.setEndTime(cursor.getString(cursor.getColumnIndex("end_time")));
                planData.setUseTime(cursor.getInt(cursor.getColumnIndex("use_time")));
                planData.setType(cursor.getString(cursor.getColumnIndex("plan_type")));
                planData.setIncome(cursor.getInt(cursor.getColumnIndex("income")));
                planData.setSpending(cursor.getInt(cursor.getColumnIndex("spending")));
                planData.setMemo(cursor.getString(cursor.getColumnIndex("memo")));
                // Listに追加
                planDataList.add(planData);
            }
        return planDataList;
    }

    public void insertData(String planTitle, String planType, String startTime, String endTime, String useTime, String income, String spending, String memoText) {
        String sql = "INSERT INTO " + PRESET_PLAN_TABLE_NAME
                + "(plan_title, plan_type, start_time,end_time,use_time,income,spending,memo) values(?,?,?,?,?,?,?,?);";
        String[] bindStr = new String[] {
                planTitle,
                planType,
                startTime,
                endTime,
                useTime,
                income,
                spending,
                memoText
        };
        super.executeSql(sql,bindStr);
    }

    public void updateData(String column, String keyword, String planTitle, String planType, String startTime, String endTime, String useTime, String income, String spending, String memoText) {
        String sql = "UPDATE " + PRESET_PLAN_TABLE_NAME
                + " SET plan_title = ?, plan_type = ?, time_zone = ?, income = ?, spending = ? "
                + "WHERE ? = ? ;";
        String[] bindStr = new String[] {
                planTitle,
                planType,
                startTime,
                endTime,
                useTime,
                income,
                spending,
                memoText,
                column,
                keyword
        };
        super.executeSql(sql,bindStr);
    }

    @Override
    void deleteData(String column, String keyword) {
        String sql = "DELETE FROM " + PRESET_PLAN_TABLE_NAME
                + " WHERE ? = ?;";
        String[] bindStr = new String[]{
                column,
                keyword
        };
        super.executeSql(sql,bindStr);
    }
}
