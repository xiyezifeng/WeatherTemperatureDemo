package com.example.lixiao.basicdemo.app.ui.home.presenter;

import android.util.Log;

import com.example.lixiao.basicdemo.app.http.NetConsumer;
import com.example.lixiao.basicdemo.app.bean.ResponseBean;
import com.example.lixiao.basicdemo.app.bean.Student;
import com.example.lixiao.basicdemo.app.ui.home.viewimpl.HomeImpl.*;
import com.example.lixiao.basicdemo.app.model.NetModel;
import com.google.gson.Gson;

import java.util.List;


public  class HomePresenter extends Presenter {

    @Override
    public void getHomeData() {
        model.writeLoginData("你好啊");
        view.getHomeData(model.readLoginData());
    }

    @Override
    public void login(String name , String pwd) {
        NetModel.login(context,name,pwd)
                .subscribe(new NetConsumer(new NetConsumer.ResponseBack<Student>() {
                    @Override
                    public void onSuccess(ResponseBean<Student> responseBean) {
                        Log.e("HomePresenter", "111111   " + responseBean.msg);
                        model.writeLoginData(new Gson().toJson(responseBean.data));
                        view.login(responseBean);
                    }

                    @Override
                    public void onFail(ResponseBean<Student> responseBean) {
                        Log.e("HomePresenter", "111111    "+ responseBean.msg);
                        view.login(responseBean);
                    }
                }));
    }

    @Override
    public void testData() {
        NetModel.testData(context)
                .subscribe(new NetConsumer<Student>(new NetConsumer.ResponseBack() {
                    @Override
                    public void onSuccess(ResponseBean responseBean) {
                        Log.e("HomePresenter", "111111");
                    }

                    @Override
                    public void onFail(ResponseBean responseBean) {
                        Log.e("HomePresenter", "111111");
                    }
                }));
    }

    @Override
    public void testData1() {
        NetModel.testData1(context)
                .subscribe(new NetConsumer<List<Student>>(new NetConsumer.ResponseBack() {
                    @Override
                    public void onSuccess(ResponseBean responseBean) {
                        Log.e("HomePresenter", "111111");
                    }

                    @Override
                    public void onFail(ResponseBean responseBean) {
                        Log.e("HomePresenter", "111111");
                    }
                }));
    }

    @Override
    public void testData2() {
        NetModel.testData2(context)
                .subscribe(new NetConsumer<List<Student>>(new NetConsumer.ResponseBack() {
                    @Override
                    public void onSuccess(ResponseBean responseBean) {
                        Log.e("HomePresenter", "111111");
                    }

                    @Override
                    public void onFail(ResponseBean responseBean) {
                        Log.e("HomePresenter", "111111");
                    }
                }));
    }
}
