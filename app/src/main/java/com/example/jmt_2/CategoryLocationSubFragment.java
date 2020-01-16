package com.example.jmt_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class CategoryLocationSubFragment extends Fragment {
    ImageButton button;
    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;

    public CategoryLocationSubFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.category_location_subfragment, container, false);

        button = (ImageButton) view.findViewById(R.id.geonchulwoo_button);
        button1 = (ImageButton) view.findViewById(R.id.art_button);
        button2 = (ImageButton) view.findViewById(R.id.sangdae_button);
        button3 = (ImageButton) view.findViewById(R.id.back_gate_button);
        button4 = (ImageButton) view.findViewById(R.id.main_gate_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCategoryLocationListActivity("전철우");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCategoryLocationListActivity("예대");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCategoryLocationListActivity("상대");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCategoryLocationListActivity("후문");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCategoryLocationListActivity("정문");
            }
        });

        return view;
    }

    private void gotoCategoryLocationListActivity(String location) {
        Intent intent = new Intent(getActivity(), CategoryLocationListActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }
}
