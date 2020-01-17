package com.example.jmt_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewRecyclerviewAdaptor extends RecyclerView.Adapter<ReviewRecyclerviewAdaptor.ViewHolder> {
    private ArrayList<ReviewData> reviewData;
    private CommentAdapter commentAdapter;
    private boolean isClicked = false;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {

        holder.textNickName.setText(reviewData.get(i).getNickName());
        holder.storeInfo.setText(reviewData.get(i).getStoreName()+", "+reviewData.get(i).getPlace());
        holder.reviewContent.setText(reviewData.get(i).getReviewContent());
        holder.userImage.setImageResource(reviewData.get(i).getUserImg());
        holder.foodImage.setImageResource(reviewData.get(i).getReviewImg());
        holder.likeCount.setText(reviewData.get(i).getLikeCount()+"");

        holder.listView.setAdapter(commentAdapter);
        commentAdapter.setItems(reviewData.get(i).getItems());

        setListViewHeightBasedOnChildren(holder.listView);

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isClicked) {
                    isClicked = false;
                    reviewData.get(i).minusLikeCount();
                    holder.likeCount.setText(reviewData.get(i).getLikeCount()+"");
                    holder.likeButton.setBackground(v.getResources().getDrawable(R.drawable.ic_like_18dp));
                } else {
                    isClicked = true;
                    reviewData.get(i).plusLikeCount();
                    holder.likeCount.setText(reviewData.get(i).getLikeCount()+"");
                    holder.likeButton.setBackground(v.getResources().getDrawable(R.drawable.ic_like_clicked_18dp));
                }

            }
        });

        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton button = (ImageButton) v;

                if (button.getBackground() == v.getResources().getDrawable(R.drawable.ic_save_18dp)) {
                    button.setBackground(v.getResources().getDrawable(R.drawable.ic_save_selected_18dp));
                } else {
                    button.setBackground(v.getResources().getDrawable(R.drawable.ic_save_18dp));
                }

            }
        });


        holder.ratingBar.setNumStars(reviewData.get(i).getNumStar());

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
        public EditText editText;
        public ImageView commentImage;
        public TextView commentNickname;
        public ImageButton likeButton;
        private FirebaseAuth mAuth;
        private FirebaseDatabase database;
        private FirebaseUser user;
        public ImageButton saveButton;
        public RatingBar ratingBar;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            userImage = (ImageView) itemView.findViewById(R.id.comment_imageView);
            textNickName = (TextView) itemView.findViewById(R.id.nick_name);
            storeInfo = (TextView) itemView.findViewById(R.id.store_info);
            foodImage = (ImageView) itemView.findViewById(R.id.review_food_image);
            reviewContent = (TextView) itemView.findViewById(R.id.review_ment);
            likeCount = (TextView) itemView.findViewById(R.id.like_count);
            commentImage = (ImageView) itemView.findViewById(R.id.header_imageView);
            commentNickname = (TextView) itemView.findViewById(R.id.header_nickname);
            editText = (EditText) itemView.findViewById(R.id.editText);
            listView = (ListView) itemView.findViewById(R.id.comment_listView);
            listView.setVerticalScrollBarEnabled(false);
            likeButton = (ImageButton) itemView.findViewById(R.id.like_button);
            saveButton = (ImageButton) itemView.findViewById(R.id.save_button);
            likeCount.setText("0");
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

            mAuth = FirebaseAuth.getInstance();
            user = mAuth.getCurrentUser();
            database = FirebaseDatabase.getInstance();

            String cu = mAuth.getUid();

            if (cu != null) {
                database.getReference().child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        commentNickname.setText(dataSnapshot.getValue(UserData.class).getUserName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });

            } else {
                commentNickname.setText("익명");
                editText.setHint("♥로그인시 댓글을 달 수 있어욧!♥");
                editText.setFocusable(false);
                editText.setClickable(false);
            }

            commentImage.setImageResource(R.drawable.userimage);

            editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        String comment = editText.getText().toString();
                        InputMethodManager inputMethodManager = (InputMethodManager) itemView.getContext().getSystemService(MainActivity.INPUT_METHOD_SERVICE);

                        if (comment.trim().getBytes().length <= 0) {
                            Toast.makeText(itemView.getContext(), "댓글을 입력해주세요!", Toast.LENGTH_SHORT).show();
                            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                            editText.setText("");
                            editText.clearFocus();

                            return false;
                        }

                        CommentAdapter commentAdapter = (CommentAdapter) listView.getAdapter();

                        BitmapDrawable bitmapDrawable = (BitmapDrawable) commentImage.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();

                        commentAdapter.addItem(new CommentItem(commentNickname.getText().toString(), comment, bitmap));
                        commentAdapter.notifyDataSetChanged();
                        listView.setAdapter(commentAdapter);
                        setListViewHeightBasedOnChildren(listView);
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        editText.setText("");
                        editText.clearFocus();

                        return true;
                    }
                    return false;
                }
            });
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
            totalHeight += comment.getMeasuredHeight() + 25;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}

class ReviewData {
    private String userId;
    private String commentId;
    private String nickName;
    private String storeName;
    private String place;
    private String menu;
    private String keyword;
    private String reviewContent;
    private String menuEaten;
    private int numStar;
    private int userImg;
    private int reviewImg;
    private int likeCount = 0;
    private ArrayList<CommentItem> items;

    public ReviewData (String nickName, String storeName, int numStar, String place, String reviewContent, int userImg, int reviewImg, ArrayList<CommentItem> items) {
        this.nickName = nickName;
        this.storeName = storeName;
        this.place = place;
        this.userImg = userImg;
        this.numStar = numStar;
        this.reviewImg = reviewImg;
        this.reviewContent = reviewContent;
        likeCount = 0;
        this.items = items;
    }

    public ReviewData (String nickName, String storeName, String place, String reviewContent, int userImg, int reviewImg, ArrayList<CommentItem> items) {
        this.nickName = nickName;
        this.storeName = storeName;
        this.place = place;
        this.userImg = userImg;
        this.reviewImg = reviewImg;
        this.reviewContent = reviewContent;
        likeCount = 0;
        this.items = items;
    }

    public ReviewData (String userId, String nickName, String storeName, int numStar, String place, String reviewContent, String menuEaten) {
        this.nickName = nickName;
        this.storeName = storeName;
        this.place = place;
        this.reviewContent = reviewContent;
        this.menuEaten = menuEaten;
        this.numStar = numStar;
        this.userId = userId;
        likeCount = 0;
        userImg = R.drawable.temp_user_image_20dp;
        items = new ArrayList<>();
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getNumStar() {
        return numStar;
    }

    public void setNumStar(int numStar) {
        this.numStar = numStar;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }

    public int getReviewImg() {
        return reviewImg;
    }

    public void setReviewImg(int reviewImg) {
        this.reviewImg = reviewImg;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMenuEaten() {
        return menuEaten;
    }

    public void setMenuEaten(String menuEaten) {
        this.menuEaten = menuEaten;
    }
}
