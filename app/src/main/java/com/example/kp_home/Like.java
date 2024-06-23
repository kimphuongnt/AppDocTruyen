package com.example.kp_home;

public class Like {
    int id;
    int userID;
    String maTruyen;

    Boolean isLike;

    public Like() {
    }

    public Like(int id, int userID, String maTruyen, Boolean isLike) {
        this.id = id;
        this.userID = userID;
        this.maTruyen = maTruyen;
        this.isLike = isLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(String maTruyen) {
        this.maTruyen = maTruyen;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}
