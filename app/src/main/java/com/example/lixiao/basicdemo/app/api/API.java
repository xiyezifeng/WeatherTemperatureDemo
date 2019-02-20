package com.example.lixiao.basicdemo.app.api;

import com.example.lixiao.basicdemo.app.bean.MixBean;
import com.example.lixiao.basicdemo.app.bean.ResponseBean;
import com.example.lixiao.basicdemo.app.bean.Student;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface API {

    @GET("s")
    Observable<ResponseBean<String>> user_login();

    @GET("td")
    Observable<ResponseBean<Student>> test_data();

    @GET("tdl")
    Observable<ResponseBean<List<Student>>> test_data_list();

    @GET("index3")
    Observable<ResponseBean<MixBean>> test_data_list_2();

    @GET("login")
    Observable<ResponseBean<Student>> login(@Query("name") String name , @Query("pwd") String pwd);

}
