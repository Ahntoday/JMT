package com.example.jmt_2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    EditText nicknameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        nicknameEditText = (EditText) findViewById(R.id.nicknameEditText);


        findViewById(R.id.nextButton).setOnClickListener(onClickListener);
        findViewById(R.id.backButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.next:
                case R.id.nextButton:
                    signUp();
                    break;
                case R.id.backButton:
                    gotoStartActivity();
                    break;
            }
        }
    };

    private void signUp() {
        final String nickname = ((EditText) findViewById(R.id.nicknameEditText)).getText().toString();
        final String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.passwordCheckEditText)).getText().toString();

        if (nickname.length() > 0 && email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) {
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "onComplete: " + task.isSuccessful());
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    String cu = mAuth.getUid();
                                    String email = user.getEmail();

                                    Log.v("알림", "현재로그인한 유저 " + cu);
                                    Log.v("알림", "유저 이름 " + nickname);
                                    //이 부분이 DB에 데이터 저장
                                    UserData userdata = new UserData(nickname, email);
//                                    mDatabase = FirebaseDatabase.getInstance().getReference();
//                                    mDatabase.child("users").child(cu).setValue(userdata);
                                    database.getReference().child("users").child(cu).setValue(userdata);
                                    database.getReference().child("nickNameList").child(nickname).setValue(email);
                                    Toast.makeText(SignUpActivity.this, "FireBase 아이디 생성이 완료 되었습니다", Toast.LENGTH_SHORT).show();
                                    gotoCertificationActivity();

                                } else {
                                    if (task.getException() != null) {

                                    } else if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        gotoPopUpActivity();
                                    }
                                }
                                // ...
                            }
                        });
            } else {
                startToast("비밀번호가 일치하지 않습니다.");
            }

        } else {
            startToast("앗! 중간에 빠진 정보 입력해주세요!");
        }
    }

    private void gotoStartActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    private void gotoCertificationActivity() {
        Intent intent = new Intent(this, CertificationActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoPopUpActivity() {
        Intent intent = new Intent(this, PopUpActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoMainActivity() {
        Log.e("test", "buttowon");
        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
