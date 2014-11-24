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

        // instantiate our layout
        LinearLayout linearLayout = new LinearLayout(getActivity());


        // set the params of our layout
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // instantiate our text area
        EditText editText = new EditText(getActivity());

        // add the text area to the layout using our params
        linearLayout.addView(editText, params);

        return linearLayout;
    }

}
