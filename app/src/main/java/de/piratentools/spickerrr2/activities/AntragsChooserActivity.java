package de.piratentools.spickerrr2.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Objects;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.api.AntragsAPI;
import de.piratentools.spickerrr2.fragments.AntragsListFragment;
import de.piratentools.spickerrr2.model.Antrag;
import de.piratentools.spickerrr2.model.AntragsSortOptions;
import de.piratentools.spickerrr2.model.DataHolder;
import de.piratentools.spickerrr2.model.Package;
import de.piratentools.spickerrr2.uielements.SpickerrrViewPager;
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

    private DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_chooser);
        dataHolder = DataHolder.getInstance();
        aPackage = dataHolder.getaPackage();
        AntragsSortOptions sortOptions = (AntragsSortOptions) getIntent().getSerializableExtra("antragsSortOptions");
        if (sortOptions != null) {
            antragsSortOptions = sortOptions;
        }
        initializeUI();
        showProgressDialog();
        loadData();
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(AntragsChooserActivity.this);
        dialog.setMessage(getString(R.string.loading_motions));
        dialog.show();
    }

    private void loadData() {
        Callback dataCallback = new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.error_loading_motions);
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }

            @Override
            public void onResponse(@NonNull Call call, Response response) throws IOException {
                Context context = getApplicationContext();
                antragslist = new ArrayList<>();

                try {
                    antragslist = AntragsAPI.parseData(Objects.requireNonNull(response.body()).string(), aPackage, context);
                } catch (JSONException e) {
                    Log.e("JSON", e.toString());
                }
                dataHolder.setAntragslist(antragslist);
                dialog.dismiss();
                runOnUiThread(AntragsChooserActivity.this::setUpView);

            }
        };

        if (dataHolder.getAntragslist() == null) {
            AntragsAPI.loadData(aPackage, dataCallback);
        } else {
            dialog.dismiss();
            antragslist = new ArrayList<>();
            antragslist.addAll(dataHolder.getAntragslist());
            runOnUiThread(this::setUpView);
        }


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
                restartView();
                break;

            case R.id.menuSortTopic:
                antragsSortOptions = AntragsSortOptions.TOPIC;
                restartView();
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

    private void initializeUI() {
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(aPackage.name());
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        SpickerrrViewPager adapter = new SpickerrrViewPager(getSupportFragmentManager());

        HashMap<String, ArrayList<Antrag>> mapoflists = new HashMap<>();
        dataHolder.setMapOfLists(mapoflists);
        //for each criterion create an arrraylist and add it to the map
        //if there isn't a list for the criterion, create one
        for (Antrag antrag : antragslist) {
            String criterion;
            if (Objects.requireNonNull(antragsSortOptions) == AntragsSortOptions.TOPIC) {
                criterion = antrag.topic();
            } else {
                criterion = antrag.kind();
            }

            if (mapoflists.containsKey(criterion)) {
                Objects.requireNonNull(mapoflists.get(criterion)).add(antrag);
            } else {
                ArrayList<Antrag> list = new ArrayList<>();
                list.add(antrag);
                mapoflists.put(criterion, list);
            }

        }

        //create a fragment for each list in the map
        for (Map.Entry<String, ArrayList<Antrag>> entry : mapoflists.entrySet()) {
            Bundle bundle = new Bundle();

            AntragsListFragment frag = new AntragsListFragment();
            String key = entry.getKey();

            bundle.putString("key", key);
            frag.setArguments(bundle);
            adapter.addFragment(frag, key);
        }


        viewPager.setAdapter(adapter);
    }

    private void restartView() {
        finish();
        Intent intent = new Intent(this, AntragsChooserActivity.class);
        intent.putExtra("antragsSortOptions", antragsSortOptions);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}

