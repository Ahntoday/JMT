package com.example.jmt_2;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

public class ReviewFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ReviewRecentSubFragment reviewRecentSubFragment;
    ReviewTabPagerAdaptor pagerAdaptor;

    public ReviewFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.review_fragment, container, false);

//        reviewRecentSubFragment = new ReviewRecentSubFragment();

//        getChildFragmentManager().beginTransaction().add(R.id.viewPager, reviewRecentSubFragment).commit();

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.review_tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setOffscreenPageLimit(2);

        pagerAdaptor = new ReviewTabPagerAdaptor(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdaptor);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        return view;
    }



}
