package com.project.app.cryptotracker.Dashboard;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        // clear database preference
        Preference clearDatabasePreference = findPreference("clear_database");
        if (clearDatabasePreference != null) {
            clearDatabasePreference.setOnPreferenceClickListener(preference -> {
                new MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Clear Database")
                        .setMessage("Are you sure you want to delete all data from the database?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            clearDatabase();
                            Toast.makeText(requireContext(), "Database cleared", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                        })
                        .show();
                return true;
            });
        }
    } //: end of onCreatePreferences


    //clear the database
    private void clearDatabase() {
        CryptoDatabase cryptoDatabase = new CryptoDatabase(requireContext());
        SQLiteDatabase sqLiteDatabase = cryptoDatabase.getWritableDatabase();
        sqLiteDatabase.delete(CryptoDatabase.COIN_TABLE, null, null);
        sqLiteDatabase.delete(CryptoDatabase.FAVORITE_TABLE, null, null);
        sqLiteDatabase.delete(CryptoDatabase.INVESTMENT_TABLE, null, null);
        sqLiteDatabase.close();
    }

}