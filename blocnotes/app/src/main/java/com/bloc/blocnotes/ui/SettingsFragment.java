package com.bloc.blocnotes.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bloc.blocnotes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);

        addPreferencesFromResource(R.xml.fragment_settings);
    }


}
