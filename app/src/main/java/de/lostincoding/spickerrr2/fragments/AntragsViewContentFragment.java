package de.lostincoding.spickerrr2.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import de.lostincoding.spickerrr2.R;


public class AntragsViewContentFragment extends Fragment {
    private String content = "";
    private WebView contentWebView;

    public AntragsViewContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //get String from Arguments
        content = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            content = bundle.getString("content");
        }

        //fill View
        View rootView = inflater.inflate(R.layout.fragment_antrags_view_content, container, false);
        contentWebView = (WebView) rootView.findViewById(R.id.contentFragmentWebView);

        contentWebView.getSettings();
        contentWebView.setBackgroundColor(Color.TRANSPARENT);


        contentWebView.loadData(content, "text/html; charset=UTF-8", null);


        return rootView;
    }
}
