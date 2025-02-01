package de.piratentools.spickerrr2.uielements;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lostincoding on 30.06.16.
 */
public class SpickerrrViewPager extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SpickerrrViewPager(FragmentManager manager) {
        super(manager);
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(POSITION_NONE);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}
