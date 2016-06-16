package de.lostincoding.spickerrr2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.model.Antrag;

public class AntragsViewActivity extends AppCompatActivity {
    private Antrag antrag;
    private TextView description;
    private TextView motivation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_view);

        antrag = getIntent().getParcelableExtra("antrag");
        initalizeUI();
    }


    private void initalizeUI() {
        description = (TextView) findViewById(R.id.description);
        motivation = (TextView) findViewById(R.id.motivation);
        setTitle(antrag.getId() + " " + antrag.getTitle());
        description.setText(antrag.getDescription());
        motivation.setText(antrag.getMotivation());
    }
}
