package com.bloc.blocnotes.ui;


import android.app.ActionBar;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.bloc.blocnotes.BlocNotesApplication;
import com.bloc.blocnotes.R;
import com.bloc.blocnotes.models.Note;
import com.bloc.blocnotes.models.NoteCentre;
import com.bloc.blocnotes.models.Notebook;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotebookFragment extends Fragment {


    private Notebook mNotebook;                         // the notebook we're expanding
    private ArrayList<Note> mNotesList;

    public NotebookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        // Inflate the layout for this fragment
        final ListView v = (ListView) inflater.inflate(R.layout.fragment_notebook, container, false);


        // because we're going to query the db start a new thread to do so
        // then use the result to create an adapter but do that on the ui thread
        new Thread() {

            @Override
            public void run() {

                super.run();

                // get an array list of the notes in this notebook
                NoteCentre nc = new NoteCentre();
                mNotesList = nc.getNotesForNotebook(mNotebook.getId());

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        // use our custom note array adapter
                        NoteArrayAdapter noteArrayAdapter = new NoteArrayAdapter(
                                getActivity(),
                                android.R.layout.simple_list_item_activated_1,
                                mNotesList
                        );

                        // set our adapter to our list view
                        v.setAdapter(noteArrayAdapter);

                    }
                });
            }

        }.start();

        // set the title of our app to the name of the notebook
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle(mNotebook.getName());

        return v;
    }

    /**     Must be called before the fragment is created
     *      Passes in notebook name and id
     *
     */
    public void setUp(Notebook notebook) {

        mNotebook = notebook;

    }

}
