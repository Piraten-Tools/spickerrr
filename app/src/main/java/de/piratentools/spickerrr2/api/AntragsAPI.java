package de.piratentools.spickerrr2.api;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import de.piratentools.spickerrr2.model.Antrag;
import de.piratentools.spickerrr2.model.Package;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
        JSONArray antragsArray;

        antragsArray = new JSONArray(data);
        for (int i = 0; i < antragsArray.length(); i++) {
            JSONObject aktobject = antragsArray.getJSONObject(i);
            //i have to now if the object is null if i want to cast to string so i use proxyobjects to check this
            String id = aktobject.isNull(insertpackage.getColId()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColId());
            String title = aktobject.isNull(insertpackage.getColTitle()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColTitle());
            String kind = aktobject.isNull(insertpackage.getColKind()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColKind());
            String topic = aktobject.isNull(insertpackage.getColTopic()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColTopic());
            String owner = aktobject.isNull(insertpackage.getColOwner()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColOwner());
            String infourl = aktobject.isNull(insertpackage.getColInfoUrl()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColInfoUrl());
            String abstract_short = aktobject.isNull(insertpackage.getColAbstract()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColAbstract());
            String description = aktobject.isNull(insertpackage.getColDescription()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColDescription());
            String motivation = aktobject.isNull(insertpackage.getColMotivation()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.getColMotivation());

            id = fixUrlEncoding(id);
            title = fixUrlEncoding(title);
            kind = fixUrlEncoding(kind);
            topic = fixUrlEncoding(topic);


            //fix relative owner URL
            String baseurl = "http://wiki.piratenpartei.de/";
            if (infourl.startsWith(baseurl) && !owner.contains(baseurl)) {

                StringBuilder sb = new StringBuilder();
                if (owner.indexOf('/') >= 0) {
                    sb.append("<a href=\"").append(baseurl).append(owner.substring(owner.indexOf('/') + 1));
                    owner = sb.toString();
                }
            }

            String bookKey = insertpackage.getBookKey();
            String packageKey = insertpackage.getKey();
            antragsliste.add(new Antrag(id, title, topic, kind, owner, infourl, abstract_short, description, motivation, bookKey, packageKey));
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

    private static String fixUrlEncoding(String input) {
        input = StringEscapeUtils.unescapeHtml4(input);
        input = input.replaceAll("<p>", "");
        input = input.replaceAll("</p>", "");
        input = input.replaceAll("<br>", "");
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
