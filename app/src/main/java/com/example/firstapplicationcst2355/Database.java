package com.example.firstapplicationcst2355;
import android.content.Context;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    //static variables for column names
    public static final String TABLE_NAME = "todo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_URGENCY = "urgency";

    // Constructor
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + " TEXT NOT NULL, " +
                COLUMN_URGENCY + " TEXT NOT NULL);";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteTodo(TodoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(todoItem.getId())});
        } finally {
            db.close();
        }
    }

    public void addTodo(TodoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, todoItem.getTask());
        values.put(COLUMN_URGENCY, todoItem.isUrgent() ? 1 : 0);

        try {
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    //debug function to print database information
    public void printCursor(android.database.Cursor cursor) {
        Log.d("Database Info", "Database Version: " + DATABASE_VERSION);
        Log.d("Database Info", "Number of Columns: " + cursor.getColumnCount());

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.d("Database Info", "Column " + i + ": " + cursor.getColumnName(i));
        }

        Log.d("Database Info", "Number of Results: " + cursor.getCount());

        while (cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.d("Database Info", cursor.getColumnName(i) + ": " + cursor.getString(i));
            }
        }
    }
}
