package com.bloc.blocnotes;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bloc.blocnotes.ui.CustomStyleDialogFragment;
import com.bloc.blocnotes.ui.NoteFragment;


public class BlocNotes extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String TAG = "BlocNotesActivity";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private NoteFragment mNoteFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_notes);


        // get a reference to the fragment manager
        FragmentManager fm = getFragmentManager();

        mNavigationDrawerFragment = (NavigationDrawerFragment) fm.findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout)
        );


        // try and retrieve a reference to our note fragment from the fragment manager
        Fragment noteFragment = fm.findFragmentById(R.id.fragment_note);

        if (noteFragment == null || noteFragment.isDetached()) {

            // noteFragment does not yet exist in the Fragment Manager

            // instantiate one
            noteFragment = new NoteFragment();

            // add it to the fragment manager
            fm.beginTransaction()
                    .add(R.id.container, noteFragment)
                    .commit();
        }


    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

        // if user has clicked on the new notepad draw item show a toast


    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.action_add_notebook);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.bloc_notes, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {

            case R.id.action_settings:

                // does nothing, just return true
                return true;

            case R.id.action_style_dialog:

                // display our customise view dialog
                CustomStyleDialogFragment dialog = new CustomStyleDialogFragment();
                dialog.show(getFragmentManager(),null);
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }


    public void onStyleChange(CustomStyleDialogFragment dialog, int styeId) {}

    public void onFontChange(CustomStyleDialogFragment dialog, String fontName) {}

    public void onThemeChange(CustomStyleDialogFragment dialog, int themeId) {}

}
