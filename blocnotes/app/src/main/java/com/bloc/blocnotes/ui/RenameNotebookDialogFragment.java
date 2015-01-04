package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.bloc.blocnotes.R;



public class RenameNotebookDialogFragment extends DialogFragment {

    public RenameNotebookDialogFragment() {
        // Required empty public constructor
    }

    public interface RenameDialogListener {
        public void onRenameConfirm(DialogFragment dialog, String newName);
        //public void onDialogNegativeClick(DialogFragment dialog);
    }

    RenameDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {
            mListener = (RenameDialogListener) activity;
        }
        catch (ClassCastException e) {
            // Activity doesn't implement interface, throw exception
            throw new ClassCastException(activity.toString() + "must implement RenameDialogListener");
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {

        // create a builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // get an inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // inflate and set the view for the dialog
        builder.setView(inflater.inflate(R.layout.fragment_rename_notebook_dialog, null));

        // set the title
        builder.setTitle(R.string.rename_notebook_dialog_title);

        // set the buttons
        builder.setPositiveButton(R.string.rename_notebook_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // get the new name
                EditText editText = (EditText) getDialog().findViewById(R.id.et_notebook_name);

                // user clicked rename, run the positive method in the calling activity
                mListener.onRenameConfirm(RenameNotebookDialogFragment.this, editText.getText().toString());
            }
        });

        builder.setNegativeButton(R.string.rename_notebook_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // user clicked cancel, do nothing
            }
        });

        return builder.create();
    }

}
