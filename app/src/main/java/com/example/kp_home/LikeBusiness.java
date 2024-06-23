package com.example.kp_home;

import android.content.Context;

public class LikeBusiness {
    private DBManager dbManager;

    public LikeBusiness(Context context) {
        dbManager = new DBManager(context);
    }

    public boolean addOrUpdateLike(int userId, int maTruyen, boolean isLike) {
        return dbManager.addLike(userId, maTruyen, isLike);
    }
    public int getSoLike(int maTruyen)
    {
        return dbManager.getSoLike(maTruyen);
    }

    public int getSoDislike(int maTruyen)
    {
        return dbManager.getSoDislike(maTruyen);
    }
}
