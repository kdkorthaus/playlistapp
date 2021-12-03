package com.example.songlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SongDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "mysongs.db";
    private static int DATABASE_VERSION = 1;

    public SongDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "create table song (_id integer primary key autoincrement, "
                + "title text not null, "
                + "artist text, "
                + "album text);";
        sqLiteDatabase.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS song");
        onCreate(sqLiteDatabase);
    }
}
