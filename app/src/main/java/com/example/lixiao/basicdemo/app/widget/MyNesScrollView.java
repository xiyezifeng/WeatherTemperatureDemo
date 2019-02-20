package com.example.lixiao.basicdemo.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.example.lixiao.basicdemo.R;

import java.util.ArrayList;


public class MyNesScrollView extends HorizontalScrollView {
    private WeatherView weatherView;

    public MyNesScrollView(Context context) {
        super(context);
        init();
    }

    public MyNesScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyNesScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        weatherView = new WeatherView(getContext());
        FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT);
        weatherView.setLayoutParams(params);
        addView(weatherView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (weatherView == null) {
            weatherView = findViewById(R.id.weather_view);
        }
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (weatherView != null) {
            weatherView.onScrollChange(l, t, oldl, oldt);
        }
    }

    public void initAllView(){
        weatherView.drawAllView();
    }

    public void setAirData(ArrayList<String> data){
        weatherView.setAirdata(data);
    }

    public void setTimeData(ArrayList<String> data){
        weatherView.setTimedata(data);
    }

    public void setTemperatureData(int temperature[]){
        weatherView.setTemperatureData(temperature);
    }

    public void setRechargingData(int temperature[]){
        weatherView.setRechargingData(temperature);
    }

    public void setBitmapRes(int[] bitmapRes) {
        weatherView.setBitmapRes(bitmapRes);
    }

    public void darwView(){
        weatherView.drawView();
    }
}
