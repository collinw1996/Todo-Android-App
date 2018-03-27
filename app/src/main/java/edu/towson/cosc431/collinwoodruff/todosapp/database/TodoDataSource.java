package edu.towson.cosc431.collinwoodruff.todosapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 11/9/2017.
 */

public class TodoDataSource implements IDataSource {

    private static TodoDataSource instance;
    private TodoDBHelper dbHelper;

    private TodoDataSource(Context ctx) {
        dbHelper = new TodoDBHelper(ctx);
    }

    public static TodoDataSource getInstance(Context ctx) {
        if(instance == null) {
            instance = new TodoDataSource(ctx);
        }
        return instance;
    }

    public List<Todo> getAllTodos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.TABLE_NAME, null);
        List<Todo> todos = new ArrayList<>();
        while(cursor.moveToNext()) {
            Todo todo = new Todo();
            String id = cursor.getString(cursor.getColumnIndex(DatabaseContract._ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
            String contents = cursor.getString(cursor.getColumnIndex(DatabaseContract.CONTENTS_COLUMN_NAME));
            long date = cursor.getLong(cursor.getColumnIndex(DatabaseContract.DATE_COLUMN_NAME));
            boolean isAwesome = cursor.getInt(cursor.getColumnIndex(DatabaseContract.ISCOMPLETE_COLUMN_NAME)) == 1;
            todo.setId(id);
            todo.setTitle(title);
            todo.setContents(contents);
            todo.setDateCreated(date);
            todo.setComplete(isAwesome);
            todos.add(todo);
        }
        return todos;
    }

    public List<Todo> getTodo(String id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.TABLE_NAME + " where " + DatabaseContract._ID + " = ?", new String[]{id});
        List<Todo> todos = new ArrayList<>();
        while(cursor.moveToNext()) {
            Todo todo = new Todo();
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
            String contents = cursor.getString(cursor.getColumnIndex(DatabaseContract.CONTENTS_COLUMN_NAME));
            long date = cursor.getInt(cursor.getColumnIndex(DatabaseContract.DATE_COLUMN_NAME));
            boolean isComplete = cursor.getInt(cursor.getColumnIndex(DatabaseContract.ISCOMPLETE_COLUMN_NAME)) == 1;
            todo.setId(id);
            todo.setTitle(title);
            todo.setContents(contents);
            todo.setDateCreated(date);
            todo.setComplete(isComplete);
            todos.add(todo);
        }
        return todos;
    }

    public List<Todo> getActiveTodos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.TABLE_NAME + " where " + DatabaseContract.ISCOMPLETE_COLUMN_NAME + " = 0", null);
        List<Todo> todos = new ArrayList<>();
        while(cursor.moveToNext()) {
            Todo todo = new Todo();
            String id = cursor.getString(cursor.getColumnIndex(DatabaseContract._ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
            String contents = cursor.getString(cursor.getColumnIndex(DatabaseContract.CONTENTS_COLUMN_NAME));
            long date = cursor.getLong(cursor.getColumnIndex(DatabaseContract.DATE_COLUMN_NAME));
            boolean isComplete = cursor.getInt(cursor.getColumnIndex(DatabaseContract.ISCOMPLETE_COLUMN_NAME)) == 1;
            todo.setId(id);
            todo.setTitle(title);
            todo.setContents(contents);
            todo.setDateCreated(date);
            todo.setComplete(isComplete);
            todos.add(todo);
        }
        return todos;
    }

    public List<Todo> getCompletedTodos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.TABLE_NAME + " where " + DatabaseContract.ISCOMPLETE_COLUMN_NAME + " = 1", null);
        List<Todo> todos = new ArrayList<>();
        while(cursor.moveToNext()) {
            Todo todo = new Todo();
            String id = cursor.getString(cursor.getColumnIndex(DatabaseContract._ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
            String contents = cursor.getString(cursor.getColumnIndex(DatabaseContract.CONTENTS_COLUMN_NAME));
            long date = cursor.getLong(cursor.getColumnIndex(DatabaseContract.DATE_COLUMN_NAME));
            boolean isAwesome = cursor.getInt(cursor.getColumnIndex(DatabaseContract.ISCOMPLETE_COLUMN_NAME)) == 1;
            todo.setId(id);
            todo.setTitle(title);
            todo.setContents(contents);
            todo.setDateCreated(date);
            todo.setComplete(isAwesome);
            todos.add(todo);
        }
        return todos;
    }

    @Override
    public void addTodo(Todo todo) {
        ContentValues cv = todoToContentValues(todo);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseContract.TABLE_NAME, null, cv);
    }

    @Override
    public void updateTodo(Todo todo){
        ContentValues cv = todoToContentValues(todo);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(DatabaseContract.TABLE_NAME, cv, DatabaseContract.TITLE_COLUMN_NAME + "= ?", new String[]{todo.getTitle()});
        //db.update(DatabaseContract.TABLE_NAME, cv, DatabaseContract._ID + "= ?", new String[]{todo.getId()});
    }

    @Override
    public void deleteTodo(Todo todo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseContract.TABLE_NAME, DatabaseContract.TITLE_COLUMN_NAME + "= ?", new String[]{todo.getTitle()});
        //db.delete(DatabaseContract.TABLE_NAME, DatabaseContract._ID + "= ?", new String[]{todo.getId()});
    }

    private ContentValues todoToContentValues(Todo todo) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract._ID, todo.getId());
        cv.put(DatabaseContract.TITLE_COLUMN_NAME, todo.getTitle());
        cv.put(DatabaseContract.CONTENTS_COLUMN_NAME, todo.getContents());
        cv.put(DatabaseContract.DATE_COLUMN_NAME, todo.getDateCreated());
        cv.put(DatabaseContract.ISCOMPLETE_COLUMN_NAME, todo.isComplete());
        return cv;
    }

}
