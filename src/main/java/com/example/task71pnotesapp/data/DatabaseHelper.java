package com.example.task71pnotesapp.data;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.task71pnotesapp.CreateNote;
import com.example.task71pnotesapp.model.Note;
import com.example.task71pnotesapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " (" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Util.NOTE_TITLE + " TEXT," + Util.NOTE_DESCRIPTION + " TEXT)";
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        db.execSQL(DROP_NOTE_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);
    }
    public long insertNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE_TITLE, note.getNote_title());
        contentValues.put(Util.NOTE_DESCRIPTION, note.getNote_description());
        long newRowID = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowID;

    }
    public Note getNote(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.NOTE_ID, Util.NOTE_TITLE, Util.NOTE_DESCRIPTION}, Util.NOTE_ID+"=?",
                new String[]{String.valueOf(id)}, null,null, null);
        if (cursor.moveToFirst())
        {
            cursor.moveToFirst();
        }
        Note note = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        return note;
    }
    public List<Note> getNotes()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> Notes = new ArrayList<>();
        String query = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do{
                Note note = new Note();
                note.setNote_id(cursor.getInt(0));
                note.setNote_title((cursor.getString(1)));
                note.setNote_description((cursor.getString(2)));

                Notes.add(note);
            }while(cursor.moveToNext());
            cursor.moveToFirst();
        }
        return  Notes;
    }
    public boolean insert(String title,String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Util.NOTE_TITLE, title);
        cv.put(Util.NOTE_DESCRIPTION, description);

        long resultValue = db.insert(Util.TABLE_NAME,null,cv);
        if (resultValue == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public void deleteNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        int id = note.getNote_id();
        db.delete(Util.TABLE_NAME, Util.NOTE_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public int updateNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE_TITLE, note.getNote_title());
        contentValues.put(Util.NOTE_DESCRIPTION, note.getNote_description());
        return db.update(Util.TABLE_NAME, contentValues, Util.NOTE_ID+"=?", new String[]{String.valueOf(note.getNote_id())});
    }
}
