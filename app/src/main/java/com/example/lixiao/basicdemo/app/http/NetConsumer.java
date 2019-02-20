package com.example.lixiao.basicdemo.app.http;

import com.example.lixiao.basicdemo.app.bean.ResponseBean;

import io.reactivex.functions.Consumer;


public class NetConsumer<T> implements Consumer<ResponseBean<T>> {


    ResponseBack<T> back;

    public NetConsumer(ResponseBack<T> back) {
        this.back = back;
    }

    @Override
    public void accept(ResponseBean<T> stringResponseBean) {
        if (stringResponseBean.code < 0) {
            back.onFail(stringResponseBean);
            return;
        }
        if (stringResponseBean.code > 0) {
            back.onFail(stringResponseBean);
            return;
        }
        if (stringResponseBean.code == 0) {
            back.onSuccess(stringResponseBean);
            return;
        }
    }

    public interface ResponseBack<T>{
        void onSuccess(ResponseBean<T> tResponseBean);
        void onFail(ResponseBean<T> tResponseBean);
    }
}
