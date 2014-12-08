package com.bloc.blocnotes;

import android.app.Application;
import android.content.Context;

import com.bloc.blocnotes.db.BlocNotesDbHelper;

/**
 * Created by matt on 08/12/14.
 */

public class BlocNotesApplication extends Application {

    private static BlocNotesDbHelper mDB;

    public BlocNotesApplication() {}

    @Override
    public void onCreate() {

        // set up our singleton database helper instance
        mDB = new BlocNotesDbHelper(getApplicationContext());

    }

    // getter for the database helper
    public BlocNotesDbHelper getDB() {

        return mDB;
    }

    // getter for the context
    public static BlocNotesApplication get(Context context) {

        return (BlocNotesApplication) context.getApplicationContext();

    }
}
