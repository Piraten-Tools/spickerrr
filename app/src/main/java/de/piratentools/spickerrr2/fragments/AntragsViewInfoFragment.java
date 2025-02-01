package de.piratentools.spickerrr2.fragments;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.activities.AntragsViewActivity;
import de.piratentools.spickerrr2.model.Antrag;
import de.piratentools.spickerrr2.model.Book;
import de.piratentools.spickerrr2.model.DataHolder;
import de.piratentools.spickerrr2.model.Package;

/**
 * A simple {@link Fragment} subclass.
 */
public class AntragsViewInfoFragment extends Fragment implements AntragsViewActivity.InfoFragmentUpdateListener {
    private Antrag antrag;
    private WebView notice;
    private ImageView votePreference;

    public AntragsViewInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get Antrag from Arguments
        antrag = null;
        int position = requireActivity().getIntent().getIntExtra("position", -1);
        antrag = DataHolder.getInstance().getAntragslist().get(position);
        Book book = DataHolder.getInstance().getBook();
        Package aPackage = DataHolder.getInstance().getaPackage();

        //fill View
        View rootView = inflater.inflate(R.layout.fragment_antrags_view_info, container, false);
        WebView author = rootView.findViewById(R.id.author);
        WebView topic = rootView.findViewById(R.id.topic);
        notice = rootView.findViewById(R.id.notice);
        votePreference = rootView.findViewById(R.id.votePreference);

        author.getSettings();
        author.setBackgroundColor(Color.TRANSPARENT);

        topic.getSettings();
        topic.setBackgroundColor(Color.TRANSPARENT);

        notice.getSettings();
        notice.setBackgroundColor(Color.TRANSPARENT);


        author.loadData(antrag.owner(), "text/html; charset=UTF-8", "UTF-8");
        topic.loadData(antrag.topic(), "text/html; charset=UTF-8", "UTF-8");
        notice.loadData(antrag.getNoticePreference(), "text/html; charset=UTF-8", "UTF-8");
        // Inflate the layout for this fragment
        setVotePreferenceImage();
        return rootView;
    }

    private void setVotePreferenceImage() {
        switch (antrag.getVotePreference()) {
            case ACCEPT:
                votePreference.setImageResource(R.drawable.thumb_up);
                break;
            case ABSTENTION:
                votePreference.setImageResource(R.drawable.equal);
                break;
            case DECLINE:
                votePreference.setImageResource(R.drawable.thumb_down);
                break;
            case NOT_SET:
                votePreference.setImageResource(R.drawable.help);
                break;
        }

    }

    @Override
    public void onFragmentUpdate() {
        if ( antrag != null ) {
            notice.loadData(antrag.getNoticePreference(), "text/html; charset=UTF-8", "UTF-8");
        }
        setVotePreferenceImage();
    }

}
