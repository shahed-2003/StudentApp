package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsAdapter extends FragmentStateAdapter {

    private static final String[] TITLES = {"First", "Second", "Third"};

    public TabsAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return TabFragment.newInstance(TITLES[position]);
    }

    @Override
    public int getItemCount() {
        return TITLES.length;
    }
}
