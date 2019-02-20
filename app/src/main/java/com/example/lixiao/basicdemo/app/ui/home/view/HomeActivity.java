package com.example.lixiao.basicdemo.app.ui.home.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.bean.ResponseBean;
import com.example.lixiao.basicdemo.app.bean.Student;
import com.example.lixiao.basicdemo.app.ui.base.activity.MVPActivity;
import com.example.lixiao.basicdemo.app.ui.home.presenter.HomePresenter;
import com.example.lixiao.basicdemo.app.ui.home.viewimpl.HomeImpl.*;


public class HomeActivity extends MVPActivity<HomePresenter> implements View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter.getHomeData();
        findViewById(R.id.button).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.testData();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.testData1();
            }
        });
        final EditText name = findViewById(R.id.editText3);
        final EditText pwd = findViewById(R.id.editText2);
        findViewById(R.id.button3).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String strName = name.getText().toString();
                String strPwd = pwd.getText().toString();
                presenter.login(strName,strPwd);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.testData2();
            }
        });
    }

    @Override
    public void bindVToP() {
        presenter.bindView(this,this);
    }


    @Override
    public void getHomeData(String data) {
        Log.e(TAG, data);
    }


    @Override
    public void testData() {

    }

    @Override
    public void testData1() {

    }

    @Override
    public void login(ResponseBean<Student> user) {
        if (user.status) {
            Toast.makeText(context,user.msg,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,user.msg,Toast.LENGTH_SHORT).show();
        }
    }
}
