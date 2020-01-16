package com.example.jmt_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MypageFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseUser user;
    private TextView nickNameView;
    private ImageView imageView;
    private DatabaseReference mDatabase;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_fragment, container, false);

        nickNameView = (TextView) view.findViewById(R.id.userNickname);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
//        database = FirebaseDatabase.getInstance();
//        database.getReference().child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                nickNameView.setText(dataSnapshot.getValue(UserData.class).getUserName());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//
//            }
//        });

        view.findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
        imageView = view.findViewById(R.id.mypage_ImageView);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser cu = mAuth.getCurrentUser();
//        if(cu != null){
//            if(한식이 가장 많을 때){ switch
//                imageView.setImageResource(R.drawable.mypage_hansik);
//            }else if(양식이 가장 많을 때){
//                imageView.setImageResource(R.drawable.mypage_yangsik);
//            }else if(일식이 가장 많을 때){
//                imageView.setImageResource(R.drawable.mypage_ilsik);
//            }else{
//                imageView.setImageResource(R.drawable.mypage_basic);
//            }
//        imageView.setImageResource(R.drawable.mypage_basic);

        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logoutButton:
                    mAuth.signOut();
                    gotoLoginActivity();
                    break;
            }
        }
    };

    private void gotoLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

}
