package com.example.kp_home;

public class TruyenDaLuu {
    int id;
    int userID;
    int maTruyen;
    String tenTruyen;

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public TruyenDaLuu(int id, int userID, int maTruyen) {
        this.id = id;
        this.userID = userID;
        this.maTruyen = maTruyen;
    }

    public TruyenDaLuu() {
    }

    public TruyenDaLuu(int id, int userID, int maTruyen, String tenTruyen) {
        this.id = id;
        this.userID = userID;
        this.maTruyen = maTruyen;
        this.tenTruyen = tenTruyen;
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

    public int getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }
}
