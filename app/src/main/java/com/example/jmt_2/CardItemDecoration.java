package com.example.jmt_2;

import android.content.Context;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class CardItemDecoration extends RecyclerView.ItemDecoration {
    private final int size;

    public CardItemDecoration(Context context, int size) {
        this.size = dpToPx(context, size);
    }

    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = size;
    }

    private int dpToPx(@org.jetbrains.annotations.NotNull Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
