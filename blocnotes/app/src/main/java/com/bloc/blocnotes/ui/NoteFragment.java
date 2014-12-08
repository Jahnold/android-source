package com.bloc.blocnotes.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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

import com.bloc.blocnotes.BlocNotes;
import com.bloc.blocnotes.R;


public class NoteFragment extends Fragment {

    private static final String KEY_INDEX  = "noteText";
    public EditText mEditText;

    private Typeface mHelvetica;
    private Typeface mHelveticaNeue;
    private Typeface mImpact;

    public NoteFragment() {


    }

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);

        // tell android that this fragment has a menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        // create a reference to our fragment view
        View v = inflater.inflate(R.layout.fragment_note, container, false);

        // create a reference to our editText to use elsewhere in the class
        mEditText = (EditText) v.findViewById(R.id.et_note);


        // load shared prefs
        //((BlocNotes) getActivity()).loadPrefs();

        mHelvetica = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Helvetica_Reg.ttf");
        mHelveticaNeue = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue_Lt.ttf");
        mImpact = Typeface.createFromAsset(getActivity().getAssets(), "fonts/impact.ttf");

        // return the view
        return v;
    }


    @Override
    public void onResume() {

        super.onResume();

        ((BlocNotes) getActivity()).loadPrefs();

    }

    /*
        save the fragment state
    */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // run the super
        super.onSaveInstanceState(savedInstanceState);

    }

    /*
    *   Add the menu items specific to our fragment to the actionbar
    */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        if (menu.findItem(R.id.action_erase_note) == null) {

            inflater.inflate(R.menu.note_fragment,menu);

        }

        super.onCreateOptionsMenu(menu,inflater);

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

    /*  Change the font of the EditText to whichever font is passed in
    *
    *   @param font the name of the chosen font
    */
    public void setFont(String font) {

        EditText editText = (EditText) getView().findViewById(R.id.et_note);


        if (font.equals("Helvetica")) {
            editText.setTypeface(mHelvetica);
        }
        else if (font.equals("Helvetica-Neue")) {
            editText.setTypeface(mHelveticaNeue);
        }
        else if (font.equals("Impact")) {
            editText.setTypeface(mImpact);
        }
        else {
            editText.setTypeface(Typeface.DEFAULT);
        }

    }

    public void setTextAppearance(String size) {

        switch (Integer.parseInt(size)) {
            case 0:
                mEditText.setTextAppearance(getActivity(), android.R.style.TextAppearance_Small);
                break;
            case 1:
                mEditText.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium);
                break;
            case 2:
                mEditText.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
                break;
        }
    }


}
