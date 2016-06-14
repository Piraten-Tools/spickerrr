package de.lostincoding.spickerrr2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.lostincoding.spickerrr2.api.APICaller;

public class AntragsChooserActivity extends AppCompatActivity {
    private APICaller caller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_chooser);
        caller = APICaller.getInstance();
    }


    private void fillListView() {

    }
}
