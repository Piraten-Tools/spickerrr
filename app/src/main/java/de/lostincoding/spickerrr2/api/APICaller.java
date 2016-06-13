package de.lostincoding.spickerrr2.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.lostincoding.spickerrr2.model.Book;
import de.lostincoding.spickerrr2.model.Package;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lostincoding on 26.05.16.
 */
public class APICaller {
    private final String apikey = "853f688d3842";
    private SpickerrrApi service;
    private static APICaller instance;

    private APICaller() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pirat.ly/spicker/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SpickerrrApi.class);
    }

    public static APICaller getInstance() {
        if (APICaller.instance == null) {
            APICaller.instance = new APICaller();
        }
        return APICaller.instance;
    }

    public void listBooks(Callback<BookResponse> callback) {
        Call<BookResponse> call = service.listBooks(apikey);
        //asynchronous call
        call.enqueue(callback);
    }

    public void listCurrentBooks(Callback<BookResponse> callback) {
        Call<BookResponse> call = service.listCurrentBooks(apikey);
        //asynchronous call
        call.enqueue(callback);
    }

    public void listPackagesFromBook(Callback<PackageResponse> callback, String bookkey) {
        Call<PackageResponse> call = service.listPackagesFromBook(apikey, bookkey);
        call.enqueue(callback);
    }

    public ArrayList<Package> listActivePackages() {
        service.listActivePackages(apikey);
        return null;
    }

    public Package getPackage(String packagekey) {
        service.getPackage(apikey, packagekey);
        return null;
    }

}
