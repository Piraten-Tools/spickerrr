package de.lostincoding.spickerrr2.activities;

import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import de.lostincoding.spickerrr2.fragments.AppPreferenceFragment;

public class AppPreferencesActivity extends AppCompatActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AppPreferenceFragment()).commit();
        setTitle("Einstellungen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }
}
