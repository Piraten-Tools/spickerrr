package de.lostincoding.spickerrr2.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import de.lostincoding.spickerrr2.R;


public class AntragsListFragment extends Fragment {
    private ListView listView;

    public AntragsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_antragslist, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);


        // Inflate the layout for this fragment
        fillListView();
        return rootView;
    }

    private void fillListView() {
        ArrayList<String> list = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            list = bundle.getStringArrayList("antragslist");
        }else {
            list=new ArrayList<>();
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext().getApplicationContext(),
                R.layout.antragslist_item_layout,
                list);

        listView.setAdapter(arrayAdapter);
    }

}