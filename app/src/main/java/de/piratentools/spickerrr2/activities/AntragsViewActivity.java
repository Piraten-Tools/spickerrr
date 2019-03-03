package de.piratentools.spickerrr2.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.model.DataHolder;
import de.piratentools.spickerrr2.uielements.SpickerrrViewPager;
import de.piratentools.spickerrr2.model.VotePreferences;
import de.piratentools.spickerrr2.fragments.AntragsViewContentFragment;
import de.piratentools.spickerrr2.fragments.AntragsViewInfoFragment;
import de.piratentools.spickerrr2.fragments.ChooseVotePreferencesDialog;
import de.piratentools.spickerrr2.fragments.NoticeEditDialog;
import de.piratentools.spickerrr2.model.Antrag;

public class AntragsViewActivity extends AppCompatActivity implements NoticeEditDialog.NoticeEditedListener, ChooseVotePreferencesDialog.VotePreferenceSetListener {
    private Antrag antrag;
    private TabLayout tabLayout;
    private android.support.v4.view.ViewPager viewPager;
    private AntragsViewInfoFragment infoFragment;
    private AntragsViewContentFragment descriptionFragment;
    private AntragsViewContentFragment motivationFragment;
    private DataHolder dataHolder;
    int position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_view);
        dataHolder = DataHolder.getInstance();

        position = getIntent().getIntExtra("position", -1);
        antrag = dataHolder.getAntragslist().get(position);


        initalizeUI();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;

    }

    private void setupViewPager(android.support.v4.view.ViewPager viewPager) {
        SpickerrrViewPager adapter = new SpickerrrViewPager(getSupportFragmentManager());

        infoFragment = new AntragsViewInfoFragment();
        descriptionFragment = new AntragsViewContentFragment();
        motivationFragment = new AntragsViewContentFragment();

        //give  fragment the data over the bundle
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        Bundle descriptionBundle = new Bundle();
        descriptionBundle.putString("content", antrag.getDescription());

        Bundle motivationBundle = new Bundle();
        motivationBundle.putString("content", antrag.getMotivation());

        infoFragment.setArguments(bundle);
        descriptionFragment.setArguments(descriptionBundle);
        motivationFragment.setArguments(motivationBundle);

        adapter.addFragment(descriptionFragment, "Antragstext");
        adapter.addFragment(motivationFragment, "Antragsbegründung");
        adapter.addFragment(infoFragment, "Allgemein");

        viewPager.setAdapter(adapter);
    }

    private void initalizeUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(antrag.getId() + " " + antrag.getTitle());

        viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.antragsViewViewpager);
        tabLayout = (TabLayout) findViewById(R.id.antragsViewTabs);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        //adjust icons  for buttons


        FloatingActionButton voteButton = (FloatingActionButton) findViewById(R.id.editVotePreference);
        voteButton.setIcon(R.drawable.ic_thumbs_up_down_white_24dp);


        FloatingActionButton noticeButton = (FloatingActionButton) findViewById(R.id.editNotice);
        noticeButton.setIcon(R.drawable.ic_assignment_white_24dp);


    }

    // for changing the vote preference and the notice
    public void editVotePreference(View v) {
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");

        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }


        ChooseVotePreferencesDialog chooseVotePreferencesDialog = new ChooseVotePreferencesDialog();
        chooseVotePreferencesDialog.show(manager, "fragment_edit_name");
    }

    public void editNotice(View v) {
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }

        Bundle bundle = null;
        if (antrag.getNotice() != null) {
            bundle = new Bundle();
            bundle.putString("notice", antrag.getNotice());
        }

        NoticeEditDialog noticeEditDialog = new NoticeEditDialog();

        noticeEditDialog.setArguments(bundle);
        noticeEditDialog.show(manager, "fragment_edit_name");
    }


    @Override
    public void onFinishUserDialog(String notice) {
        antrag.setNotice(notice);
        infoFragment.onFragmentUpdate();

    }

    @Override
    public void onFinishUserDialog(VotePreferences preferences) {
        antrag.setVotePreferences(preferences);
        infoFragment.onFragmentUpdate();
    }

    //interfaces
    public interface InfoFragmentUpdateListener {
        void onFragmentUpdate();
    }


}
