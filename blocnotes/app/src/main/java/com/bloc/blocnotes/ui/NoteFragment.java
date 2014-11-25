package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bloc.blocnotes.R;


public class NoteFragment extends Fragment {

    private static final String KEY_INDEX  = "noteText";
    protected EditText mEditText;

    public NoteFragment() {


    }

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);

        // tell android that this fragment has a menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // create a reference to our fragment view
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);

        // create a reference to our editText to use elsewhere in the class
        mEditText = (EditText) rootView.findViewById(R.id.et_note);

        // check whether our note text is saved in the bundle
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_INDEX)) {

            // it is so put it back in our EditText
            mEditText.setText(savedInstanceState.getString(KEY_INDEX));
        }

        // return the view
        return rootView;
    }

    /*
        save the state of our editText into the bundle
        so that it is not lost in between screen rotations, etc
    */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // run the super
        super.onSaveInstanceState(savedInstanceState);

        // add the text of our editText to the saved state
        savedInstanceState.putString(KEY_INDEX, mEditText.getText().toString());

    }

    /*
    *   Add the menu items specific to our fragment to the actionbar
    */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu,inflater);

        // inflate my menu for this fragment
        inflater.inflate(R.menu.note_fragment,menu);

    }

    /*
    *   This is where the menu item events are caught
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.action_erase_note:

                // clear any text currently in the edittext
                mEditText.setText("");
                return true;

            default:

                return super.onOptionsItemSelected(menuItem);
        }


    }

}
