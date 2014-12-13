package com.bloc.blocnotes.models;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bloc.blocnotes.BlocNotesApplication;

import java.util.ArrayList;

public class NotebookCentre extends ModelCentre<Notebook> {

    public NotebookCentre() {
        super("Notebooks");
    }

    @Override
    protected Notebook _createModel(long id) {
        return new Notebook(id);
    }

    public ArrayList<Notebook> getAllNotebooks() {

        // open the db
        SQLiteDatabase db = BlocNotesApplication.getDB().getReadableDatabase();

        String query = "SELECT _id, name, count FROM Notebooks "
                    +  "LEFT JOIN "
                    +  "( "
                    +  "    SELECT notebook_id, COUNT(notebook_id) AS count FROM Notes "
                    +  "    GROUP BY notebook_id "
                    +  ") "
                    +  "ON _id = notebook_id; "
                ;

        // create a cursor (recordset) for the data we want
        Cursor cursor = db.rawQuery(query, null);

        // work out which column is which once rather than in the loop
        int idColumn = cursor.getColumnIndex("_id");
        int nameColumn = cursor.getColumnIndex("name");
        int countColumn = cursor.getColumnIndex("count");

        // transfer the data into an array list of notebooks
        ArrayList<Notebook> notebookList = new ArrayList<Notebook>();

        // loop through the cursor instantiating objects for each result and add it to our array list
        while (cursor.moveToNext()) {

            // if we were using the funny modelCentre.get business we could use the line below
            // but this would hit the db again and just retrieve info from the notebook table
            // as we've also loaded a note count there's no point in using it

            // Notebook notebook = (Notebook) get(cursor.getLong(idColumn), false);

            // create a new notebook
            Notebook notebook = new Notebook(cursor.getLong(idColumn));
            notebook.setName(cursor.getString(nameColumn));
            notebook.setNoteCount(cursor.getInt(countColumn));

            notebookList.add(notebook);
        }

        cursor.close();
        db.close();

        return notebookList;

    }
}
