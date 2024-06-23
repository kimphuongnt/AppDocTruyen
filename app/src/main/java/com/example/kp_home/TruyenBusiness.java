package com.example.kp_home;

import android.content.Context;

import java.util.List;

public class TruyenBusiness {
    private final DBManager dbManager;

    public TruyenBusiness(Context context) {
        dbManager = new DBManager(context);
    }

    public List<Truyen> getNewTruyenBusiness(int limit) {
        return dbManager.getNewtruyen(limit);
    }

    public List<Truyen> getAllTruyen() {
        return dbManager.getAllTruyen();
    }

    public List<Truyen> searchTruyen(String query) {
        return dbManager.searchTruyen(query);
    }

    public List<Truyen> searchTacGia(String query) {
        return dbManager.searchTacGia(query);
    }

    public List<Truyen> getTruyenTheoTL(String theloai) {
        return dbManager.getTruyenTheoTL(theloai);
    }

    public List<Truyen> getTruyenTheoTT(String tinhTrang) {
        return dbManager.getTruyenTheoTT(tinhTrang);
    }

    public List<Truyen> getTruyenNgan() {
        return dbManager.getTruyenNgan();
    }

    public Truyen getTruyenByMaTruyen(int maTruyen)
    {
        return dbManager.getTruyenByMaTruyen(maTruyen);
    }

    public List<Truyen> getBangXepHangtruyen()
    {
        return dbManager.getBangXepHangtruyen();
    }

}
