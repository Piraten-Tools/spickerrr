package de.lostincoding.spickerrr2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.lostincoding.spickerrr2.api.APICaller;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testApi();
    }


    public void testApi(){
        APICaller caller= APICaller.getInstance("853f688d3842");
        caller.listBooks();
    }
}
