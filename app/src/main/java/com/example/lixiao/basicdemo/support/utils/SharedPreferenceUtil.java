package com.example.lixiao.basicdemo.support.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private SharedPreferences sharedPreferences;
    private static SharedPreferenceUtil sharedPreferenceUtill;
    private SharedPreferenceUtil() {}

    public void init(Context appcContext , String name , int mode){
        if (sharedPreferences == null) {
            sharedPreferences = appcContext.getSharedPreferences(name, mode);
        }
    }

    public static SharedPreferenceUtil instance(){
        if (sharedPreferenceUtill == null) {
            synchronized ("123") {
                if (sharedPreferenceUtill == null) {
                    sharedPreferenceUtill = new SharedPreferenceUtil();
                }
            }
        }
        return sharedPreferenceUtill;
    }

    public void saveValue(String key , Object value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        }
        editor.commit();
    }

    public <T> T readValue(String key , Class<T> value){
        if (value == String.class) {
            return (T) sharedPreferences.getString(key, null);
        } else if (value == Boolean.class) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(key, false));
        } else if (value == Integer.class) {
            return (T) Integer.valueOf(sharedPreferences.getInt(key, 0));
        } else if (value == Float.class) {
            return (T) Float.valueOf(sharedPreferences.getFloat(key, 0));
        }
        return null;
    }
}
