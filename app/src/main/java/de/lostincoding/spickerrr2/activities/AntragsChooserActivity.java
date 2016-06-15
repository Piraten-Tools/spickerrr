package de.lostincoding.spickerrr2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

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
    private ListView listView;

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
                try {
                    antragslist = AntragsAPI.parseData(response.body().string(), aPackage);
                } catch (JSONException e) {
                    Log.e("JSON",e.toString());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fillListView();
                    }
                });

            }
        };

        AntragsAPI.loadData(aPackage, dataCallback);


    }

    private void initalizeUI() {
        setTitle(aPackage.getName());
        listView = (ListView) findViewById(R.id.antragsListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openNextActivity(position);
            }
        });
    }

    private void openNextActivity(int position) {
        Intent intent = new Intent(this, AntragsViewActivity.class);
        intent.putExtra("antrag", antragslist.get(position));
        startActivity(intent);
    }

    private void fillListView() {


        ArrayList<String> antragstitellist = new ArrayList<>();
        for (Antrag antrag : antragslist) {
            antragstitellist.add(antrag.getId() + " " + antrag.getTitle());
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                antragstitellist);

        listView.setAdapter(arrayAdapter);


    }


}

