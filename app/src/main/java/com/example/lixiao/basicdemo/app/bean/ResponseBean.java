package com.example.lixiao.basicdemo.app.bean;

public class ResponseBean<T> {
    public boolean status;
    public int code;
    public String msg;
    public T data;

    public ResponseBean() {
    }

    public ResponseBean(boolean status, int code, String msg, T t) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = t;
    }
}
