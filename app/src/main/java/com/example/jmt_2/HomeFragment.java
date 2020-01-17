package com.example.jmt_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CardData> cardData = new ArrayList<>();
    private Button filterButton;
    private ImageButton searchButton;
    private TextView textView;
    private TextView textView2;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    String menuHash[] = {"#전체", "#한식", "#분식", "#양식", "#일식", "#중식", "#치킨&피자", "#패스트푸드", "#카페&주점"};
    String keywordHash[] = {"#가성비", "#혼밥", "#과제", "#빨리나오는", "#분위기", "#팀플", "#또오고싶은", "#회식", "#여기별로임"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerviewAdaptor(cardData);
        recyclerView.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        String tagMenu = getActivity().getIntent().getStringExtra("tagMenu");
        String tagPlace = getActivity().getIntent().getStringExtra("tagPlace");
        String tagKeyword = getActivity().getIntent().getStringExtra("tagKeyword");

        if (tagMenu == null) {
            cardData.add(new CardData("길성유부", "#후문 #분식 #가성비", R.drawable.gilsung));
            cardData.add(new CardData("맛골", "#예대 #한식 #또오고싶은", R.drawable.hj_matgol));
            cardData.add(new CardData("예향정", "#후문 #한식 #분위기", R.drawable.usa_yehyangjeong));
            cardData.add(new CardData("대왕김밥", "#정문 #분식 #또오고싶은", R.drawable.dawang));
            cardData.add(new CardData("돈부리가게", "#상대 #일식 #가성비", R.drawable.donburi));
        } else {
            Log.e("tagMenu", tagMenu+"");
            Log.e("tagPlace", tagPlace+"");

            final ArrayList<String> dataPlace = new ArrayList<>();
            final ArrayList<String> dataFinal = new ArrayList<>();

            database.getReference().child("jmtMarket").child("locations").child(tagPlace).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    dataPlace.add(dataSnapshot.getKey());
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            database.getReference().child("jmtMarket").child("menus").child(tagMenu).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    for (String name : dataPlace) {
                        if (name.equals(dataSnapshot.getKey())) {
                            dataFinal.add(name);
                            break;
                        }
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            database.getReference().child("jmtMarket").child("stores").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    for (String name : dataFinal) {
                        if (name.equals(dataSnapshot.getKey())) {
                            String finalMenuTag = dataSnapshot.child("menu").getValue().toString();
                            String finalKeywordTag = dataSnapshot.child("keyword").getValue().toString();

                            char[] tag = finalMenuTag.toCharArray();
                            String res = menuHash[Integer.parseInt(String.valueOf((tag[tag.length-1])))];
                            Log.e("dataFinal", res+"");
                            char[] tag1 = finalKeywordTag.toCharArray();
                            String res1 = keywordHash[Integer.parseInt(String.valueOf(tag1[tag1.length-1]))];
                            Log.e("dataFinal", res1+"");

                            cardData.add(new CardData(name, res+" "+res1, R.drawable.gilsung));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }



        recyclerView.addItemDecoration(new CardItemDecoration(getContext(), 20));

        filterButton = (Button) view.findViewById(R.id.homeFilterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFilterActivity();
            }
        });

        Intent intent = getActivity().getIntent();

        textView = (TextView) view.findViewById(R.id.textPlace);
        textView2 = (TextView) view.findViewById(R.id.textMenuMent);

        String textPlace = intent.getStringExtra("textPlace");
        String textMenuMent = intent.getStringExtra("textMenuMent");
        String textMenu = intent.getStringExtra("textMenu");
        String textKeyword = intent.getStringExtra("textKeyword");

        if (textPlace == null && textMenuMent == null) {
            textView.setText("전대");
            textView2.setText("맛있는 식사");
        } else if (textMenuMent == null) {
            textView.setText(textPlace);
            textView2.setText("맛있는 식사");
        } else if (textPlace == null) {
            textView.setText("전대");
            textView2.setText(textMenuMent);
        } else {
            textView.setText(textPlace);
            textView2.setText(textMenuMent);
        }

        if (textMenu == null) {
            textMenu = "오늘";
        }

        if (textKeyword == null) {
            textKeyword = "뭐먹징?";
        }

        String textFilterButton = "#" + textView.getText().toString() + " #" + textMenu + " #" + textKeyword;
        filterButton.setText(textFilterButton);

        searchButton = (ImageButton) view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                gotoSearchActivity();
            }
        });
        return view;
    }

    private void gotoFilterActivity() {
        Intent intent = new Intent(this.getActivity(), FilteringActivity.class);
        startActivity(intent);
    }

    private void gotoSearchActivity(){
        Intent intent = new Intent(this.getActivity(), SearchActivity.class);
        startActivity(intent);
    }
}
