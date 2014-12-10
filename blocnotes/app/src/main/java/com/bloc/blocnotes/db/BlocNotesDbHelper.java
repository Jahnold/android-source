package com.bloc.blocnotes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bloc.blocnotes.db.tables.NotebooksTable;
import com.bloc.blocnotes.db.tables.NotesTable;
import com.bloc.blocnotes.db.tables.Table;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by matthewarnold on 08/12/14.
 */
public class BlocNotesDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BlocNotes";

    private static Set<Table> sTables = new HashSet<Table>();
    static {
        sTables.add(new NotesTable("Notes"));
        sTables.add(new NotebooksTable("Notebooks"));
    }

    public BlocNotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Iterator<Table> tables = sTables.iterator();
        while (tables.hasNext()){
            sqLiteDatabase.execSQL(tables.next().getCreateStatement());
        }

        // create an sample notebook 'uncategorized'
        ContentValues values = new ContentValues();
        values.put("name", "Uncategorized");
        long uncatId = sqLiteDatabase.insert("Notebooks", null, values);

        // for testing add some notes to the uncat notebook
        values.clear();
        values.put("text", "This is a sample note");
        values.put("notebook_id", uncatId);
        sqLiteDatabase.insert("Notes", null, values);

        values.clear();
        values.put("text", "This is not a sample note (j/k it is)");
        values.put("notebook_id", uncatId);
        sqLiteDatabase.insert("Notes", null, values);

        values.clear();
        values.put("text", "Please note...this is a sample note.");
        values.put("notebook_id", uncatId);
        sqLiteDatabase.insert("Notes", null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Iterator<Table> tables = sTables.iterator();
        while (tables.hasNext()) {
            tables.next().onUpgrade(sqLiteDatabase, oldVersion, newVersion);
        }
    }
}
