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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MypageFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseUser user;
    private TextView nickNameView;
    private ImageView imageView;
    private DatabaseReference mDatabase;
    private TextView ment_start;
    private TextView ment_end;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_fragment, container, false);

        nickNameView = (TextView) view.findViewById(R.id.nickNameView);
        ment_start = (TextView) view.findViewById(R.id.mypage_ment_adj);
        ment_end = (TextView) view.findViewById(R.id.mypage_ment_n);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        String cu = mAuth.getUid();

        if (cu !=null) {
            database.getReference().child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    nickNameView.setText(dataSnapshot.getValue(UserData.class).getUserName()+"님 반갑습니다.");
                    ment_start.setText("\"리뷰를 작성해 주세요 !\"");
                    ment_end.setText("");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            nickNameView.setText("");
            ment_start.setText("\"로그인을 해주세요 !\"");
            ment_end.setText("");
        }

        view.findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
        imageView = view.findViewById(R.id.mypage_ImageView);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser cu_firebase = mAuth.getCurrentUser();

//        if(cu_firebase != null){
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
