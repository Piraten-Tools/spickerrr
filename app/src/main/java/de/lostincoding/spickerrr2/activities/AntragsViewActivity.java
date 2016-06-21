package de.lostincoding.spickerrr2.activities;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.model.Antrag;

public class AntragsViewActivity extends AppCompatActivity {
    private Antrag antrag;
    private WebView description;
    private WebView motivation;
    private TextView topic;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_view);

        antrag = getIntent().getParcelableExtra("antrag");
        initalizeUI();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;

    }

    private void initalizeUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(antrag.getId() + " " + antrag.getTitle());

        description = (WebView) findViewById(R.id.description);
        motivation = (WebView) findViewById(R.id.motivation);
        author = (TextView) findViewById(R.id.author);
        topic = (TextView) findViewById(R.id.topic);

        description.getSettings();
        description.setBackgroundColor(Color.TRANSPARENT);


        motivation.getSettings();
        motivation.setBackgroundColor(Color.TRANSPARENT);

        description.loadData(antrag.getDescription(), "text/html; charset=UTF-8", null);
        motivation.loadData(antrag.getMotivation(), "text/html; charset=UTF-8", null);
        author.setText(antrag.getOwner());
        topic.setText(antrag.getTopic());
    }
}
