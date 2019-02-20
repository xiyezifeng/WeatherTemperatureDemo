package com.example.lixiao.basicdemo.app.ui.base.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.lixiao.basicdemo.app.mvp.BasePresenter;

import java.lang.reflect.ParameterizedType;

public abstract  class MVPActivity<P extends BasePresenter> extends BaseActivity{
    public P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getT(this, 0);
        bindVToP();
    }

    public <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (
                    o.getClass()
                            .getGenericSuperclass()
            )).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (ClassCastException e) {
        }
        return null;
    }

    public abstract void bindVToP();
}
