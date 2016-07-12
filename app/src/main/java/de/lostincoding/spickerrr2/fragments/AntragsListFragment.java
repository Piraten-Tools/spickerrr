package de.lostincoding.spickerrr2.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import de.lostincoding.spickerrr2.ListAntragsAdapter;
import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.activities.AntragsViewActivity;
import de.lostincoding.spickerrr2.model.Antrag;
import de.lostincoding.spickerrr2.model.DataHolder;
import de.lostincoding.spickerrr2.model.ParcableIntegerArrayList;


public class AntragsListFragment extends Fragment {
    private ListView listView;
    private ArrayList<Integer> list;
    private DataHolder dataHolder;
    public AntragsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHolder = DataHolder.getInstance();
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
        intent.putExtra("antragposition", list.get(position));

        startActivity(intent);
    }

    private void fillListView() {
        //get Data from Bundle
        list = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            ParcableIntegerArrayList container = bundle.getParcelable("antragslist");
            list = container.getList();
        } else {
            list = new ArrayList<>();
        }


        //create a  with the stuff which should be displayed
        Antrag[] antragsArray = new Antrag[list.size()];
        for (int i = 0; i < list.size(); i++) {
            antragsArray[i] = dataHolder.getAntragslist().get(list.get(i));
        }

        ListAntragsAdapter antragsAdapter = new ListAntragsAdapter(getActivity(), antragsArray);

        listView.setAdapter(antragsAdapter);
    }


}