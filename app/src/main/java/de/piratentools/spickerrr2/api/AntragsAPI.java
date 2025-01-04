package de.piratentools.spickerrr2.api;

import org.apache.commons.text.StringEscapeUtils;
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
                .url(data.dataUrl())
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
            String id = aktobject.isNull(insertpackage.colId()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colId());
            String title = aktobject.isNull(insertpackage.colTitle()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colTitle());
            String kind = aktobject.isNull(insertpackage.colKind()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colKind());
            String topic = aktobject.isNull(insertpackage.colTopic()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colTopic());
            String owner = aktobject.isNull(insertpackage.colOwner()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colOwner());
            String infourl = aktobject.isNull(insertpackage.colInfoUrl()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colInfoUrl());
            String abstract_short = aktobject.isNull(insertpackage.colAbstract()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colAbstract());
            String description = aktobject.isNull(insertpackage.colDescription()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colDescription());
            String motivation = aktobject.isNull(insertpackage.colMotivation()) ? "Nicht vorhanden" : (String) aktobject.get(insertpackage.colMotivation());

            id = fixUrlEncoding(id);
            title = fixUrlEncoding(title);
            kind = fixUrlEncoding(kind);
            topic = fixUrlEncoding(topic);

            description = fixUrlEncoding(description);
            motivation = fixUrlEncoding(motivation);

            //fix relative owner URL
            String baseurl = "http://wiki.piratenpartei.de/";
            if (infourl.startsWith(baseurl) && !owner.contains(baseurl)) {

                StringBuilder sb = new StringBuilder();
                if (owner.indexOf('/') >= 0) {
                    sb.append("<a href=\"").append(baseurl).append(owner.substring(owner.indexOf('/') + 1));
                    owner = sb.toString();
                }
            }

            String bookKey = insertpackage.bookKey();
            String packageKey = insertpackage.key();
            antragsliste.add(new Antrag(id, title, topic, kind, owner, infourl, abstract_short, description, motivation, bookKey, packageKey));
        }

        return antragsliste;

    }

    private static ArrayList<Antrag> parseCSV(String data, Package insertPackage) throws IOException {
        ArrayList<Antrag> antragsliste = new ArrayList<>();
        CSVReader reader = new CSVReader(new StringReader(data), insertPackage.csvSeperator().charAt(0), insertPackage.csvSeperator().charAt(0));
        ArrayList<String[]> columnlist = new ArrayList<>();
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            columnlist.add(nextLine);
        }
        return antragsliste;
    }

    public static ArrayList<Antrag> parseData(String data, Package insertpackage) throws JSONException, IOException {
        return switch (insertpackage.sourceType()) {
            case "JSON" -> parseJSON(data, insertpackage);
            case "CSV" -> parseCSV(data, insertpackage);
            default -> new ArrayList<>();
        };
    }

    private static String fixUrlEncoding(String input) {
        input = StringEscapeUtils.unescapeHtml4(input);
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
