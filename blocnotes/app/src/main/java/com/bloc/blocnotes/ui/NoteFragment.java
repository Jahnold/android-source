package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bloc.blocnotes.R;


public class NoteFragment extends Fragment {

    private EditText mEditText;

    public NoteFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // create a reference to our fragment view
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);

        // create a reference to our editText to use elsewhere in the class
        mEditText = (EditText) rootView.findViewById(R.id.et_note);

        // check whether our note text is saved in the bundle
        // I tried using .containsKey here but it threw a NullPointerException?
        //TODO
        if (savedInstanceState != null) {

            Log.d("matt get", savedInstanceState.getString("noteText"));

            // it is so put it back in our EditText
            mEditText.setText(savedInstanceState.getString("noteText"));
        }

        // return the view
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // run the super
        super.onSaveInstanceState(savedInstanceState);

        // check what value we're getting
        Log.d("matt set", mEditText.getText().toString());

        // add the text of our editText to the saved state
        savedInstanceState.putString("noteText", mEditText.getText().toString());

    }


}
