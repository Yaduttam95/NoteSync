package com.example.notesync;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "notes";
    private static final String TASK_TABLE_NAME = "tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DATE = "date";

    private static final String TASK_COLUMN_ID = "task_id";

    private static final String COLUMN_TASK_TITLE = "task_title";

    private static final String COLUMN_TASK_DESC = "description";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deleteNote(long noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_NOTE + " TEXT, " +
                COLUMN_TIME + " TEXT," +
                COLUMN_DATE + " TEXT" +
                ")";

        String createTaskTableQuery = "CREATE TABLE " + TASK_TABLE_NAME + "(" +
                TASK_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK_TITLE + " TEXT, " +
                COLUMN_TASK_DESC + " TEXT)";

        db.execSQL(createTableQuery);
        db.execSQL(createTaskTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // upgrade the database schema in future versions
    }

    public void addNote(String title, String note, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_NOTE, note);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                @SuppressLint("Range") String note = cursor.getString(cursor.getColumnIndex(COLUMN_NOTE));

                Note noteItem = new Note(id, title, note);
                noteList.add(noteItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return noteList;
    }

    public Note getNoteById(long noteId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Note note = null;

        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + " = ?",
                new String[]{String.valueOf(noteId)}, null, null, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            @SuppressLint("Range") String noteContent = cursor.getString(cursor.getColumnIndex(COLUMN_NOTE));

            note = new Note(id, title, noteContent);
        }

        cursor.close();
        db.close();
        return note;
    }

    public void addTask(String title, String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_TITLE, title);
        values.put(COLUMN_TASK_DESC, task);
        db.insert( TASK_TABLE_NAME, null, values);
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query( TASK_TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_TITLE));
                @SuppressLint("Range") String task = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_DESC));

                Task taskItem = new Task(id, title, task);
                taskList.add(taskItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taskList;
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_TABLE_NAME, TASK_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}
