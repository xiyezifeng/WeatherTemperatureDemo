package com.example.lixiao.basicdemo.app.mvp;

import com.example.lixiao.basicdemo.app.model.GlobalModel;

public abstract class BasePresenter<V , C> {

    protected GlobalModel model;
    protected V view;
    protected C context;

    public BasePresenter(){
        model = GlobalModel.instance();
    }
    public  void bindView(V v , C c){
        view = v;
        this.context = c;
    }
}
