package de.piratentools.spickerrr2.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.activities.AntragsViewActivity;
import de.piratentools.spickerrr2.model.Antrag;
import de.piratentools.spickerrr2.model.DataHolder;
import de.piratentools.spickerrr2.uielements.ListAntragsAdapter;


public class AntragsListFragment extends Fragment {
    private ListView listView;
    private List<Antrag> list;
    private DataHolder dataHolder = null;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openNextActivity(position);
            }
        });

        // Inflate the layout for this fragment
        fillListView();
        return rootView;
    }

    private void openNextActivity(int position) {
        Intent intent = new Intent(getContext(), AntragsViewActivity.class);
        Antrag antrag = list.get(position);
        int listPosition = DataHolder.getInstance().getAntragslist().indexOf(antrag);
        intent.putExtra("position", listPosition);
        startActivity(intent);
    }

    private void fillListView() {
        //get Data from DataHolder
        list = null;

        if (dataHolder == null) {
            dataHolder = DataHolder.getInstance();
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            list = dataHolder.getMapOfLists().get(bundle.getString("key"));
        } else {
            list = new ArrayList<>();
        }


        //create a  with the stuff which should be displayed
        Antrag[] antragsArray = new Antrag[list.size()];
        for (int i = 0; i < list.size(); i++) {
            antragsArray[i] = list.get(i);
        }

        ListAntragsAdapter antragsAdapter = new ListAntragsAdapter(getActivity(), antragsArray);

        listView.setAdapter(antragsAdapter);
    }


}