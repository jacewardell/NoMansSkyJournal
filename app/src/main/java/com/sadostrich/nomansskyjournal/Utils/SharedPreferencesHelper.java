package com.sadostrich.nomansskyjournal.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by jacewardell on 8/8/16.
 */
public class SharedPreferencesHelper {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public static void saveLoginInfo(Context context, String username, String password) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public static String[] getLoginInfo(Context context) {
        String[] loginInfo = new String[2];
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        loginInfo[0] = prefs.getString(USERNAME, "");
        loginInfo[1] = prefs.getString(PASSWORD, "");
        return loginInfo;
    }

    public static void logout(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit();
    }
}
