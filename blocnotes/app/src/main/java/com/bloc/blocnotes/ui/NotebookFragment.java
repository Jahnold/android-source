package com.bloc.blocnotes.ui;


import android.app.ActionBar;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bloc.blocnotes.BlocNotes;
import com.bloc.blocnotes.R;
import com.bloc.blocnotes.models.Note;
import com.bloc.blocnotes.models.NoteCentre;
import com.bloc.blocnotes.models.Notebook;

import java.util.ArrayList;

/**
 * Fragment shows a list of all notes in a notebook
 */
public class NotebookFragment extends Fragment {


    private Notebook mNotebook;                         // the notebook we're expanding
    private ArrayList<Note> mNotesList;
    private NoteArrayAdapter mNoteArrayAdapter;

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
                        mNoteArrayAdapter = new NoteArrayAdapter(
                                getActivity(),
                                android.R.layout.simple_list_item_activated_1,
                                mNotesList
                        );

                        // set the empty view
                        notesListView.setEmptyView(emptyNotesListView);

                        // set our adapter to our list view
                        notesListView.setAdapter(mNoteArrayAdapter);

                        // add a listener for when a user clicks a new
                        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                // pass the note to the method in the activity
                                ((BlocNotes) getActivity()).onEditNote(mNoteArrayAdapter.getItem(i));

                            }
                        });

                    }
                });
            }

        }.start();

        // set the title of our app to the name of the notebook
        //ActionBar actionBar = getActivity().getActionBar();
        //actionBar.setTitle(mNotebook.getName());

        // add a listener to the new note button
        Button newNote = (Button) v.findViewById(R.id.btn_new_note);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((BlocNotes) getActivity()).onNewNote(mNotebook.getId());

            }
        });



        return v;
    }

    /**     Must be called before the fragment is created
     *      Passes in notebook name and id
     *
     */
    public void setUp(Notebook notebook) {

        mNotebook = notebook;

    }

    public void removeNote(Note note) {

        mNoteArrayAdapter.remove(note);

    }

    public void refresh() {

        mNoteArrayAdapter.notifyDataSetChanged();

    }

}
