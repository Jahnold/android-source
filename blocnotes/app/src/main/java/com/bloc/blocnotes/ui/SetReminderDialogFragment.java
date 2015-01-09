package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.bloc.blocnotes.R;

import java.util.ArrayList;

/**
 * Created by matthewarnold on 06/01/15.
 */
public class SetReminderDialogFragment extends DialogFragment {

    public SetReminderDialogFragment() {}

    public interface SetReminderListener {

        public void onSetReminderConfirm(DialogFragment dialog, int time);

    }

    SetReminderListener mListener;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {
            mListener = (SetReminderListener) activity;
        }
        catch (ClassCastException e) {
            // Activity doesn't implement interface, throw exception
            throw new ClassCastException(activity.toString() + "must implement SetReminderListener");
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {


        // inflate our view
        View view = inflater.inflate(R.layout.fragment_set_reminder_dialog, container, false);

        // set the title
        getDialog().setTitle(R.string.reminder_dialog_title);

        // get a ref to the listview
        ListView timesList = (ListView) view.findViewById(R.id.list_notify_time);

        // create an array adapter for the times
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.reminder_times,
                android.R.layout.simple_list_item_single_choice
        );

        // introduce our listview to our adapter
        timesList.setAdapter(adapter);

        // create a click listener for the listview
        timesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                mListener.onSetReminderConfirm(SetReminderDialogFragment.this, position);
                getDialog().dismiss();
            }
        });

        return view;
    }



}
