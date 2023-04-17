package com.project.app.cryptotracker.Dashboard;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.project.app.cryptotracker.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}