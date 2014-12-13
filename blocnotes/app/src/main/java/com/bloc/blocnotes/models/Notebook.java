package com.bloc.blocnotes.models;

import android.content.ContentValues;
import android.database.Cursor;


public class Notebook extends Model {

    private String mName;
    private int mNoteCount;

    // gets and sets

    public String getName() { return mName; }
    public void setName(String name) { this.mName = name; }

    public int getNoteCount() { return mNoteCount; }
    public void setNoteCount(int noteCount) { this.mNoteCount = noteCount; }



    public Notebook(long id) {
        super("Notebooks", id);
    }

    @Override
    protected void _load(Cursor row) {

        // load the name from the cursor
        mName = row.getString(row.getColumnIndex("name"));

    }

    @Override
    protected ContentValues _getContentValues() {
        ContentValues values = new ContentValues();
        values.put("name", mName);
        return values;
    }
}
