package com.example.jmt_2;

public class CommentItem {
    private String nickname;
    private String comment;
    private int resId;

    public CommentItem(String nickname, String comment, int resId) {
        this.nickname = nickname;
        this.comment = comment;
        this.resId = resId;
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

}
