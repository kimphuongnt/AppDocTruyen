package com.example.kp_home;

import android.content.Context;

import java.util.List;

public class BinhLuanBusiness {
    private DBManager dbManager;

    public BinhLuanBusiness(Context context) {
        dbManager = new DBManager(context);
    }

    public boolean addBinhLuan(int maTruyen, int userId, String noiDung) {
        return dbManager.addBinhLuan(maTruyen, userId, noiDung);
    }

    public List<BinhLuan> getBinhLuan(int maTruyen) {
        return dbManager.getBinhLuan(maTruyen);
    }

    public void updateComment(int maTruyen, int userID, String ngayBinhLuan, String newNoiDung) {
        dbManager.updateComment(maTruyen, userID, ngayBinhLuan, newNoiDung);
    }
}
