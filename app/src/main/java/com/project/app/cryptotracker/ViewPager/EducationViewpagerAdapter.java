package com.project.app.cryptotracker.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class EducationViewpagerAdapter extends FragmentStateAdapter {
    // TODO: add arraylist datasource to class properties and constructor
    public EducationViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // TODO: get data from the current position of arraylist and pass it to the fragment
        return EducationViewPagerFragment.newInstance("","");
    }

    @Override
    public int getItemCount() {
        // TODO: return the total size of arraylist
        return 0;
    }
}
