package com.example.jmt_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CommentItemView extends RelativeLayout {
    TextView nickName;
    TextView commentText;
    ImageView imageView;

    public CommentItemView(Context context) {
        super(context);
        init(context);
    }
    
    public CommentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.comment_item_view_footer, this, true);

        nickName = (TextView) findViewById(R.id.comment_nickname);
        imageView = (ImageView) findViewById(R.id.comment_imageView);
        commentText = (TextView) findViewById(R.id.comment_content);
    }

    public void setImageView(int resId) {
        imageView.setImageResource(resId);
    }

    public void setImageView(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public void setNickname(String nickname) {
        nickName.setText(nickname);
    }

    public void setComment(String comment) {
        commentText.setText(comment);
    }

}
