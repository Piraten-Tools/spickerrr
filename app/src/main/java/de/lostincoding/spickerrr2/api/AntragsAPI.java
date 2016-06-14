package de.lostincoding.spickerrr2.api;

import android.content.Context;
import android.widget.Toast;

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

    private ArrayList<Antrag> parseJSON(String data) {
        JSONObject reader
    }

    private ArrayList<Antrag> parseCSV(String data) {
        return null;
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
                antragslist = parseJSON(data);
                break;
            case "CSV":
                antragslist = parseCSV(data);
                break;
        }


        return antragslist;
    }
}
