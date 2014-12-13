package com.bloc.blocnotes.models;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bloc.blocnotes.BlocNotesApplication;

import java.util.ArrayList;

public class NoteCentre extends ModelCentre<Note> {

    public NoteCentre() {
        super("Notebooks");
    }

    @Override
    protected Note _createModel(long id) {
        return new Note(id);
    }

    /**
     *  Return an array list with all the notes associated with a given notebook id
     *  @param notebookId The notebook to search for
     *  @return ArrayList of all the found notes
     */
    public ArrayList<Note> getNotesForNotebook(long notebookId) {

        // open the db
        SQLiteDatabase db = BlocNotesApplication.getDB().getReadableDatabase();

        // create a cursor (record set) for the data we want
        Cursor cursor = db.query(
                "Notes",
                new String[] {"_id", "text"},
                "notebook_id = ?", new String[] {String.valueOf(notebookId)} ,
                null,null,null
        );

        // grab the column numbers outside the loop
        int idColumn = cursor.getColumnIndex("_id");
        int textColumn = cursor.getColumnIndex("text");

        ArrayList<Note> notes = new ArrayList<Note>();

        while (cursor.moveToNext()) {

            // create a new note object
            Note note = new Note(cursor.getLong(idColumn));
            note.setText(cursor.getString(textColumn));

            // add it to the list
            notes.add(note);
        }

        return notes;

    }

}
