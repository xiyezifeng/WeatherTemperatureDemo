package com.example.lixiao.basicdemo.app.model;

public class GlobalModel {

    private static final GlobalModel GLOBAL_MODEL = new GlobalModel();

    private GlobalModel() {
    }
    public static GlobalModel instance(){
        return GLOBAL_MODEL;
    }


    public void writeLoginData(String data){
        CacheModel.writeTestData(data);
    }
    public String readLoginData(){
        return CacheModel.readTestData();
    }
}
