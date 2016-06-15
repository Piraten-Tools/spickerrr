package de.lostincoding.spickerrr2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import de.lostincoding.spickerrr2.api.APICaller;
import de.lostincoding.spickerrr2.api.AntragsAPI;
import de.lostincoding.spickerrr2.model.Antrag;
import de.lostincoding.spickerrr2.model.Package;

public class AntragsChooserActivity extends AppCompatActivity {
    private Package aPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_chooser);

        Intent intent = getIntent();
        aPackage = intent.getParcelableExtra("package");
        fillListView();
    }


    private void fillListView() {
        AntragsAPI api = new AntragsAPI();
        ArrayList<Antrag> antragsliste = null;
        try {
            antragsliste = api.getAntraege(aPackage);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> antragstitellist = new ArrayList<>();
        for (Antrag antrag : antragsliste) {
            antragstitellist.add(antrag.getId());
        }


        ListView listView = (ListView) findViewById(R.id.antragsListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                antragstitellist);

        listView.setAdapter(arrayAdapter);
    }
}

