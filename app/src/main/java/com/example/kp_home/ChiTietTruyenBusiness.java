package com.example.kp_home;

import android.content.Context;

import java.util.List;

public class ChiTietTruyenBusiness {
    private DBManager dbManager;

    public ChiTietTruyenBusiness(Context context) {
        dbManager = new DBManager(context);
    }

    public List<ChiTietTruyen> getDanhSachChuong(int maTruyen) {
        return dbManager.getDanhSachChuong(maTruyen);
    }

    public ChiTietTruyen getNoiDungChuong(int maTruyen, int maChuong) {
        return dbManager.getNoiDungChuong(maTruyen, maChuong);
    }
}
