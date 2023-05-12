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
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DATE = "date";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean deleteNote(long noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(noteId)});
        db.close();
        return rowsAffected > 0;
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
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement this method if you need to upgrade the database schema in future versions
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
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));

                Note noteItem = new Note(id, title, note, time, date);
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
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));

            note = new Note(id, title, noteContent, time, date);
        }

        cursor.close();
        db.close();
        return note;
    }
}
