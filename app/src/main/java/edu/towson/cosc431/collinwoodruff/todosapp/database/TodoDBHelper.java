package edu.towson.cosc431.collinwoodruff.todosapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Collin on 11/9/2017.
 */

public class TodoDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todos.db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE =
            "create table " + DatabaseContract.TABLE_NAME + " ( " +
                    DatabaseContract._ID + " text primary key," +
                    DatabaseContract.TITLE_COLUMN_NAME + " text," +
                    DatabaseContract.CONTENTS_COLUMN_NAME + " text, " +
                    DatabaseContract.DATE_COLUMN_NAME + " long, " +
                    DatabaseContract.ISCOMPLETE_COLUMN_NAME + " integer);";

    public TodoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
