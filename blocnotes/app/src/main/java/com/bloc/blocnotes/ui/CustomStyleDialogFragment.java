package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

        // inflate the dialog box and keep a reference to it
        View view = inflater.inflate(R.layout.fragment_custom_style_dialog, container, false);

        // set the dialog title
        getDialog().setTitle("Customize View");

        // set the values in the font spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.system_font_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.system_fonts,
                android.R.layout.simple_spinner_item
        );
        spinner.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;

    }




}
