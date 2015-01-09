package com.bloc.blocnotes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bloc.blocnotes.BlocNotes;
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
        final Note note = mNotes.get(position);

        if (note != null) {

            // get refs to the TextView
            TextView text = (TextView) convertView.findViewById(R.id.txt_notebook_note);

            // assign the values from the current notebook
            text.setText(note.getText());

        }

        // set up the menu
        ImageButton threeDots = (ImageButton) convertView.findViewById(R.id.three_dots);

        final PopupMenu popupMenu = new PopupMenu(getContext(), threeDots);
        popupMenu.getMenu().add(Menu.NONE, 0, Menu.NONE, "Delete Note");
        popupMenu.getMenu().add(Menu.NONE, 1, Menu.NONE, "Remind Me");

        // set up the listener for when a user chooses a menu item
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case 0:

                        // delete note
                        ((BlocNotes) getContext()).onNoteDelete(note);
                        break;

                    case 1:

                        // remind me
                        ((BlocNotes) getContext()).onSetReminder(note);
                        break;

                }

                return false;
            }
        });

        // set up the listener for when a user clicks on the menu button
        threeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // show the popup menu
                popupMenu.show();
            }
        });

        return convertView;
    }

}
