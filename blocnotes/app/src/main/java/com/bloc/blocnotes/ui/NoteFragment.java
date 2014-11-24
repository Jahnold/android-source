package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bloc.blocnotes.R;


public class NoteFragment extends Fragment {

    public NoteFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // create a reference to our fragment view
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);

        // it said to create a reference to our edit text...doesn't explain why
        EditText editText = (EditText) rootView.findViewById(R.id.et_note);

        // return the view
        return rootView;
    }

}
