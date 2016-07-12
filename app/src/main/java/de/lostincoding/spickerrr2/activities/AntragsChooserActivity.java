package de.lostincoding.spickerrr2.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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

import de.lostincoding.spickerrr2.AntragsSortOptions;
import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.SpickerrrViewPager;
import de.lostincoding.spickerrr2.api.AntragsAPI;
import de.lostincoding.spickerrr2.fragments.AntragsListFragment;
import de.lostincoding.spickerrr2.model.Antrag;
import de.lostincoding.spickerrr2.model.DataHolder;
import de.lostincoding.spickerrr2.model.ParcableIntegerArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AntragsChooserActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressDialog dialog;
    private AntragsSortOptions antragsSortOptions = AntragsSortOptions.KIND;
    private DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_chooser);
        dataHolder = DataHolder.getInstance();
        initalizeUI();

        if (dataHolder.getAntragslist() == null) {
            showProgressDialog();
            loadData();
        } else {
            setUpView();
        }

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
                ArrayList<Antrag> antragslist = new ArrayList<>();
                try {
                    antragslist = AntragsAPI.parseData(response.body().string(), dataHolder.getaPackage());
                } catch (JSONException e) {
                    Log.e("JSON", e.toString());
                }
                dataHolder.setAntragslist(antragslist);
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUpView();


                    }
                });

            }
        };

        AntragsAPI.loadData(dataHolder.getaPackage(), dataCallback);


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
        setTitle(dataHolder.getaPackage().getName());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        SpickerrrViewPager adapter = new SpickerrrViewPager(getSupportFragmentManager());

        HashMap<String, ArrayList<Integer>> mapoflists = new HashMap<>();
        //for each criterion create an arrraylist and add it to the map
        //if there isnt a list for the criterion, create one
        int counter = 0;
        for (Antrag antrag : dataHolder.getAntragslist()) {
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
                mapoflists.get(criterion).add(new Integer(counter));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(new Integer(counter));
                mapoflists.put(criterion, list);
            }
            counter++;
        }
        //create a fragment for each list in the map
        for (Map.Entry<String, ArrayList<Integer>> entry : mapoflists.entrySet()) {
            AntragsListFragment frag = new AntragsListFragment();
            String key = entry.getKey();
            ParcableIntegerArrayList container = new ParcableIntegerArrayList();
            container.setList(entry.getValue());

            //give the fragment the data over the bundle
            Bundle bundle = new Bundle();
            bundle.putParcelable("antragslist", container);

            frag.setArguments(bundle);
            adapter.addFragment(frag, key);
        }


        viewPager.setAdapter(adapter);
    }


}

