package de.piratentools.spickerrr2.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.preference.PreferenceManager;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
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
    private static final String TAG = "MainActivity";

    private SpickerrrApi service;

    private Callback<BookResponse> bookcallback;
    private Callback<PackageResponse> packagecallback;
    private List<JsonBook> bookList;
    private List<JsonPackage> packageList;
    private Spinner packageSpinner;
    private FloatingActionButton fab;
    private SharedPreferences sharedPreferences;
    private DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://spickerrr.piraten.tools/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SpickerrrApi.class);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        dataHolder = DataHolder.createInstance(getApplicationContext());
        dataHolder.setAppPreferences(sharedPreferences);
    }

    @Override
    protected void onStart() {
        initializeUI();
        loadData();
        super.onStart();
    }

    private void initializeUI() {
        fab = findViewById(R.id.fab);
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
            Log.v(TAG, "error_no_internet");
            showToast(getString(R.string.error_no_internet));
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
        packageSpinner = findViewById(R.id.packagespinner);
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
        Spinner bookSpinner = findViewById(R.id.bookspinner);
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
            Log.e(TAG, "Error: No motionbooks available");
            showToast(getString(R.string.error_no_motionbooks_available));
        }

    }

    private void initCallbacks() {
        bookcallback = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<BookResponse> call, @NonNull Response<BookResponse> response) {
                if (response.body() != null && response.body().getSuccess()) {
                    bookList = response.body().getData();
                    fillBookSpinner(bookList);
                    Log.i(TAG, "Loaded new motionbooks");
                } else {
                    //Not Successful
                    Log.e(TAG, "Unknown error while loading motionbooks");
                    showToast(getString(R.string.error_loading_motionbooks));
                }
            }

            @Override
            public void onFailure(@NonNull Call<BookResponse> call, @NonNull Throwable t) {
                //Not Successful
                Log.e(TAG, "Error while loading motionbooks", t);
                showToast(getString(R.string.error_loading_motionbooks));
            }
        };
        packagecallback = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<PackageResponse> call, @NonNull Response<PackageResponse> response) {
                if (response.body() != null && response.body().getSuccess()) {
                    //filter csv , it is not working right now
                    List<JsonPackage> list = response.body().getData();
                    packageList = new ArrayList<>();
                    for (JsonPackage listitem : list) {
                        if (listitem.getSourceType().equals("JSON")) {
                            packageList.add(listitem);
                        }
                    }
                    fillPackageSpinner(packageList);
                    Log.i(TAG, "Loaded new motionpackages");

                } else {
                    //Not Successful
                    Log.e(TAG, "Unknown error while loading motionpackages");
                    showToast(getString(R.string.error_loading_motionpackages));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PackageResponse> call, @NonNull Throwable t) {
                //Not Successful
                Log.e(TAG, "Error while loading motionpackages", t);
                showToast(getString(R.string.error_loading_motionpackages));
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
            case R.id.reload:
                initializeUI();
                loadData();
                break;
            case R.id.preferences:
                startActivity(new Intent(this, AppPreferencesActivity.class));
                break;
        }
        return true;
    }

    public void openNextActivity(View view) {
        if (packageSpinner != null) {
            if (packageSpinner.getSelectedItem() != null) {
                Log.i(TAG, "Opening motionpackages: " + packageSpinner.getSelectedItem());
                Intent intent = new Intent(this, AntragsChooserActivity.class);
                //intent.putExtra("package", packageList.get(packageSpinner.getSelectedItemPosition()));
                startActivity(intent);
            } else {
                Log.e(TAG, "Error: No motionpackages chosen");
                showToast(getString(R.string.error_no_motionpackage_chosen));
            }
        }


    }
}
