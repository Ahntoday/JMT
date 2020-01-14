package com.example.jmt_2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class SituationItemView extends LinearLayout {
    Button button;

    public SituationItemView(Context context) {
        super(context);
    }

    public SituationItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.situation_item_view, this, true);

        button = (Button) findViewById(R.id.situation_button);



    }

    public void setTextView(String text) {
        button.setText(text);
    }
}
