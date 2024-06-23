package com.example.kp_home;

public class Truyen {
    private int maTruyen;
    private int soChuong;
    private String tenTruyen;
    private String tinhTrang;
    private String tacGia;
    private String anhBia;
    private String ngayRaMat;
    private int tongLike;
    private int maTheLoai;
    private String tenTheLoai;

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public Truyen(int maTruyen, String tenTruyen, String anhBia, String tacGia, String ngayRaMat, int soChuong, String tinhTrang, int maTheLoai) {
        this.maTruyen = maTruyen;
        this.tenTruyen = tenTruyen;
        this.anhBia = anhBia;
        this.tacGia = tacGia;
        this.ngayRaMat = ngayRaMat;
        this.soChuong = soChuong;
        this.tinhTrang = tinhTrang;
        this.maTheLoai = maTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public int getTongLike() {
        return tongLike;
    }

    public void setTongLike(int tongLike) {
        this.tongLike = tongLike;
    }

    public Truyen(int maTruyen, int soChuong, String tenTruyen, String tinhTrang, String tacGia, String ngayRaMat, String anhBia) {
        this.maTruyen = maTruyen;
        this.soChuong = soChuong;
        this.tenTruyen = tenTruyen;
        this.tinhTrang = tinhTrang;
        this.tacGia = tacGia;
        this.ngayRaMat = ngayRaMat;
        this.anhBia = anhBia;
    }

    public int getMaTruyen() {
        return maTruyen;
    }

    public Truyen() {
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }

    public int getSoChuong() {
        return soChuong;
    }

    public void setSoChuong(int soChuong) {
        this.soChuong = soChuong;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNgayRaMat() {
        return ngayRaMat;
    }

    public void setNgayRaMat(String ngayRaMat) {
        this.ngayRaMat = ngayRaMat;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }
}
