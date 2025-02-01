package de.piratentools.spickerrr2.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.Objects;

import de.piratentools.spickerrr2.fragments.AppPreferenceFragment;

public class AppPreferencesActivity extends AppCompatActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AppPreferenceFragment()).commit();
        setTitle("Einstellungen");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    @Override
    public boolean onPreferenceChange(@NonNull Preference preference, Object o) {
        return false;
    }
}
