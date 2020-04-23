package com.reepling.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.reepling.data.local.model.User;

/**
 * Created by Michaël on 24/03/2018.
 */

public class MySharedPrefs {

    public static final String TAG = MySharedPrefs.class.getSimpleName();

    public static final String SHARED_PREFS_KEY_NIGHT_MODE = "NIGHT_MODE";
    public static final String SHARED_PREFS_KEY_USER_THEME = "USER_THEME";
    public static final String SHARED_PREFS_KEY_USER_CONNECTED = "USER_CONNECTED";

    private static SharedPreferences mSharedPrefs;
/*
    private MySharedPrefs( Context context){
        this.mSharedPrefs = PreferenceManager.getDefaultSharedPreferences( context );
    }
    */

    private MySharedPrefs() {
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setSharedPrefsKeyNightMode(Context context, boolean isNightMode) {
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mSharedPrefs.edit().putBoolean(SHARED_PREFS_KEY_NIGHT_MODE, isNightMode).apply();
    }

    public static boolean getNightMode(Context context) {
        boolean boolValue = false;

        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolValue = mSharedPrefs.getBoolean(SHARED_PREFS_KEY_NIGHT_MODE, false);

        return boolValue;
    }


    public static void setSharedPrefsKeyUserTheme(Context context, int themeId) {
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mSharedPrefs.edit().putInt(SHARED_PREFS_KEY_USER_THEME, themeId).apply();
    }

    public static int getUserTheme(Context context) {
        int themeId;

        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        themeId = mSharedPrefs.getInt(SHARED_PREFS_KEY_USER_THEME, 0);

        Log.e(TAG, "getThemeId() - themeId is  : " + themeId);

        return themeId;
    }


    public static void setSharedPrefsKeyConnectedUser(Context context, User user) {
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mSharedPrefs.edit().putString(SHARED_PREFS_KEY_USER_CONNECTED, user.getUsername()).apply();
    }

    public static String getConnectedUser(Context context) {
        String username;

        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        username = mSharedPrefs.getString(SHARED_PREFS_KEY_USER_CONNECTED, "");

        Log.e(TAG, "getConnectedUser() - username is  : " + username);

        return username;
    }

}
