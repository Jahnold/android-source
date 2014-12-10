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

/**
 * A simple {@link Fragment} subclass.
 */
public class NotebookFragment extends Fragment {


    private int mNotebookId;                            // the id of the notebook we're showing
    private String mNotebookName;                       // the name of the notebook we're showing

    public NotebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        // Inflate the layout for this fragment
        ListView v = (ListView) inflater.inflate(R.layout.fragment_notebook, container, false);

        // open the db
        SQLiteDatabase db = BlocNotesApplication.get(getActivity()).getDB().getReadableDatabase();

        // create a cursor (record set) for the data we want
        Cursor cursor = db.query("Notes",new String[] {"_id", "text"},"notebook_id = ?", new String[] {Integer.toString(mNotebookId)} ,null,null,null);

        // use our custom notebook adapter to map the cursor to list view items
        NotebookAdapter notebookAdapter = new NotebookAdapter(getActivity(),cursor);

        // set our adapter to our list view
        v.setAdapter(notebookAdapter);

        db.close();

        // set the title of our app to the name of the notebook
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle(mNotebookName);

        return v;
    }

    /**     Must be called before the fragment is created
     *      Passes in notebook name and id
     *
     */
    public void setUp(int notebookId, String notebookName) {

        mNotebookId = notebookId;
        mNotebookName = notebookName;

    }

}
