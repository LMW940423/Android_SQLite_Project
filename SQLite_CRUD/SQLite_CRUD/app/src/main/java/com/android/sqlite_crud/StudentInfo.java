package com.android.sqlite_crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class StudentInfo extends SQLiteOpenHelper {

    final static String TAG = "StudentInfo";

    public StudentInfo(@Nullable Context context) { // Nullable = 꼭 써도 되고 안 써도 된다.
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate");
        String query = "CREATE TABLE student(sno INTEGER PRIMARY KEY AUTOINCREMENT, usename TEXT, userid TEXT, userpw TEXT, usermajor TEXT, usertel TEXT);";
        db.execSQL(query); // 쿼리문에 따른 테이블 생성
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "onUpgrade");
        String query = "DROP TABLE IF EXISTS student;";
        db.execSQL(query);
        onCreate(db); // 드랍한 다음 다시 만든다.

    }
}
