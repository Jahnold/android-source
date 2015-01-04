package com.bloc.blocnotes;

import android.app.Activity;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.bloc.blocnotes.models.Note;
import com.bloc.blocnotes.models.Notebook;
import com.bloc.blocnotes.ui.CustomStyleDialogFragment;
import com.bloc.blocnotes.ui.NewNotebookFragment;
import com.bloc.blocnotes.ui.NoteFragment;
import com.bloc.blocnotes.ui.NotebookFragment;
import com.bloc.blocnotes.ui.RenameNotebookDialogFragment;
import com.bloc.blocnotes.ui.SettingsFragment;

import java.util.HashMap;
import java.util.Map;


public class BlocNotes extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
                   RenameNotebookDialogFragment.RenameDialogListener {

    private static final String TAG = "BlocNotesActivity";

    private NavigationDrawerFragment mNavigationDrawerFragment;         // navigation draw
    private NoteFragment mNoteFragment;                                 // main note fragment
    private NotebookFragment mNotebookFragment;

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
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));


        // try and retrieve a reference to our note fragment from the fragment manager using its tag
        mNoteFragment = (NoteFragment) fm.findFragmentByTag("fragment_note");

        if (mNoteFragment == null || mNoteFragment.isDetached()) {

            // noteFragment does not yet exist in the Fragment Manager

            // instantiate one
            mNoteFragment = new NoteFragment();

            // add it to the fragment manager with a tag so we can retrieve it later
            fm.beginTransaction()
                    .add(R.id.container, mNoteFragment, "fragment_note")
                    .commit();
        }

    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

        // if user has clicked on the new notepad draw item show a toast


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
            menu.clear();

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

                // load the settings fragment
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.action_style_dialog:

                // display our customise view dialog
                CustomStyleDialogFragment dialog = new CustomStyleDialogFragment();
                dialog.show(getFragmentManager(),null);
                return true;

            case R.id.action_add_notebook:

                // display the new notebook dialog fragment
                NewNotebookFragment newNotebookDialog = new NewNotebookFragment();
                newNotebookDialog.show(getFragmentManager(),null);
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    /* Change the font size of the editview
    *
    */
    public void onStyleChange(CustomStyleDialogFragment dialog, String styeId) {

        // update the editText
        mNoteFragment.setTextAppearance(styeId);

        // update the shared prefs
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString(getString(R.string.PREF_FONT_SIZE),styeId).commit();

    }


    /* Change the font of the editview
    *
    */
    public void onFontChange(CustomStyleDialogFragment dialog, String fontPath) {


        // call the set font method of the fragment
        mNoteFragment.setFont(fontPath);

        // update the shared prefs
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString(getString(R.string.PREF_FONT_NAME), fontPath).commit();

    }

    public void onThemeChange(CustomStyleDialogFragment dialog, int themeId) {}


    public void onFinishNewNotebookDialog(final String newNotebookName) {

        new AsyncTask<Void,Void,Notebook>() {

            protected Notebook doInBackground(Void... args) {

                // create a new notebook
                Notebook notebook = new Notebook(0);
                notebook.setName(newNotebookName);
                notebook.setNoteCount(0);
                notebook.save();

                return notebook;

            }

            protected void onPostExecute(Notebook notebook) {

                // refresh the navdraw list adapter
                mNavigationDrawerFragment.onDatabaseUpdated(notebook);

            }

        }.execute();

    }

    public void onNotebookSelected(Notebook notebook) {

        // create new notebook fragment
        mNotebookFragment = new NotebookFragment();

        // pass in the id and name to the setup method
        mNotebookFragment.setUp(notebook);

        // have the fragment manager display the fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mNotebookFragment)
                .addToBackStack(null)
                .commit();

    }

    public void onNoteDelete(Note note) {

        // delete note
        note.delete();

        // update note list
        mNotebookFragment.removeNote(note);

        // show a toast confirming action

    }

    public void onNotebookDelete(Notebook notebook) {

        // delete notebook
        notebook.delete();

        // update the nav draw
        mNavigationDrawerFragment.removeNotebook(notebook);

        // show a toast confirming action

    }

    public void onNotebookRename(Notebook notebook, int postition) {

        // create dialog
        RenameNotebookDialogFragment dialog = new RenameNotebookDialogFragment();

        // pass the notebook id to the dialog
        Bundle args = new Bundle();
        args.putLong("notebookId", notebook.getId());
        args.putInt("notebookPosition", postition);
        dialog.setArguments(args);

        // show the dialog
        dialog.show(getFragmentManager(), "RenameNotebookDialogFragment");

    }

    public void onRenameConfirm(DialogFragment dialog, String newName) {

        // get the dialog bundle
        Bundle args = dialog.getArguments();

        // create a notebook object from the id
        Notebook notebook = new Notebook(args.getLong("notebookId"));

        // set the new name
        notebook.setName(newName);

        // save
        notebook.save();

        // update the nav list
        mNavigationDrawerFragment.renameNotebook(args.getInt("notebookPosition"), newName);
    }

    /* Load and set any shared preferences for font & size
    *
    */
    public void loadPrefs() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String fontName = prefs.getString(getString(R.string.PREF_FONT_NAME),"Default");
        String fontSize = prefs.getString(getString(R.string.PREF_FONT_SIZE),"1");

        // set font size BEFORE typeface otherwise it reverts to default font face
        mNoteFragment.setTextAppearance(fontSize);
        mNoteFragment.setFont(fontName);


    }

}
