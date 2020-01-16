package com.example.jmt_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewRecentSubFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReviewData> reviewData = new ArrayList<>();

    public ReviewRecentSubFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.review_recent_subfragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.review_recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ReviewRecyclerviewAdaptor(reviewData);
        recyclerView.setAdapter(adapter);

        ArrayList<CommentItem> commentItems = new ArrayList<>();
        ArrayList<CommentItem> commentItems1 = new ArrayList<>();
        ArrayList<CommentItem> commentItems2 = new ArrayList<>();

        commentItems.add(new CommentItem("나는야뀨", "마자ㅠㅠ 카츠 진짜 맛잇어용!", R.drawable.userimage));
        commentItems.add(new CommentItem("나는야뀨", "마자ㅠㅠ 카츠 진짜 맛잇어용!", R.drawable.userimage));
        commentItems1.add(new CommentItem("찌은", "라볶이도 맛있음!!", R.drawable.userimage));

//        reviewData.add(new ReviewData("서진쓰", "돈부리 가게, 상대", "솔직히 카츠 이 가격에 이 양이라니,,,, 감격,,,", R.drawable.temp_user_image_27dp, R.drawable.donburi, commentItems));
//        reviewData.add(new ReviewData("나는야뀨", "대왕김밥, 정문", "돈까스 파삭파삭 존맛탱", R.drawable.temp_user_image_27dp, R.drawable.dawang, commentItems1));
//        reviewData.add(new ReviewData("찌은", "길성유부, 후문", "가성비 갑!! 김치유부, 참치유부 다 맛있어유ㅠㅠ", R.drawable.temp_user_image_27dp, R.drawable.gilsung, commentItems2));
//        reviewData.add(new ReviewData("서진쓰", "돈부리 가게, 상대", "솔직히 카츠 이 가격에 이 양이라니,,,, 감격,,,", R.drawable.temp_user_image_27dp, R.drawable.donburi, commentItems));

        reviewData.add(new ReviewData("서진쓰", "돈부리 가게, 상대", "솔직히 카츠 이 가격에 이 양이라니,,,, 감격,,,", R.drawable.temp_user_image_27dp, R.drawable.donburi));
        reviewData.add(new ReviewData("나는야뀨", "대왕김밥, 정문", "돈까스 파삭파삭 존맛탱", R.drawable.temp_user_image_27dp, R.drawable.dawang));
        reviewData.add(new ReviewData("찌은", "길성유부, 후문", "가성비 갑!! 김치유부, 참치유부 다 맛있어유ㅠㅠ", R.drawable.temp_user_image_27dp, R.drawable.gilsung));
        reviewData.add(new ReviewData("서진쓰", "돈부리 가게, 상대", "솔직히 카츠 이 가격에 이 양이라니,,,, 감격,,,", R.drawable.temp_user_image_27dp, R.drawable.donburi));

        return view;
    }
}
