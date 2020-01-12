package com.example.jmt_2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CategoryTabPagerAdaptor extends FragmentStatePagerAdapter {
    private int tabCount;

    public CategoryTabPagerAdaptor(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CategoryLocationSubFragment();
            case 1:
                return new CategorySituationSubFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
