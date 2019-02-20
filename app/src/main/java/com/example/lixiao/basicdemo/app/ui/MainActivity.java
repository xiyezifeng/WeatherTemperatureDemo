package com.example.lixiao.basicdemo.app.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.lixiao.basicdemo.app.widget.MyNesScrollView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.ui.base.activity.BaseActivity;
import com.example.lixiao.basicdemo.app.ui.home.view.HomeActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private int temperature[] = {
            -40 ,
            -4,
            -30,
            -12,
            7,
            -1,
            10,
            15,
            23,
            40,
            18,
            10,
            -1,
            -2,
            -2,
            -22,
            -33,
            2,
            20,
            23,
            10,
            17,
            15,
            10
    };

    private int recharging[] = {
            0,4,12,15,18,22,23
    };

    private int bitMapRes[] ={
            R.mipmap.ic_launcher_round,
            R.mipmap.weather_sun,
            R.mipmap.weather_sun,
            R.mipmap.weather_sun,
            R.mipmap.weather_sun,
            R.mipmap.weather_sun,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<String> timeData = new ArrayList<>();
        ArrayList<String> airData = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            timeData.add(i + ":00");
            airData.add("666 重度");
        }

        MyNesScrollView nesScrollView = findViewById(R.id.nesview);
        MyNesScrollView nesScrollView1 = findViewById(R.id.nesview_1);


        nesScrollView.setBitmapRes(bitMapRes);
        nesScrollView.setRechargingData(recharging);
        nesScrollView.setTemperatureData(temperature);
        nesScrollView.setTimeData(timeData);
        nesScrollView.setAirData(airData);


        nesScrollView.initAllView();


        nesScrollView1.setBitmapRes(bitMapRes);
        nesScrollView1.setRechargingData(recharging);
        nesScrollView1.setTemperatureData(temperature);
        nesScrollView1.setTimeData(timeData);
        nesScrollView1.setAirData(airData);


        nesScrollView1.initAllView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
