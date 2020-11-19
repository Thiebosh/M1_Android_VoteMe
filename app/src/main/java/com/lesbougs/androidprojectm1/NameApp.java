package com.lesbougs.androidprojectm1;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class NameApp extends Application {

    //public static

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    public void onCreate(){
        super.onCreate();

        sContext = getApplicationContext();
    }


    public static final String SHARED_PREFERENCES_FILE_NAME = "appNameSharedPrefs";
    public static final String PREF_LOGIN = "prefLogin";

    private static SharedPreferences getSharedPreferences(){
        return sContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static String getLogin(){
        final SharedPreferences prefs = getSharedPreferences();
        return prefs.getString(PREF_LOGIN, null);
    }

    public static void setLogin(String login){
        final SharedPreferences prefs = getSharedPreferences();
        prefs.edit().putString(PREF_LOGIN, login).apply();
    }
}
