package com.bloc.blocnotes.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.bloc.blocnotes.R;


public class NotebookAdapter extends CursorAdapter {


    public NotebookAdapter(Context context, Cursor cursor) {

        super(context,cursor,0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        // inflate an empty notebook item and return it
        return LayoutInflater.from(context).inflate(R.layout.fragment_notebook_item,viewGroup,false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // bind our data to one of the empty notebook items which was created in newView
        TextView notebookNote = (TextView) view.findViewById(R.id.txt_notebook_note);
        notebookNote.setText(cursor.getString(cursor.getColumnIndex("text")));

    }

}
