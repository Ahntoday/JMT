package com.example.jmt_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CardData> cardData = new ArrayList<>();
    private Button filterButton;
    private TextView textView;
    private TextView textView2;

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

        cardData.add(new CardData("길성유부", "#분식 #가성비", R.drawable.gilsung));
        cardData.add(new CardData("돈부리 가게", "#일식 #가성비", R.drawable.donburi));
        cardData.add(new CardData("대왕 김밥", "#분식 #또오고싶은", R.drawable.dawang));

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

        return view;
    }

    private void gotoFilterActivity() {
        Intent intent = new Intent(this.getActivity(), FilteringActivity.class);
        startActivity(intent);
    }
}
