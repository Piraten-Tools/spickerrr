package de.lostincoding.spickerrr2.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.VotePreferences;

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
        getDialog().setTitle("Lege dein Wahlverhalten fest");

        return view;
    }

    public interface VotePreferenceSetListener {
        void onFinishUserDialog(VotePreferences preferences);
    }

    public void setVotePreference(View v) {

        VotePreferenceSetListener activity = (VotePreferenceSetListener) getActivity();
        switch (v.getId()) {
            case R.id.accept:
                activity.onFinishUserDialog(VotePreferences.ACCEPT);
                break;
            case R.id.abstention:
                activity.onFinishUserDialog(VotePreferences.ABSTENTION);
                break;
            case R.id.decline:
                activity.onFinishUserDialog(VotePreferences.DECLINE);
                break;
            case R.id.notset:
                activity.onFinishUserDialog(VotePreferences.NOT_SET);
                break;
        }

        this.dismiss();
    }

}
