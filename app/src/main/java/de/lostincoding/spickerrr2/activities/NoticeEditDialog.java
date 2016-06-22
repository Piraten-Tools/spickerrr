package de.lostincoding.spickerrr2.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import de.lostincoding.spickerrr2.R;

/**
 * Created by lostincoding on 22.06.16.
 */
public class NoticeEditDialog extends DialogFragment {
    private EditText mEditText;

    public interface NoticeEditedListener {
        void onFinishUserDialog(String user);
    }

    // Empty constructor required for DialogFragment
    public NoticeEditDialog() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_edit_dialog, container);
        mEditText = (EditText) view.findViewById(R.id.editText);

        // set this instance as callback for editor action
        //  mEditText.setOnEditorActionListener(this);
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Please enter username");

        return view;
    }

  /*  @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // Return input text to activity
        NoticeEditedListener activity = (UserNameListener) getActivity();
        activity.onFinishUserDialog(mEditText.getText().toString());
        this.dismiss();
        return true;
    }*/
}
