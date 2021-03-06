package com.bloc.blocnotes.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bloc.blocnotes.BlocNotesApplication;


public abstract class Model {

    protected long mId;                 // the model id
    protected String mTableName;        // the name of the table
    protected boolean mLoaded;          // has the model been loaded

    // partial constructor
    public Model(String table, long id) {

        // run the full constructor default lazy to false
        this(table, id, false);

    }

    // full constructor
    public Model(String table, long id, boolean lazy) {

        mTableName = table;
        mId = id;
        if (!lazy) {
            load();
        }

    }


    public boolean isLoaded() {

        return mLoaded;
    }

    protected void setLoaded(boolean loaded) {

        mLoaded = loaded;
    }

    public final void load() {

        // first check that it's not already loaded
        if (isLoaded()) {
            return;
        }

        // or that it has an id of 0
        if (mId == 0) {
            return;
        }

        // load a single row from the table which matches the id
        SQLiteDatabase db = BlocNotesApplication.getDB().getReadableDatabase();
        Cursor row = db.query(
                true,                                       // DISTINCT
                mTableName,                                 // FROM
                null,                                       // SELECT (*)
                "_id=?",                                    // WHERE
                new String[] {String.valueOf(getId())},     // values
                null,
                null,
                null,
                "1"                                         // LIMIT
        );
        row.moveToFirst();
        _load(row);
        row.close();
        setLoaded(true);
    }

    /**
     *  Save the contents of the current object to the db
     *  If it already has an ID then do an update
     *  If not create a new entry
    */
    public final void save() {

        ContentValues values = _getContentValues();
        SQLiteDatabase db = BlocNotesApplication.getDB().getWritableDatabase();

        // if the id == 0 then this is a new model
        // if not then it's an update
        if (mId == 0) {

            // new model, do insert and capture the new id
            mId = db.insert(
                    mTableName,
                    null,
                    values
            );

        }
        else {

            // updated
            db.update(
                    mTableName,
                    values,
                    "_id = ? ",
                    new String[] {String.valueOf(mId)}
            );
        }

        db.close();

    }

    /**
     *  Delete the entry which corresponds to this object from the db
    */
    public final void delete() {

        // first check we have a valid ID
        if (mId != 0) {

            SQLiteDatabase db = BlocNotesApplication.getDB().getWritableDatabase();
            db.delete(mTableName, "_id = ?", new String[] {String.valueOf(mId)});

            db.close();
        }

    }

    public final long getId() {

        return mId;

    }

    protected abstract void _load(Cursor row);

    protected abstract ContentValues _getContentValues();

}
