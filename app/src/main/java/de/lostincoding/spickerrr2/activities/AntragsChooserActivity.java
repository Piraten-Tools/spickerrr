package de.lostincoding.spickerrr2.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.api.APICaller;
import de.lostincoding.spickerrr2.api.AntragsAPI;
import de.lostincoding.spickerrr2.model.Antrag;
import de.lostincoding.spickerrr2.model.Package;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AntragsChooserActivity extends AppCompatActivity {
    private Package aPackage;
    private ArrayList<Antrag> antragslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_chooser);

        Intent intent = getIntent();
        aPackage = intent.getParcelableExtra("package");
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
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fillListView(antragslist);
                    }
                });

            }
        };


        ArrayList<Antrag> antragsliste = null;

        AntragsAPI.loadData(aPackage, dataCallback);


    }

    private void fillListView(ArrayList<Antrag> antragsliste) {


        ArrayList<String> antragstitellist = new ArrayList<>();
        for (Antrag antrag : antragsliste) {
            antragstitellist.add(antrag.getId()+" "+antrag.getTitle());
        }


        ListView listView = (ListView) findViewById(R.id.antragsListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                antragstitellist);

        listView.setAdapter(arrayAdapter);


    }


}

