package com.example.kp_home;

public class User {
    int id;
    String tenDN;
    String matKhau;
    String tenND;
    String email;
    String dob;

    public User() {
    }

    public User(int id, String tenDN, String matKhau, String tenND, String email, String dob) {
        this.id = id;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.tenND = tenND;
        this.email = email;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


}
