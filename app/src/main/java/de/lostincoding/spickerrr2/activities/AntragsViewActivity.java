package de.lostincoding.spickerrr2.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import de.lostincoding.spickerrr2.R;
import de.lostincoding.spickerrr2.model.Antrag;

public class AntragsViewActivity extends AppCompatActivity {
    private Antrag antrag;
    private WebView description;
    private WebView motivation;
    private TextView topic;
    private WebView author;

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
        author = (WebView) findViewById(R.id.author);
        topic = (TextView) findViewById(R.id.topic);

        description.getSettings();
        description.setBackgroundColor(Color.TRANSPARENT);

        FloatingActionButton voteButton = (FloatingActionButton) findViewById(R.id.editVotePreference);
        voteButton.setIcon(R.drawable.ic_thumbs_up_down_white_24dp);

        FloatingActionButton noticeButton = (FloatingActionButton) findViewById(R.id.editNotice);
        noticeButton.setIcon(R.drawable.ic_assignment_white_24dp);

        motivation.getSettings();
        motivation.setBackgroundColor(Color.TRANSPARENT);

        author.getSettings();
        author.setBackgroundColor(Color.TRANSPARENT);


        description.loadData(antrag.getDescription(), "text/html; charset=UTF-8", null);
        motivation.loadData(antrag.getMotivation(), "text/html; charset=UTF-8", null);
        author.loadData(antrag.getOwner(), "text/html; charset=UTF-8", null);
        topic.setText(antrag.getTopic());
    }

    public void editVotePreference(View v) {

    }

    public void editNotice(View v) {
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        NoticeEditDialog editNameDialog = new NoticeEditDialog();
        editNameDialog.show(manager, "fragment_edit_name");
    }

}
