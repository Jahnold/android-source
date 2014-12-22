package com.bloc.blocnotes.ui;


import android.app.ActionBar;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        // Inflate the layout for this fragment
        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_notebook, container, false);

        // get refs to the list view and empty view
        final ListView notesListView = (ListView) v.findViewById(R.id.list_notes);
        final LinearLayout emptyNotesListView = (LinearLayout) v.findViewById(R.id.empty_notebook);

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

                        // set the empty view
                        notesListView.setEmptyView(emptyNotesListView);

                        // set our adapter to our list view
                        notesListView.setAdapter(noteArrayAdapter);

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
