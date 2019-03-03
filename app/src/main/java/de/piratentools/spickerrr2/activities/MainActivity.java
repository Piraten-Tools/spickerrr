package de.piratentools.spickerrr2.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.api.BookResponse;
import de.piratentools.spickerrr2.api.PackageResponse;
import de.piratentools.spickerrr2.api.SpickerrrApi;
import de.piratentools.spickerrr2.model.DataHolder;
import de.piratentools.spickerrr2.model.JsonBook;
import de.piratentools.spickerrr2.model.JsonPackage;
import de.piratentools.spickerrr2.model.Package;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private SpickerrrApi service;

    private Callback<BookResponse> bookcallback;
    private Callback<PackageResponse> packagecallback;
    private List<JsonBook> bookList;
    private List<JsonPackage> packageList;
    private Spinner packageSpinner;
    private Spinner bookSpinner;
    private FloatingActionButton fab;
    private SharedPreferences sharedPreferences;
    private DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://spicker.piraten-tools.de/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SpickerrrApi.class);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initalizeUI();
        dataHolder = DataHolder.getInstance();
        loadData();

    }

    private void initalizeUI() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
    }

    private void loadData() {
        if (checkInternetConnection()) {
            initCallbacks();

            if (sharedPreferences.getBoolean("bookLoadPreference", false)) {
                Call<BookResponse> call = service.listBooks();
                call.enqueue(bookcallback);
            } else {
                Call<BookResponse> call = service.listCurrentBooks();
                call.enqueue(bookcallback);
            }

        } else {
            Context context = getApplicationContext();
            CharSequence text = "Internetverbindung notwendig";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void fillPackageSpinner(final List<JsonPackage> packagelist) {
        ArrayList<String> packagenames = new ArrayList<>();
        packageSpinner = (Spinner) findViewById(R.id.packagespinner);
        for (JsonPackage pack : packagelist) {
            packagenames.add(pack.getName());
        }
        ArrayAdapter<String> packageadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, packagenames);
        packageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        packageSpinner.setAdapter(packageadapter);
        packageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Package apackage = packagelist.get(position).toPackage();
                //if package has changed load new package to dataholder and set antragslist null so it will be updated
                if (!apackage.equals(dataHolder.getaPackage())) {
                    dataHolder.setaPackage(apackage);
                    dataHolder.setAntragslist(null);
                }
                fab.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    private void fillBookSpinner(final List<JsonBook> booklist) {
        ArrayList<String> booknames = new ArrayList<>();
        bookSpinner = (Spinner) findViewById(R.id.bookspinner);
        if (booklist != null) {


            for (JsonBook book : booklist) {
                booknames.add(book.getName());
            }
            ArrayAdapter<String> bookadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, booknames);
            bookadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            bookSpinner.setAdapter(bookadapter);
            bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dataHolder.setBook(booklist.get(position).toBook());
                    Call<PackageResponse> call = service.listPackagesFromBook(booklist.get(position).getKey());
                    call.enqueue(packagecallback);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //Do nothing
                }
            });
        } else {
            showToast("Es sind im Moment keine aktiven Antragsbücher verfügbar. Es können auch Antragsbücher vergangener Parteitage geladen werden. Gehe dazu einfach in die Einstellungen.");
        }

    }

    private void initCallbacks() {
        bookcallback = new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.body().getSuccess()) {
                    bookList = response.body().getData();
                    fillBookSpinner(bookList);
                } else {
                    //Not Successful
                    showToast("Beim Laden der Antragsbücher ist ein Fehler aufgetreten!");
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                //Not Successful
                showToast("Beim Laden der Antragsbücher ist ein Fehler aufgetreten!");
            }
        };
        packagecallback = new Callback<PackageResponse>() {
            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse> response) {
                if (response.body().getSuccess()) {
                    //filter csv , it is not working right now
                    List<JsonPackage> list = response.body().getData();
                    packageList = new ArrayList<>();
                    for (JsonPackage listitem : list) {
                        if (listitem.getSourceType().equals("JSON")) {
                            packageList.add(listitem);
                        }
                    }
                    fillPackageSpinner(packageList);

                } else {
                    //Not Successful
                    showToast("Beim Laden der Antragspakete ist ein Fehler aufgetreten!");
                }
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {
                //Not Successful
                showToast("Beim Laden der Antragspakete ist ein Fehler aufgetreten!");
            }
        };
    }


    private void showToast(CharSequence message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, message, duration).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                startActivity(new Intent(this, AppPreferencesActivity.class));
                break;
        }


        return true;
    }

    public void openNextActivity(View view) {
        if (packageSpinner != null) {
            if (packageSpinner.getSelectedItem() != null) {
                Intent intent = new Intent(this, AntragsChooserActivity.class);
                //intent.putExtra("package", packageList.get(packageSpinner.getSelectedItemPosition()));
                startActivity(intent);
            } else {
                showToast("Es ist noch kein Antragspaket gewählt!");
            }
        }


    }
}
