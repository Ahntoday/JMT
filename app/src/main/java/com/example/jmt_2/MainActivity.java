package com.example.jmt_2;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment = new HomeFragment();
    private CategoryFragment categoryFragment = new CategoryFragment();
    private ReviewFragment reviewFragment = new ReviewFragment();
    private MypageFragment mypageFragment = new MypageFragment();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("test","mainactivity");
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, homeFragment).commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        bottomNavigationView.setOnNavigationItemSelectedListener(new NavigationSelectedListener());
        bottomNavigationView.setItemIconTintList(null);





    }

    class NavigationSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commitAllowingStateLoss();
                    break;
                case R.id.navigation_browsing:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new CategoryFragment()).commitAllowingStateLoss();
                    break;
                case R.id.navigation_review:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ReviewFragment()).commitNowAllowingStateLoss();
                    break;
                case R.id.navigation_mypage:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MypageFragment()).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }


}


