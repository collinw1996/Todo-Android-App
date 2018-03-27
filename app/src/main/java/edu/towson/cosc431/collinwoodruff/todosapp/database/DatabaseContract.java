package edu.towson.cosc431.collinwoodruff.todosapp.database;

import android.provider.BaseColumns;

/**
 * Created by Collin on 11/9/2017.
 */

public class DatabaseContract implements BaseColumns {
    public static final String TABLE_NAME = "Todo";
    public static final String TITLE_COLUMN_NAME = "Title";
    public static final String CONTENTS_COLUMN_NAME = "Contents";
    public static final String DATE_COLUMN_NAME = "Date";
    public static final String ISCOMPLETE_COLUMN_NAME = "IsComplete";
}
