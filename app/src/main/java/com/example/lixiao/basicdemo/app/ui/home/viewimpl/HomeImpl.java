package com.example.lixiao.basicdemo.app.ui.home.viewimpl;

import com.example.lixiao.basicdemo.app.bean.ResponseBean;
import com.example.lixiao.basicdemo.app.bean.Student;
import com.example.lixiao.basicdemo.app.ui.home.view.HomeActivity;
import com.example.lixiao.basicdemo.app.mvp.BasePresenter;

public interface  HomeImpl {

    abstract class Presenter extends BasePresenter<View,HomeActivity> {
         public abstract void getHomeData();
         public abstract void testData();
         public abstract void testData1();
         public abstract void testData2();
         public abstract void login(String name , String pwd);

    }

    interface  View  {
        void getHomeData(String data);
        void testData();
        void testData1();
        void login(ResponseBean<Student> user);
    }
}
