package de.lostincoding.spickerrr2.activities;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.api.AntragsAPI;
import de.lostincoding.spickerrr2.model.Antrag;
import de.lostincoding.spickerrr2.model.Package;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AntragsChooserActivity extends AppCompatActivity {
    private Package aPackage;
    private ArrayList<Antrag> antragslist;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_chooser);
        aPackage = getIntent().getParcelableExtra("package");
        initalizeUI();
         loadData();
    }

    private void loadData() {
        Callback dataCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Context context = getApplicationContext();
                CharSequence text = "Beim Laden der Anträge ist ein Fehler aufgetreten!";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                antragslist=new ArrayList<>();
                try {
                    antragslist = AntragsAPI.parseData(response.body().string(), aPackage);
                } catch (JSONException e) {
                    Log.e("JSON", e.toString());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupViewPager(viewPager);
                        tabLayout.setupWithViewPager(viewPager);


                    }
                });

            }
        };

        AntragsAPI.loadData(aPackage, dataCallback);


    }

    private void initalizeUI() {
        setTitle(aPackage.getName());
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

     /*   listView = (ListView) findViewById(R.id.antragsListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openNextActivity(position);
            }
        }); */
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        AntragsListFragment frag = new AntragsListFragment();

        ArrayList<ArrayList<String>> listoflists = new ArrayList<>();

        ArrayList<String> antragstitellist = new ArrayList<>();
        for (Antrag antrag : antragslist) {
            antragstitellist.add(antrag.getId() + " " + antrag.getTitle());
        }

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("antragslist", antragstitellist);
        frag.setArguments(bundle);
        adapter.addFragment(frag,"Anträge");


        viewPager.setAdapter(adapter);
    }

    private void openNextActivity(int position) {
        Intent intent = new Intent(this, AntragsViewActivity.class);
        intent.putExtra("antrag", antragslist.get(position));
        startActivity(intent);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

