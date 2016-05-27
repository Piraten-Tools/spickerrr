package de.lostincoding.spickerrr2.api;

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
    private String apikey;
    private SpickerrrApi service;
    private static APICaller instance;

    private APICaller(String apikey) {
        this.apikey = apikey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pirat.ly/spicker/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SpickerrrApi.class);
    }

    public static APICaller getInstance(String apikey) {
        if (APICaller.instance == null) {
            APICaller.instance = new APICaller(apikey);
        }
        return APICaller.instance;
    }


    public List<Book> listBooks() {
        Call<BookResponse> call = service.listBooks(apikey);

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {
                    BookResponse bookResponse = response.body();
                    List<Book> books = bookResponse.getData();
                } else {

                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {

            }
        });



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
