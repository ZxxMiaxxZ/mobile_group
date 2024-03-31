package com.example.advanced_download_manager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 2; // Define the number of pages

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return the fragment for the given position
        switch (position) {
            case 0:
                return new BlankFragment1();
            case 1:
                return new BlankFragment2();
            default:
                return new BlankFragment1();
        }
    }

    @Override
    public int getItemCount() {
        // Return the number of pages
        return NUM_PAGES;
    }
}
