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

/**
 * Created by matthewarnold on 12/01/15.
 *
 */
public class AddImageDialogFragment extends DialogFragment {

    public AddImageDialogFragment() {}

    public interface AddImageListener {

        public void onAddImageConfirm(DialogFragment dialog, String imageURL);
    }

    AddImageListener mListener;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {
            mListener = (AddImageListener) activity;
        }
        catch (ClassCastException e) {
            // Activity doesn't implement interface, throw exception
            throw new ClassCastException(activity.toString() + "must implement AddImageListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {

        // create a builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // get an inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // inflate and set the view for the dialog
        builder.setView(inflater.inflate(R.layout.fragment_add_image_dialog, null));

        // set the title
        builder.setTitle(R.string.add_image_dialog_title);

        // set the buttons
        builder.setPositiveButton(R.string.add_image_dialog_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // get the new name
                EditText editText = (EditText) getDialog().findViewById(R.id.et_image_url);

                // user clicked rename, run the positive method in the calling activity
                mListener.onAddImageConfirm(AddImageDialogFragment.this, editText.getText().toString());
                //mListener.onAddImageConfirm(AddImageDialogFragment.this, "http://www.google.co.uk/images/srpr/logo11w.png");
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // user clicked cancel, do nothing
            }
        });

        return builder.create();

    }
}
