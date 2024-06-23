package com.example.kp_home;

import android.content.Context;

import java.util.List;

public class TruyenDaLuuBusiness {
    private final DBManager dbManager;

    public TruyenDaLuuBusiness(Context context) {
        dbManager = new DBManager(context);
    }

    public boolean addTruyenDaLuu(int userID, int maTruyen) {
        return dbManager.addTruyenDaLuu(userID, maTruyen);
    }

    public boolean checkTruyenDaLuu(int userID, int maTruyen) {
        return dbManager.checkTruyenDaLuu(userID, maTruyen);


    }

    public List<TruyenDaLuu> getTruyenDaLuu(int userID) {
        return dbManager.getTruyenDaLuu(userID);
    }

    public boolean deleteTruyenDaLuu(TruyenDaLuu truyenDaLuu) {
        return dbManager.deleteTruyenDaLuu(truyenDaLuu);
    }

}
