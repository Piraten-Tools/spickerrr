package de.piratentools.spickerrr2.activities;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.fragments.AntragsViewContentFragment;
import de.piratentools.spickerrr2.fragments.AntragsViewInfoFragment;
import de.piratentools.spickerrr2.fragments.ChooseVotePreferencesDialog;
import de.piratentools.spickerrr2.fragments.NoticeEditDialog;
import de.piratentools.spickerrr2.model.Antrag;
import de.piratentools.spickerrr2.model.DataHolder;
import de.piratentools.spickerrr2.model.VotePreference;
import de.piratentools.spickerrr2.uielements.SpickerrrViewPager;

public class AntragsViewActivity extends AppCompatActivity implements NoticeEditDialog.NoticeEditedListener, ChooseVotePreferencesDialog.VotePreferenceSetListener {
    private Antrag antrag;
    private AntragsViewInfoFragment infoFragment;
    int position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrags_view);
        DataHolder dataHolder = DataHolder.getInstance();

        position = getIntent().getIntExtra("position", -1);
        antrag = dataHolder.getAntragslist().get(position);


        initializeUI();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;

    }

    private void setupViewPager(ViewPager viewPager) {
        SpickerrrViewPager adapter = new SpickerrrViewPager(getSupportFragmentManager());

        infoFragment = new AntragsViewInfoFragment();
        AntragsViewContentFragment descriptionFragment = new AntragsViewContentFragment();
        AntragsViewContentFragment motivationFragment = new AntragsViewContentFragment();

        //give  fragment the data over the bundle
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        Bundle descriptionBundle = new Bundle();
        descriptionBundle.putString("content", antrag.description());

        Bundle motivationBundle = new Bundle();
        motivationBundle.putString("content", antrag.motivation());

        infoFragment.setArguments(bundle);
        descriptionFragment.setArguments(descriptionBundle);
        motivationFragment.setArguments(motivationBundle);

        adapter.addFragment(descriptionFragment, getString(R.string.motion_description));
        adapter.addFragment(motivationFragment, getString(R.string.motion_motivation));
        adapter.addFragment(infoFragment, getString(R.string.motion_info));

        viewPager.setAdapter(adapter);
    }

    private void initializeUI() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(antrag.id() + " " + antrag.title());

        ViewPager viewPager = findViewById(R.id.antragsViewViewpager);
        TabLayout tabLayout = findViewById(R.id.antragsViewTabs);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        //adjust icons  for buttons


        FloatingActionButton voteButton = findViewById(R.id.editVotePreference);
        voteButton.setIcon(R.drawable.ic_thumbs_up_down_white_24dp);


        FloatingActionButton noticeButton = findViewById(R.id.editNotice);
        noticeButton.setIcon(R.drawable.ic_assignment_white_24dp);


    }

    // for changing the vote preference and the notice
    public void editVotePreference(View v) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");

        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }


        ChooseVotePreferencesDialog chooseVotePreferencesDialog = new ChooseVotePreferencesDialog();
        chooseVotePreferencesDialog.show(manager, "fragment_edit_name");
    }

    public void editNotice(View v) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }

        Bundle bundle = null;
        if (antrag.getNoticePreference() != null) {
            bundle = new Bundle();
            bundle.putString("notice", antrag.getNoticePreference());
        }

        NoticeEditDialog noticeEditDialog = new NoticeEditDialog();

        noticeEditDialog.setArguments(bundle);
        noticeEditDialog.show(manager, "fragment_edit_name");
    }


    @Override
    public void onFinishUserDialog(String notice) {
        antrag.setNoticePreference(notice);
        if (infoFragment.isVisible()) {
            infoFragment.onFragmentUpdate();
        }
    }

    @Override
    public void onFinishUserDialog(VotePreference preferences) {
        antrag.setVotePreference(preferences);
        if (infoFragment.isVisible()) {
            infoFragment.onFragmentUpdate();
        }
    }

    public interface InfoFragmentUpdateListener {
        void onFragmentUpdate();
    }


}
