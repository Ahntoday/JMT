package com.example.jmt_2;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReviewQnaSubFragment extends Fragment {


    public ReviewQnaSubFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.review_qna_subfragment, container, false);

        TextView textView = view.findViewById(R.id.chonnam_qna);

        Spannable span = new SpannableString(textView.getText());
        span.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.colorMainGreen)), 0, span.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(span);

        return view;
    }
}
