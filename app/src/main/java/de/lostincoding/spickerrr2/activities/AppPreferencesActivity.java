package de.lostincoding.spickerrr2.activities;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import de.lostincoding.spickerrr2.R;

/**
 * Created by lostincoding on 29.06.16.
 */
public class AppPreferencesActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener

{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        return false;
    }
}
