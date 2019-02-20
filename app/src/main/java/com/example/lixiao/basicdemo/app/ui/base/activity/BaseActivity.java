package com.example.lixiao.basicdemo.app.ui.base.activity;


import android.content.Context;
import android.os.Bundle;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import androidx.annotation.Nullable;

public class BaseActivity extends RxAppCompatActivity {
    protected String TAG = getClass().getSimpleName();
    protected Context context;
    //弹窗
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    public void showAlter(){

    }

    public void hindAlter(){

    }


}
