package de.lostincoding.spickerrr2.api;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import de.lostincoding.spickerrr2.model.Antrag;
import de.lostincoding.spickerrr2.model.Package;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lostincoding on 14.06.16.
 */
public class AntragsAPI {

    public static void loadData(Package data, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(data.getDataUrl())
                .build();

        client.newCall(request).enqueue(callback);

    }

    private static ArrayList<Antrag> parseJSON(String data, Package insertpackage) throws JSONException {
        ArrayList<Antrag> antragsliste = new ArrayList<>();
        JSONArray antragsArray = null;

        antragsArray = new JSONArray(data);
        for (int i = 0; i < 10; i++) {
            JSONObject aktobject = antragsArray.getJSONObject(i);
            String id = (String) aktobject.get(insertpackage.getColId());
            String title = (String) aktobject.get(insertpackage.getColTitle());
            String topic = "";//(String) aktobject.get(insertpackage.getColTopic());
            String kind = (String) aktobject.get(insertpackage.getColKind());
            String owner = (String) aktobject.get(insertpackage.getColOwner());
            String infourl = (String) aktobject.get(insertpackage.getColInfoUrl());
            String abstract_short = "";//(String) aktobject.get(insertpackage.getColAbstract());
            String description = (String) aktobject.get(insertpackage.getColDescription());
            String motivation = (String) aktobject.get(insertpackage.getColMotivation());
            antragsliste.add(new Antrag(id, title, topic, kind, owner, infourl, abstract_short, description, motivation));
        }

        return antragsliste;

    }

    private static ArrayList<Antrag> parseCSV(String data, Package insertPackage) throws IOException {
        ArrayList<Antrag> antragsliste = new ArrayList<>();
        CSVReader reader = new CSVReader(new StringReader(data), insertPackage.getCsvSeperator().charAt(0), insertPackage.getCsvSeperator().charAt(0));
        ArrayList<String[]> columnlist = new ArrayList<>();
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            columnlist.add(nextLine);
        }


        return antragsliste;
    }

    public static ArrayList<Antrag> parseData(String data, Package insertpackage) throws JSONException, IOException {

        ArrayList<Antrag> antragslist = new ArrayList<>();

        switch (insertpackage.getSourceType()) {
            case "JSON":
                antragslist = parseJSON(data, insertpackage);
                break;
            case "CSV":
                antragslist = parseCSV(data, insertpackage);
                break;
        }


        return antragslist;
    }
}
