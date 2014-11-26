package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bloc.blocnotes.R;


public class CustomStyleDialogFragment extends DialogFragment {


    public CustomStyleDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        // set the dialog title
        getDialog().setTitle("Customize View");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_style_dialog, container, false);

    }




}
