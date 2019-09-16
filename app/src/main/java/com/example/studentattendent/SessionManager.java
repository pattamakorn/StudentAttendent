package com.example.studentattendent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    public static final String USER = "USER";
    public static final String PASS = "PASSWORD";
    public static final String NAME = "NAME";
    private static final String LOGIN = "IS_LOGIN";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void creatsession(String name,String user){
        editor.putBoolean(LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(USER,user);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void  checklogin(){
        if(!this.isLoggin()){

            Intent i = new Intent(context,login.class);
            context.startActivity(i);
            ((MainActivity)context).finish();
        }
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String>user = new HashMap<>();
        user.put(NAME,sharedPreferences.getString(NAME,null));
        user.put(USER,sharedPreferences.getString(USER,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,login.class);
        context.startActivity(i);
        ((MainActivity)context).finish();
    }
}
