package de.lostincoding.spickerrr2.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

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
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().setTitle("Lege dein Wahlverhalten fest");
        setUpImageButtons(view);

        return view;
    }

    public interface VotePreferenceSetListener {
        void onFinishUserDialog(VotePreferences preferences);
    }

    public ChooseVotePreferencesDialog() {

    }

    public void setUpImageButtons(View view) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VotePreferenceSetListener activity = (VotePreferenceSetListener) getActivity();
                switch (view.getId()) {
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


                dismiss();
            }
        };

        ImageButton accept = (ImageButton) view.findViewById(R.id.accept);
        ImageButton abstention = (ImageButton) view.findViewById(R.id.abstention);
        ImageButton decline = (ImageButton) view.findViewById(R.id.decline);
        ImageButton not_set = (ImageButton) view.findViewById(R.id.notset);

        accept.setOnClickListener(listener);
        abstention.setOnClickListener(listener);
        decline.setOnClickListener(listener);
        not_set.setOnClickListener(listener);
    }

}
