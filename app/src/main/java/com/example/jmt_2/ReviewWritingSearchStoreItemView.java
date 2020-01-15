package com.example.jmt_2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ReviewWritingSearchStoreItemView extends LinearLayout {
    TextView textView;

    public ReviewWritingSearchStoreItemView(Context context) {
        super(context);
        init(context);
    }

    public ReviewWritingSearchStoreItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.review_writing_search_store_item, this, true);

        textView = (TextView) findViewById(R.id.search_result_textView);

    }
    public void setTextView(String name) {
        textView.setText(name);
    }
}
