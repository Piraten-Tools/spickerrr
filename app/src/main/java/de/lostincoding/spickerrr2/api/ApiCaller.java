package de.lostincoding.spickerrr2.api;

import java.util.ArrayList;

import de.lostincoding.spickerrr2.model.Book;
import de.lostincoding.spickerrr2.model.Package;

/**
 * Created by lostincoding on 26.05.16.
 */
public class ApiCaller {

    private static ApiCaller instance;

    private ApiCaller() {

    }

    public static ApiCaller getInstance() {
        if (ApiCaller.instance == null) {
            ApiCaller.instance = new ApiCaller();
        }
        return ApiCaller.instance;
    }


    public ArrayList<Book> listBooks(String apikey) {
        return null;
    }

    public ArrayList<Book> listCurrentBooks(String apikey) {
        return null;
    }

    public ArrayList<Package> listPackagesFromBook(String apikey, String bookkey) {
        return null;
    }

    public ArrayList<Package> listActivePackages(String apikey) {
        return null;
    }

    public Package getPackage(String apikey, String packagekey) {
        return null;
    }

}
