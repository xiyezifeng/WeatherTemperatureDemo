package com.example.lixiao.basicdemo.app.model;

import com.example.lixiao.basicdemo.support.utils.SharedPreferenceUtil;

public class CacheModel {
    private static final String LOGIN_KEY = "login_user";

    public static void writeTestData(String data){
        SharedPreferenceUtil.instance().saveValue(LOGIN_KEY,data);
    }

    public static String readTestData(){
        String data = SharedPreferenceUtil.instance().readValue(LOGIN_KEY,String.class);
        return data;
    }

}
