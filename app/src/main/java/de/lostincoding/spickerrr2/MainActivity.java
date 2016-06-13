package de.lostincoding.spickerrr2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.lostincoding.spickerrr2.api.APICaller;
import de.lostincoding.spickerrr2.api.BookResponse;
import de.lostincoding.spickerrr2.api.SpickerrrApi;
import de.lostincoding.spickerrr2.model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<BookResponse> {
private final String apikey="853f688d3842";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // loadCurrentBooks();
        loadBooks();
    }

    public void loadCurrentBooks() {
        APICaller caller = APICaller.getInstance(apikey);
        caller.listCurrentBooks(this);
    }
    public void loadBooks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pirat.ly/spicker/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpickerrrApi  service = retrofit.create(SpickerrrApi.class);

        Call<BookResponse> call=service.listBooks(apikey);
        //asynchronous call
        call.enqueue(this);

    }

    public void fillBookSpinner(List<Book> booklist) {
        Spinner spinner = (Spinner) findViewById(R.id.bookspinner);
        ArrayList<String> booknames = new ArrayList<>();
        for (Book book : booklist) {
            booknames.add(book.getName());
        }
        ArrayAdapter<String> bookadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, booknames);
        bookadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(bookadapter);
    }

    @Override
    public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
        if (response.body().getSuccess()) {
            List<Book> booklist = response.body().getData();
            fillBookSpinner(booklist);
        } else {
            //Not Successful
            Context context = getApplicationContext();
            CharSequence text = "Beim Laden der Daten ist ein Fehler aufgetreten!";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    @Override
    public void onFailure(Call<BookResponse> call, Throwable t) {
        //Not Successful
        Context context = getApplicationContext();
        CharSequence text = "Beim Laden der Daten ist ein Fehler aufgetreten!";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }
}
