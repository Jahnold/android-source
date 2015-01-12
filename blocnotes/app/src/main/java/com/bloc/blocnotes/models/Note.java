package com.bloc.blocnotes.models;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by matthewarnold on 13/12/14.
 *
 */
public class Note extends Model {

    private String mText;
    private long mNotebookId;
    private String mImageURL;

    // gets and sets
    public String getText() { return mText; }
    public void setText(String text) { this.mText = text; }

    public long getNotebookId() { return  mNotebookId; }
    public void  setNotebookId(long id) { this.mNotebookId = id; }

    public String getImageURL() { return  mImageURL; }
    public void setImageURL(String iURL) { this.mImageURL = iURL;}

    public Note(long id) {
        super("Notes", id);
    }


    @Override
    protected void _load(Cursor row) {

        // load the name from the cursor
        mText = row.getString(row.getColumnIndex("text"));
        mNotebookId = row.getLong(row.getColumnIndex("notebook_id"));
        mImageURL = row.getString(row.getColumnIndex("image_url"));

    }

    @Override
    protected ContentValues _getContentValues() {
        ContentValues values = new ContentValues();
        values.put("text", mText);
        values.put("notebook_id", mNotebookId);
        return values;
    }
}
