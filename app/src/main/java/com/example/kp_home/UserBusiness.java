package com.example.kp_home;

import android.content.Context;
import android.content.SharedPreferences;

public class UserBusiness {
    private DBManager dbManager;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "UserPref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";

    public UserBusiness(Context context) {
        dbManager = new DBManager(context);
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public User login(String username, String password) {
        User user = dbManager.getUser(username, password);
        if (user != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_IS_LOGGED_IN, true);
            editor.putInt(KEY_USER_ID, user.getId());
            editor.apply();
        }
        return user;
    }

    public boolean register(User user) {
        return dbManager.addUser(user);
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.putInt(KEY_USER_ID, -1);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public User getLoggedInUser() {
        if (isLoggedIn()) {
            int userId = sharedPreferences.getInt(KEY_USER_ID, -1);
            return dbManager.getUserById(userId);
        }
        return null;
    }
    public int getCurrentUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, -1);
    }
    public boolean updateUser(User user) {
        return dbManager.updateUser(user);
    }
}
