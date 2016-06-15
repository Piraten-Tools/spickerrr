package de.lostincoding.spickerrr2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.model.Antrag;

public class AntragsViewActivity extends AppCompatActivity {
private Antrag antrag;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_view);

    antrag=getIntent().getParcelableExtra("antrag");
        initalizeUI();
    }


    private void initalizeUI() {
        textView=(TextView) findViewById(R.id.antragsview);
        setTitle(antrag.getId()+" "+antrag.getTitle());
        textView.setText(antrag.getDescription());
    }
}
