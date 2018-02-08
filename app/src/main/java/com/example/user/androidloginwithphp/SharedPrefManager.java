package com.example.user.androidloginwithphp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 10/25/2017.
 */

public class SharedPrefManager {

    private static SharedPrefManager Instance;
    private static Context ctx;

    private static final String Key_Pref = "Key_Pref";
    private static final String Key_Id = "Key_id";
    private static final String Key_Email = "Key_Email";

    private SharedPrefManager(Context context) {
        ctx = context;
    }

    private SharedPrefManager getInstance(Context context){
        if(Instance==null){
            Instance = new SharedPrefManager(context);
        }
        return Instance;
    }

    public boolean UserLogin(int id, String email) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Key_Pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Key_Id, id);
        editor.putString(Key_Email, email);
        editor.apply();
        return true;


    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Key_Pref, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(Key_Email, null) != null) {
            return true;
        }
        return false;


    }

    public boolean Logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Key_Pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


}
