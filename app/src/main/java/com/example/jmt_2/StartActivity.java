package com.example.jmt_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    StartOne startOne;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

//        startOne = new StartOne();
//        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, startOne).commit();

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoStartButton).setOnClickListener(onClickListener);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        if (mAuth.getCurrentUser() != null) {
            gotoMainActivity();
        }


//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            gotoMainActivity();
//                        } else {
//                            gotoStartActivity();
//                        }
//                    }
//                });
        final LinearLayout indi_layout = (LinearLayout) findViewById(R.id.indi);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i < indi_layout.getChildCount(); i++){
                    ImageView indi = (ImageView) indi_layout.getChildAt(i);
                    indi.setImageResource(R.drawable.page_indi_non);
                }
                ImageView t_indi = (ImageView) indi_layout.getChildAt(position);
                t_indi.setImageResource(R.drawable.page_indi);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loginButton:
                    gotoLoginActivity();
                    break;
                case R.id.signUpButton:
                    gotoSignupActivity();
                    break;
                case R.id.gotoStartButton:
                    gotoMainActivity();
                    break;
            }
        }
    };

//
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    if (user != null) {
//        // Name, email address, and profile photo Url
//        String name = user.getDisplayName();
//
//        // Check if user's email is verified
//        boolean emailVerified = user.isEmailVerified();
//
//        // The user's ID, unique to the Firebase project. Do NOT use this value to
//        // authenticate with your backend server, if you have one. Use
//        // FirebaseUser.getIdToken() instead.
//        String uid = user.getUid();
//    }


    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void gotoSignupActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void gotoMainActivity() {
        Log.e("test", "buttowon");
        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void gotoStartActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

}
