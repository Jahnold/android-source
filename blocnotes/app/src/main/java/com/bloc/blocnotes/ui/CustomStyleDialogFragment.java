package com.bloc.blocnotes.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bloc.blocnotes.BlocNotes;
import com.bloc.blocnotes.R;



public class CustomStyleDialogFragment extends DialogFragment {


    private static int mSpinnerPosition = 0;

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

        /*
        *   Font Spinner
        *   ~~~~~~~~~~~~
        */

        // set the values in the font spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.system_font_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.system_fonts,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // set a listener for when the spinner changes
        final CustomStyleDialogFragment dialogFragment = this;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                // spinner item has been selected
                // call the onFontChange method of the BlocNotes activity
                ((BlocNotes) getActivity()).onFontChange(dialogFragment, parent.getItemAtPosition(pos).toString());

                // set the mFont to keep track of the current font selection
                mSpinnerPosition = pos;

            }

            public void onNothingSelected(AdapterView<?> parent) {}

        });

        // set the spinner to whatever the current font is
        spinner.setSelection(mSpinnerPosition);

        /*
        *   Style Radio Group
        *   ~~~~~~~~~~~~~~~~~
        */

        // get a reference to our radiogroup
        RadioGroup textStyle = (RadioGroup) view.findViewById(R.id.rg_text_style);

        // set a listener
        textStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup textStyle, int checkedID ) {

                switch (checkedID) {
                    case R.id.rb_style_small:
                        ((BlocNotes) getActivity()).onStyleChange(null,"0");
                        break;
                    case R.id.rb_style_medium:
                        ((BlocNotes) getActivity()).onStyleChange(null,"1");
                        break;
                    case R.id.rb_style_large:
                        ((BlocNotes) getActivity()).onStyleChange(null,"2");
                        break;
                }

            }

        });

        // Inflate the layout for this fragment
        return view;

    }

}
