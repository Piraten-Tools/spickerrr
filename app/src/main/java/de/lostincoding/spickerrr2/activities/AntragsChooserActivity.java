package de.lostincoding.spickerrr2.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.lostincoding.spickerrr2.AntragsChooserViewPagerAdapter;
import de.lostincoding.spickerrr2.AntragsSortOptions;
import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.api.AntragsAPI;
import de.lostincoding.spickerrr2.fragments.AntragsListFragment;
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
    private ProgressDialog dialog;
    private AntragsSortOptions antragsSortOptions = AntragsSortOptions.KIND;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_chooser);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        aPackage = getIntent().getParcelableExtra("package");
        initalizeUI();
        showProgressDialog();
        loadData();
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(AntragsChooserActivity.this);
        dialog.setMessage("Lade Anträge...");
        dialog.show();
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
                antragslist = new ArrayList<>();
                try {
                    antragslist = AntragsAPI.parseData(response.body().string(), aPackage);
                } catch (JSONException e) {
                    Log.e("JSON", e.toString());
                }
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUpView();


                    }
                });

            }
        };

        AntragsAPI.loadData(aPackage, dataCallback);


    }

    public void setUpView() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                //nothing
                break;

            case R.id.menuSortKind:
                antragsSortOptions = AntragsSortOptions.KIND;
                setUpView();
                break;

            case R.id.menuSortTopic:
                antragsSortOptions = AntragsSortOptions.TOPIC;
                setUpView();
                break;

            default:
                finish();
                break;
        }

        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.antragschooser_menu, menu);
        return true;
    }

    private void initalizeUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(aPackage.getName());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        AntragsChooserViewPagerAdapter adapter = new AntragsChooserViewPagerAdapter(getSupportFragmentManager());

        HashMap<String, ArrayList<Antrag>> mapoflists = new HashMap<>();
        //for each criterion create an arrraylist and add it to the map
        //if there isnt a list for the criterion, create one
        for (Antrag antrag : antragslist) {
            String criterion;
            switch (antragsSortOptions) {
                case KIND:
                    criterion = antrag.getKind();
                    break;
                case TOPIC:
                    criterion = antrag.getTopic();
                    break;
                default:
                    criterion = antrag.getKind();
                    break;
            }

            if (mapoflists.containsKey(criterion)) {
                mapoflists.get(criterion).add(antrag);
            } else {
                ArrayList<Antrag> list = new ArrayList<>();
                list.add(antrag);
                mapoflists.put(criterion, list);
            }

        }
        //create a fragment for each list in the map
        for (Map.Entry<String, ArrayList<Antrag>> entry : mapoflists.entrySet()) {
            AntragsListFragment frag = new AntragsListFragment();
            String key = entry.getKey();
            ArrayList<Antrag> value = entry.getValue();

            //give the fragment the data over the bundle
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("antragslist", value);

            frag.setArguments(bundle);
            adapter.addFragment(frag, key);
        }


        viewPager.setAdapter(adapter);
    }


}

