package com.example.kp_home;

import android.content.Intent;

public class BinhLuan {
    String maTruyen;
    int userID;
    String noiDung;
    private String tenND;
    String ngayBinhLuan;

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public BinhLuan() {
    }

    public BinhLuan(String maTruyen,  int userID, String noiDung, String ngayBinhLuan) {
        this.maTruyen = maTruyen;
        this.userID = userID;
        this.noiDung = noiDung;
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public String getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(String maTruyen) {
        this.maTruyen = maTruyen;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(String ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }
}
