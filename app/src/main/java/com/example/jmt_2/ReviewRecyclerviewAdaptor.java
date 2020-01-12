package com.example.jmt_2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewRecyclerviewAdaptor extends RecyclerView.Adapter<ReviewRecyclerviewAdaptor.ViewHolder> {
    private ArrayList<ReviewData> reviewData;
    private CommentAdapter commentAdapter;

    public ReviewRecyclerviewAdaptor(ArrayList<ReviewData> reviewData) {
        this.reviewData = reviewData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item_view, parent, false);
        commentAdapter = new CommentAdapter(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.textNickName.setText(reviewData.get(i).nickName);
        holder.storeInfo.setText(reviewData.get(i).storeNameLocation);
        holder.reviewContent.setText(reviewData.get(i).reviewContent);
        holder.userImage.setImageResource(reviewData.get(i).userImg);
        holder.foodImage.setImageResource(reviewData.get(i).reviewImg);
        holder.listView.setAdapter(commentAdapter);
        commentAdapter.setItems(reviewData.get(i).getItems());

        setListViewHeightBasedOnChildren(holder.listView);

    }


    @Override
    public int getItemCount() {
        return reviewData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView userImage;
        public ImageView foodImage;
        public TextView textNickName;
        public TextView storeInfo;
        public TextView reviewContent;
        public TextView likeCount;
        public ListView listView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = (ImageView) itemView.findViewById(R.id.comment_imageView);
            textNickName = (TextView) itemView.findViewById(R.id.nick_name);
            storeInfo = (TextView) itemView.findViewById(R.id.store_info);
            foodImage = (ImageView) itemView.findViewById(R.id.review_food_image);
            reviewContent = (TextView) itemView.findViewById(R.id.review_ment);
            likeCount = (TextView) itemView.findViewById(R.id.like_count);
            listView = (ListView) itemView.findViewById(R.id.comment_listView);
            listView.setVerticalScrollBarEnabled(false);

        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int itemcount = listAdapter.getCount();
        int totalHeight = 0;

        for (int i = 0; i < itemcount; i++) {
            View listItem = listAdapter.getView(i, null, listView);

            TextView comment = listItem.findViewById(R.id.comment_content);
            comment.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            totalHeight += comment.getMeasuredHeight() + 20;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

class ReviewData {
    public String nickName;
    public String storeNameLocation;
    public String reviewContent;
    public int userImg;
    public int reviewImg;
    private int likeCount;
    private ArrayList<CommentItem> items;

    public ReviewData (String nickName, String storeNameLocation, String reviewContent, int userImg, int reviewImg, ArrayList<CommentItem> items) {
        this.nickName = nickName;
        this.storeNameLocation = storeNameLocation;
        this.userImg = userImg;
        this.reviewImg = reviewImg;
        this.reviewContent = reviewContent;
        likeCount = 0;
        this.items = items;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStoreNameLocation() {
        return storeNameLocation;
    }

    public void setStoreNameLocation(String storeNameLocation) {
        this.storeNameLocation = storeNameLocation;
    }

    public ArrayList<CommentItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CommentItem> items) {
        this.items = items;
    }

    public void plusLikeCount() {
        likeCount++;
    }

    public void minusLikeCount() {
        likeCount--;
    }

}
