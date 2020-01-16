package com.example.jmt_2;

public class CategoryLocationListItem {
    String storeName;
    int resId;
    int starRate;
    int reviewNum;
    String place;
    String menu;

    public CategoryLocationListItem(String storeName, int resId, int starRate, int reviewNum, String place, String menu) {
        this.storeName = storeName;
        this.resId = resId;
        this.starRate = starRate;
        this.reviewNum = reviewNum;
        this.place = place;
        this.menu = menu;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
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
}
