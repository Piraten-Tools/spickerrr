package de.lostincoding.spickerrr2.api;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.lostincoding.spickerrr2.model.Antrag;
import de.lostincoding.spickerrr2.model.Package;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lostincoding on 14.06.16.
 */
public class AntragsAPI {

    private String loadData(String dataUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(dataUrl)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private ArrayList<Antrag> parseJSON(String data, Package insertpackage) {
        ArrayList<Antrag> antragsliste = new ArrayList<>();
        JSONArray antragsArray = null;
        try {
            antragsArray = new JSONArray(data);
            for (int i = 0; i < 10; i++) {
                JSONObject aktobject = antragsArray.getJSONObject(i);
                String id = (String) aktobject.get(insertpackage.getColId());
                String title = (String) aktobject.get(insertpackage.getColTitle());
                String topic = (String) aktobject.get(insertpackage.getColTopic());
                String kind = (String) aktobject.get(insertpackage.getColKind());
                String owner = (String) aktobject.get(insertpackage.getColOwner());
                String infourl = (String) aktobject.get(insertpackage.getColInfoUrl());
                String abstract_short = (String) aktobject.get(insertpackage.getColAbstract());
                String description = (String) aktobject.get(insertpackage.getColDescription());
                String motivation = (String) aktobject.get(insertpackage.getColMotivation());
                antragsliste.add(new Antrag(id, title, topic, kind, owner, infourl, abstract_short, description, motivation));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return antragsliste;

    }

    private ArrayList<Antrag> parseCSV(String data,Package insertPackage) {
        ArrayList<Antrag> antragsliste = new ArrayList<>();


        return  antragsliste;
    }

    public ArrayList<Antrag> getAntraege(Package insertpackage) {
        String data = null;
        ArrayList<Antrag> antragslist = new ArrayList<>();
        try {


            data = loadData(insertpackage.getDataUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (insertpackage.getSourceType()) {
            case "JSON":
                antragslist = parseJSON(data, insertpackage);
                break;
            case "CSV":
                antragslist = parseCSV(data);
                break;
        }


        return antragslist;
    }
}
