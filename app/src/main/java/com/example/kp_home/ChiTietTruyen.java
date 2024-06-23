package com.example.kp_home;

public class ChiTietTruyen {
    String maTruyen;
    String maChuong;
    String tenChuong;
    String noiDung;

    int soLike;
    int soDislike;

    public int getSoLike() {
        return soLike;
    }

    public void setSoLike(int soLike) {
        this.soLike = soLike;
    }

    public int getSoDislike() {
        return soDislike;
    }

    public void setSoDislike(int soDislike) {
        this.soDislike = soDislike;
    }

    public ChiTietTruyen() {
    }

    public ChiTietTruyen(String maTruyen, String maChuong, String tenChuong, String noiDung, int soLike, int soDislike) {
        this.maTruyen = maTruyen;
        this.maChuong = maChuong;
        this.tenChuong = tenChuong;
        this.noiDung = noiDung;
        this.soLike = soLike;
        this.soDislike = soDislike;
    }

    public String getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(String maTruyen) {
        this.maTruyen = maTruyen;
    }

    public String getMaChuong() {
        return maChuong;
    }

    public void setMaChuong(String maChuong) {
        this.maChuong = maChuong;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
