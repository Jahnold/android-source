package com.bloc.blocnotes.db.tables;

import android.database.sqlite.SQLiteDatabase;


public class NotebooksTable extends Table {

    public NotebooksTable(String name) {

        super(name);

    }

    @Override
    public String getCreateStatement() {

        String q = "CREATE TABLE NoteBooks ( " +
                   "    _id INTEGER PRIMARY KEY, " +
                   "    name TEXT " +
                   ")";
        return q;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlLiteDatabase, int oldVersion, int NewVersion) {}

}
