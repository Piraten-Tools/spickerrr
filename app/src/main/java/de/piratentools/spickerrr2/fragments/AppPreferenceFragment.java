package de.piratentools.spickerrr2.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import de.piratentools.spickerrr2.R;

/**
 * Created by lostincoding on 29.06.16.
 */
public class AppPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
