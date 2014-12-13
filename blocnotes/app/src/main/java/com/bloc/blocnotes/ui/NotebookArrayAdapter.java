package com.bloc.blocnotes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bloc.blocnotes.R;
import com.bloc.blocnotes.models.Notebook;

import java.util.ArrayList;


public class NotebookArrayAdapter extends ArrayAdapter<Notebook> {

    // the arraylist of notebooks that is being worked on
    private ArrayList<Notebook> mNotebooks;


    public NotebookArrayAdapter(Context context, int textViewResourceId, ArrayList<Notebook> items) {

        super(context, textViewResourceId, items);
        this.mNotebooks = items;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check whether the view needs inflating (it may be recycled)
        if (convertView == null) {

            convertView =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_navigation_drawer_item,null);

        }

        // get the notebook at [position] from the array list
        Notebook notebook = mNotebooks.get(position);

        if (notebook != null) {

            // get refs to the two TextViews
            TextView name = (TextView) convertView.findViewById(R.id.nav_draw_notebook_name);
            TextView count = (TextView) convertView.findViewById(R.id.nav_draw_note_count);

            // assign the values from the current notebook
            name.setText(notebook.getName());
            count.setText(String.valueOf(notebook.getNoteCount()));

        }

        return convertView;
    }
}
