package de.lostincoding.spickerrr2.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import de.lostincoding.spickerrr2.R;

/**
 * Created by lostincoding on 22.06.16.
 */
public class ChooseVotePreferencesDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vote_preference_dialog, container);


        // set this instance as callback for editor action
        //  mEditText.setOnEditorActionListener(this);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Lege Wahlverhalten fest");

        return view;
    }
}
