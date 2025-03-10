package de.piratentools.spickerrr2.fragments;

import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import de.piratentools.spickerrr2.R;

/**
 * Created by lostincoding on 22.06.16.
 */
public class NoticeEditDialog extends DialogFragment implements View.OnClickListener {
    private EditText mEditText;


    public interface NoticeEditedListener {
        void onFinishUserDialog(String user);
    }

    // Empty constructor required for DialogFragment
    public NoticeEditDialog() {

    }

    @Override
    public void onResume() {
        //make fragment bigger
        // Get existing layout params for the window
        WindowManager.LayoutParams params = Objects.requireNonNull(getDialog().getWindow()).getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(params);
        // Call super onResume after sizing
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notice_edit_dialog, container);
        mEditText = view.findViewById(R.id.editText);
        Button save = view.findViewById(R.id.saveButton);


        mEditText.requestFocus();

        Bundle arguments = getArguments();
        if (arguments != null) {
            mEditText.setText(arguments.getString("notice"));
        }

        save.setOnClickListener(this);

        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(R.string.edit_notice);


        return view;
    }


    @Override
    public void onClick(View view) {
        // Return input text to activity
        NoticeEditedListener activity = (NoticeEditedListener) getActivity();
        activity.onFinishUserDialog(mEditText.getText().toString());
        this.dismiss();
    }

}
