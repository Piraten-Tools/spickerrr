package de.lostincoding.spickerrr2.api;

import java.util.ArrayList;

import de.lostincoding.spickerrr2.model.Book;
import de.lostincoding.spickerrr2.model.Package;
import retrofit2.Retrofit;

/**
 * Created by lostincoding on 26.05.16.
 */
public class APICaller {
    private String apikey;
    private SpickerrrApi service;
    private static APICaller instance;

    private APICaller(String apikey) {
        this.apikey = apikey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pirat.ly/spicker/api/")
                .build();

        service = retrofit.create(SpickerrrApi.class);
    }

    public static APICaller getInstance(String apikey) {
        if (APICaller.instance == null) {
            APICaller.instance = new APICaller(apikey);
        }
        return APICaller.instance;
    }


    public ArrayList<Book> listBooks() {
        service.listBooks(apikey);
        return null;
    }

    public ArrayList<Book> listCurrentBooks() {
        service.listCurrentBooks(apikey);
        return null;
    }

    public ArrayList<Package> listPackagesFromBook(String bookkey) {
        service.listPackagesFromBook(apikey, bookkey);
        return null;
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
