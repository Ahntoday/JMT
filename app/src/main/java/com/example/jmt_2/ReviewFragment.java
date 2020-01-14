package com.example.jmt_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

public class ReviewFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ReviewRecentSubFragment reviewRecentSubFragment;
    ReviewTabPagerAdaptor pagerAdaptor;

    public ReviewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.review_fragment, container, false);

//        reviewRecentSubFragment = new ReviewRecentSubFragment();
//        getChildFragmentManager().beginTransaction().add(R.id.viewPager, reviewRecentSubFragment).commit();
        EditText editText = (EditText) view.findViewById(R.id.editText);
        ImageButton button = (ImageButton) view.findViewById(R.id.write_imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context wrapper = new ContextThemeWrapper(getActivity().getApplicationContext(), R.style.PopUpMenuTheme);
//                PopupMenu popupMenu = new PopupMenu(wrapper, v);
//                getActivity().getMenuInflater().inflate(R.menu.write_more_menu, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        return false;
//                    }
//                });
//                popupMenu.show();
                final PowerMenu powerMenu = new PowerMenu.Builder(getContext())
                        .addItem(new PowerMenuItem("리뷰 쓰기", false))
                        .addItem(new PowerMenuItem(getResources().getString(R.string.write_QnA), false))
//                        .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT)
                        .setMenuRadius(5f)
                        .setMenuShadow(10f)
                        .setTextColor(getResources().getColor(R.color.colorMainBlack))
                        .setMenuColor(Color.WHITE)
                        .setBackgroundColor(getResources().getColor(R.color.colorTransparentBlack))
                        .build();

                powerMenu.setOnMenuItemClickListener(new OnMenuItemClickListener<PowerMenuItem>() {
                    @Override
                    public void onItemClick(int position, PowerMenuItem item) {
                        switch (position) {
                            case 0:
                                gotoReviewWritingActivity();
                                powerMenu.dismiss();
                                break;
                        }
                    }
                });

                LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearLayout);
                powerMenu.showAsAnchorRightTop(layout);

            }
        });

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

    private void gotoReviewWritingActivity() {
        Intent intent = new Intent(this.getActivity(), ReviewWritingActivity.class);
        startActivity(intent);
    }


}
