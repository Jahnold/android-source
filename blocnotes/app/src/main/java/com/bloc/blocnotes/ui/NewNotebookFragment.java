package com.bloc.blocnotes.ui;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.bloc.blocnotes.BlocNotes;
import com.bloc.blocnotes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewNotebookFragment extends DialogFragment implements TextView.OnEditorActionListener {


    private EditText mEditText;

    public NewNotebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_notebook, container, false);

        // set the dialog title
        getDialog().setTitle(getString(R.string.new_notebook_dialog_title));

        // get a ref to the edit text
        mEditText = (EditText)  view.findViewById(R.id.et_notebook_name);

        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        mEditText.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (EditorInfo.IME_ACTION_DONE == actionId) {

            // user clicked the 'done' action on the soft keyboard
            // return the new notebook name to the activity
            ((BlocNotes) getActivity()).onFinishNewNotebookDialog(mEditText.getText().toString());

            // close the dialog
            this.dismiss();

            return true;

        }

        return false;

    }

}
