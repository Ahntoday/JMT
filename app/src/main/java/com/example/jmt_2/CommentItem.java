package com.example.jmt_2;

import android.graphics.Bitmap;

public class CommentItem {
    private String nickname;
    private String comment;
    private int resId;
    private Bitmap bitmap;
    private boolean isDrawable = false;

    public CommentItem(String nickname, String comment, int resId) {
        this.nickname = nickname;
        this.comment = comment;
        this.resId = resId;
        isDrawable = true;
    }

    public CommentItem(String nickname, String comment, Bitmap bitmap) {
        this.nickname = nickname;
        this.comment = comment;
        this.bitmap = bitmap;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
