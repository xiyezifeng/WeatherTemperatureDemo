package com.example.lixiao.basicdemo.support.netsys;


import com.example.lixiao.basicdemo.app.bean.ResponseBean;

import java.net.ConnectException;

import io.reactivex.functions.Function;
import retrofit2.HttpException;

public class HttpError<T> implements Function<Throwable, ResponseBean<T>> {

    @Override
    public ResponseBean<T> apply(Throwable throwable) throws Exception {
        if (throwable instanceof ConnectException) {
            return new ResponseBean<T>(false,-1,"没有网络",null);
        }else if (throwable instanceof HttpException)
            return new ResponseBean<T>(false,-1,"失败",null);
        return null;
    }
}
