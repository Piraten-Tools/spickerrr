package de.lostincoding.spickerrr2.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.lang3.StringEscapeUtils;
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
        for (int i = 0; i < antragsArray.length(); i++) {
            JSONObject aktobject = antragsArray.getJSONObject(i);
            //i have to now if the object is null if i want to cast to string so i use proxyobjects to check this
            String id = aktobject.isNull(insertpackage.getColId()) ? "null" : (String) aktobject.get(insertpackage.getColId());
            String title = aktobject.isNull(insertpackage.getColTitle()) ? "null" : (String) aktobject.get(insertpackage.getColTitle());
            String topic = aktobject.isNull(insertpackage.getColTopic()) ? "null" : (String) aktobject.get(insertpackage.getColTopic());
            String kind = aktobject.isNull(insertpackage.getColKind()) ? "null" : (String) aktobject.get(insertpackage.getColKind());
            String owner = aktobject.isNull(insertpackage.getColOwner()) ? "null" : (String) aktobject.get(insertpackage.getColOwner());
            String infourl = aktobject.isNull(insertpackage.getColInfoUrl()) ? "null" : (String) aktobject.get(insertpackage.getColInfoUrl());
            String abstract_short = aktobject.isNull(insertpackage.getColAbstract()) ? "null" : (String) aktobject.get(insertpackage.getColAbstract());
            String description = aktobject.isNull(insertpackage.getColDescription()) ? "null" : (String) aktobject.get(insertpackage.getColDescription());
            String motivation = aktobject.isNull(insertpackage.getColMotivation()) ? "null" : (String) aktobject.get(insertpackage.getColMotivation());

            id = fixUrlEncoding(id);
            title = fixUrlEncoding(title);
            topic = fixUrlEncoding(topic);
            kind = fixUrlEncoding(kind);
            owner = fixUrlEncoding(owner);
            infourl = fixUrlEncoding(infourl);
            abstract_short = fixUrlEncoding(abstract_short);
            description = fixUrlEncoding(description);
            motivation = fixUrlEncoding(motivation);

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
        //Fix buggy html shit
        // String unescapeddata = StringEscapeUtils.unescapeHtml4(data);
        // unescapeddata=fixUrlEncoding(unescapeddata);
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

    private static String fixUrlEncoding(String input) {
        input = StringEscapeUtils.unescapeHtml4(input);
        input = input.replaceAll("<p>", "\n");
        input = input.replaceAll("</p>", "\n");
        input = input.replaceAll("<br>", "\n");
        input = input.replaceAll("%C3%84", "Ä");
        input = input.replaceAll("%C3%96", "Ö");
        input = input.replaceAll("%C3%9C", "Ü");
        input = input.replaceAll("%C3%9F", "ß");
        input = input.replaceAll("%C3%A4", "ä");
        input = input.replaceAll("%C3%B6", "ö");
        input = input.replaceAll("%C3%BC", "ü");
        return input;
    }
}
