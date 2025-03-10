package de.piratentools.spickerrr2.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import de.piratentools.spickerrr2.R;


public class AntragsViewContentFragment extends Fragment {

    public AntragsViewContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //get String from Arguments
        String content = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            content = bundle.getString("content");
        }

        //fill View
        View rootView = inflater.inflate(R.layout.fragment_antrags_view_content, container, false);
        WebView contentWebView = rootView.findViewById(R.id.contentFragmentWebView);

        contentWebView.getSettings();
        contentWebView.setBackgroundColor(Color.TRANSPARENT);


        contentWebView.loadData(content, "text/html; charset=UTF-8", null);


        return rootView;
    }

}
