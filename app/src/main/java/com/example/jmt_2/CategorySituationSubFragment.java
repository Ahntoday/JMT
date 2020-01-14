package com.example.jmt_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategorySituationSubFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SituationItemData> situationItemData = new ArrayList<>();

    public CategorySituationSubFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.category_situation_subfragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.situation_recyclerView);

        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SituationRecyclerviewAdaptor(situationItemData);
        recyclerView.setAdapter(adapter);

        situationItemData.add(new SituationItemData("# PC방 맛집"));
        situationItemData.add(new SituationItemData("# 해장히기 좋은"));
        situationItemData.add(new SituationItemData("# 어색한 시이일 때"));


        return view;
    }

}
