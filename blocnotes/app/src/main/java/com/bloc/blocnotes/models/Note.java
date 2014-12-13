package com.bloc.blocnotes.models;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by matthewarnold on 13/12/14.
 */
public class Note extends Model {

    private String mText;

    // gets and sets
    public String getText() { return mText; }
    public void setText(String text) { this.mText = text; }


    public Note(long id) {
        super("Notes", id);
    }


    @Override
    protected void _load(Cursor row) {

        // load the name from the cursor
        mText = row.getString(row.getColumnIndex("text"));

    }

    @Override
    protected ContentValues _getContentValues() {
        ContentValues values = new ContentValues();
        values.put("text", mText);
        return values;
    }
}
