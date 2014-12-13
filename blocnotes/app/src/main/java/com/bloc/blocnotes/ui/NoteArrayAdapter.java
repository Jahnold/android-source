package com.bloc.blocnotes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bloc.blocnotes.R;
import com.bloc.blocnotes.models.Note;

import java.util.ArrayList;


public class NoteArrayAdapter extends ArrayAdapter<Note>{

    // the arraylist of notebooks that is being worked on
    private ArrayList<Note> mNotes;


    public NoteArrayAdapter(Context context, int textViewResourceId, ArrayList<Note> items) {

        super(context, textViewResourceId, items);
        this.mNotes = items;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check whether the view needs inflating (it may be recycled)
        if (convertView == null) {

            convertView =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_notebook_item,null);

        }

        // get the notebook at [position] from the array list
        Note note = mNotes.get(position);

        if (note != null) {

            // get refs to the two TextViews
            TextView text = (TextView) convertView.findViewById(R.id.txt_notebook_note);

            // assign the values from the current notebook
            text.setText(note.getText());

        }

        return convertView;
    }

}
