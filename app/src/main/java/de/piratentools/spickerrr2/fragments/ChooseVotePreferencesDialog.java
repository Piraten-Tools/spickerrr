package de.piratentools.spickerrr2.fragments;

import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.Objects;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.model.VotePreference;

/**
 * Created by lostincoding on 22.06.16.
 */
public class ChooseVotePreferencesDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.vote_preference_dialog, container);
        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().setTitle(R.string.edit_vote);
        setUpImageButtons(view);

        return view;
    }

    public ChooseVotePreferencesDialog() {

    }

    public void setUpImageButtons(View view) {
        View.OnClickListener listener = view1 -> {
            VotePreferenceSetListener activity = (VotePreferenceSetListener) getActivity();
            switch (view1.getId()) {
                case R.id.accept:
                    activity.onFinishUserDialog(VotePreference.ACCEPT);
                    break;
                case R.id.abstention:
                    activity.onFinishUserDialog(VotePreference.ABSTENTION);
                    break;
                case R.id.decline:
                    activity.onFinishUserDialog(VotePreference.DECLINE);
                    break;
                case R.id.notset:
                    activity.onFinishUserDialog(VotePreference.NOT_SET);
                    break;
            }


            dismiss();
        };

        ImageButton accept = view.findViewById(R.id.accept);
        ImageButton abstention = view.findViewById(R.id.abstention);
        ImageButton decline = view.findViewById(R.id.decline);
        ImageButton not_set = view.findViewById(R.id.notset);

        accept.setOnClickListener(listener);
        abstention.setOnClickListener(listener);
        decline.setOnClickListener(listener);
        not_set.setOnClickListener(listener);
    }

    public interface VotePreferenceSetListener {
        void onFinishUserDialog(VotePreference preferences);
    }

}
