package com.example.lixiao.basicdemo.app.model;



import com.example.lixiao.basicdemo.app.api.API;
import com.example.lixiao.basicdemo.app.bean.ResponseBean;
import com.example.lixiao.basicdemo.support.netsys.RetrofitUtils;
import com.trello.rxlifecycle3.LifecycleProvider;


import java.net.ConnectException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NetModel {
    private static final String TAG = "NetModel";

    private static <T>Observable<T>  init(final Observable<T>  observable , LifecycleProvider lifecycleProvider){
        return observable.compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, ResponseBean>() {
                    @Override
                    public ResponseBean apply(Throwable throwable) throws Exception {
                        if (throwable instanceof ConnectException) {
                            return new ResponseBean<T>(false,-1,"没有网络",null);
                        }else
                            return new ResponseBean<T>(false,-1,"失败",null);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static API api(){
        return RetrofitUtils.get().retrofit().create(API.class);
    }

    public static Observable<ResponseBean<String>> login(String name , String psw , LifecycleProvider lifecycleProvider){
        return init(api().user_login(),lifecycleProvider);
    }

    public static Observable testData(LifecycleProvider lifecycleProvider){
        return init(api().test_data(),lifecycleProvider);
    }

    public static Observable testData1(LifecycleProvider lifecycleProvider){
        return init(api().test_data_list(),lifecycleProvider);
    }

    public static Observable testData2(LifecycleProvider lifecycleProvider){
        return init(api().test_data_list_2(),lifecycleProvider);
    }

    public static Observable login(LifecycleProvider lifecycleProvider ,  String  name , String pwd){
        return init(api().login(name,pwd),lifecycleProvider);
    }
}
