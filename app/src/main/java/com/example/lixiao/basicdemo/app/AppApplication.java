package com.example.lixiao.basicdemo.app;

import android.app.Application;
import android.content.Context;

import com.example.lixiao.basicdemo.app.config.Constant;
import com.example.lixiao.basicdemo.support.netsys.RetrofitUtils;
import com.example.lixiao.basicdemo.support.utils.SharedPreferenceUtil;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceUtil.instance().init(this,"APP",Context.MODE_PRIVATE);
        RetrofitUtils.BASE_API = Constant.BASE_URL;
    }
}
