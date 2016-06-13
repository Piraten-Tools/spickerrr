package de.lostincoding.spickerrr2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import de.lostincoding.spickerrr2.model.Package;
import java.util.ArrayList;
import java.util.List;
import de.lostincoding.spickerrr2.api.APICaller;
import de.lostincoding.spickerrr2.api.BookResponse;
import de.lostincoding.spickerrr2.api.PackageResponse;
import de.lostincoding.spickerrr2.model.Book;
import de.lostincoding.spickerrr2.model.Package;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String apikey = "853f688d3842";
    private APICaller caller;
    private Callback<BookResponse> bookcallback;
    private Callback<PackageResponse> packagecallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initaliseCallbacks();
        caller = APICaller.getInstance(apikey);
        caller.listCurrentBooks(bookcallback);
    }

    private void fillPackageSpinner(final List<Package> packagelist) {
        ArrayList<String> packagenames = new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.packagespinner);
        for (Package pack : packagelist) {
            packagenames.add(pack.getName());
        }
        ArrayAdapter<String> packageadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, packagenames);
        packageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(packageadapter);
    }

    private void fillBookSpinner(final List<Book> booklist) {
        ArrayList<String> booknames = new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.bookspinner);
        for (Book book : booklist) {
            booknames.add(book.getName());
        }
        ArrayAdapter<String> bookadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, booknames);
        bookadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(bookadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                caller.listPackagesFromBook(packagecallback, booklist.get(position).getKey());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });
    }

    private void initaliseCallbacks() {
        bookcallback = new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.body().getSuccess()) {
                    List<Book> booklist = response.body().getData();
                    fillBookSpinner(booklist);
                } else {
                    //Not Successful
                    Context context = getApplicationContext();
                    CharSequence text = "Beim Laden der Antragsbücher ist ein Fehler aufgetreten!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                //Not Successful
                Context context = getApplicationContext();
                CharSequence text = "Beim Laden der Antragsbücher ist ein Fehler aufgetreten!";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }
        };
        packagecallback = new Callback<PackageResponse>() {
            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse> response) {
                if (response.body().getSuccess()) {
                    List<de.lostincoding.spickerrr2.model.Package> packagelist = response.body().getData();
                    fillPackageSpinner(packagelist);
                } else {
                    //Not Successful
                    Context context = getApplicationContext();
                    CharSequence text = "Beim Laden der Antragspakete ist ein Fehler aufgetreten!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {
                //Not Successful
                Context context = getApplicationContext();
                CharSequence text = "Beim Laden der Antragspakete ist ein Fehler aufgetreten!";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }
        };
    }

}
