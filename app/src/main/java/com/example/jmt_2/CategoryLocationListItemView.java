package com.example.jmt_2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CategoryLocationListItemView extends LinearLayout {
    TextView textView;
    ImageView imageView;
    RatingBar ratingBar;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    ImageButton imageButton;
    ImageButton imageButton2;

    public CategoryLocationListItemView(Context context) {
        super(context);
        init(context);
    }

    public CategoryLocationListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.category_location_list_item, this, true);

        textView = (TextView) findViewById(R.id.location_list_storeName);
        textView2 = (TextView) findViewById(R.id.location_list_reviewNum);
        textView3 = (TextView) findViewById(R.id.location_list_place);
        textView4 = (TextView) findViewById(R.id.location_list_menu);
        imageView = (ImageView) findViewById(R.id.location_list_imageView);
        imageButton = (ImageButton) findViewById(R.id.location_list_save_store);
        ratingBar = (RatingBar) findViewById(R.id.location_list_ratingBar);
        imageButton2 = (ImageButton) findViewById(R.id.location_list_save_store);
    }

    public void setStoreName(String name) {textView.setText(name);}
    public void setReviewNum(int num) {textView2.setText(num+"");}
    public void setPlace(String place) {textView3.setText(place);}
    public void setMenu(String menu) {textView4.setText(menu);}
    public void setImage(int resId) {imageView.setImageResource(resId);}
    public void setRatingBar(int starRate) {ratingBar.setRating(starRate);}
}
