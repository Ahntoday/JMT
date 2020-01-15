package com.example.jmt_2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ReviewWritingActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_writing);

        textView = (TextView) findViewById(R.id.review_writing_storeName);
        textView.setText(getIntent().getStringExtra("storeName")+"");
    }
}
