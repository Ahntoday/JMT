package com.example.jmt_2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class MyPagerAdapter extends FragmentStatePagerAdapter {
    private int tabcount;

    public MyPagerAdapter(FragmentManager fm, int tabcount) {
        super(fm);
        this.tabcount = tabcount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                StartOne startOne = new StartOne();
                return startOne;
            case 1:
                StartTwo startTwo = new StartTwo();
                return startTwo;
            case 2:
                StartThree startThree = new StartThree();
                return startThree;
            default:
                return fragment;
        }
    }


    @Override
    public int getCount() {
        return tabcount;
    }
}
