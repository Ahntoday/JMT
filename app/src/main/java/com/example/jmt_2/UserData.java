package com.example.jmt_2;


public class UserData {
    private String userName;
    private String profile;
    private String myReview;

    public UserData(){

    }

    public UserData(String userName, String profile) {
        this.userName = userName;
        this.profile = profile;
//        this.myReview = myReview;

    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getProfile() {
        return profile;
    }


    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMyReview() {
        return myReview;
    }

    public void setMyReview(String myReview) {
        this.myReview = myReview;
    }
}

