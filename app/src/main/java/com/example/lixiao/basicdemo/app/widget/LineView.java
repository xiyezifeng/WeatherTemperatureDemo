package com.example.lixiao.basicdemo.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.widget.bean.BitmapBean;
import com.example.lixiao.basicdemo.app.widget.bean.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;


public class LineView extends View implements OnScrollChangeLisenter{

    /**
     * 记录每个时刻温度的坐标
     */
    private List<Point> points = new ArrayList<>();
    /**
     * 每个小时之间间隔距离  px
     */
    private int setpSize = 110;
    /**
     * view本身绘制起点
     */
    private int startEndPadding = 110;

    /**
     * view本身绘制起点
     */
    private int topPadding = 50;
    private int bottomPadding = 50;

    /**
     * view本赋予绘制最大高度
     */
    private int viewHeight = 150;

    /**
     * 度数文字尺寸
     */
    private int textSize = 14;

    /**
     * 度数文字偏移量Y轴
     */
    private int textYOffsite = 13;

    /**
     * 圆的半径
     */
    private int cirRad = 5;


    /**
     * 绘制时折线颜色颜色  白色
     */
    private int lineColor;

    /**
     * 绘制时折线上的点颜色  白色
     */
    private int cirColor;
    /**
     * 折线上的温度文字
     */
    private List<String> lineTextList;
    /**
     * 折线上的温度文字颜色 白色
     */
    private int textColor;

    /**
     * 绘制时虚线颜色颜色  白色
     */
    private int imaginaryColor;
    /**
     * 虚线宽度 1
     */
    private int imaginaryLineWidth = 1;

    /**
     * 折线画笔
     */
    private Paint linePaint;
    /**
     * 连接点画笔
     */
    private Paint cirPaint;
    /**
     * 温度画笔
     */
    private Paint textPaint;


    /**
     * 虚线画笔
     */
    private Paint imaginaryPaint;

    /**
     * 模拟温度数据
     */
    private int temperature[] = {};

    /**
     * 模拟天气分段时间
     */
    private int recharging[]  = {};


    private int bitmapRes[] = {};


    public int bitmapSize = 30;

    public int getBitmapDarwTopMargin(){
        return topPadding + viewHeight + (bottomPadding - bitmapSize) / 2;
    }

    public void setBitmapRes(int[] bitmapRes) {
        this.bitmapRes = bitmapRes;
    }

    /**
     * 设置温度
     * @param temperature
     */
    public void setTemperature(int[] temperature) {
        this.temperature = temperature;
    }


    /**
     * 设置分段时间
     * @param recharging
     */
    public void setRecharging(int[] recharging) {
        this.recharging = recharging;
    }



    private List<BitmapBean> bitmapBeans = new ArrayList<>();

    public List<BitmapBean> getBitmapBeans() {
        return bitmapBeans;
    }


    public static int[] MAXMIN(int[] array) {
        int maxIndex = array[0];//定义最大值为该数组的第一个数
        int minIndex = array[0];//定义最小值为该数组的第一个数
        //遍历循环数组
        System.out.print("这个数组为：");
        for (int i = 0; i < array.length; i++) {
            if (maxIndex < array[i]) {
                maxIndex = array[i];
            }
            if (minIndex > array[i]) {
                minIndex = array[i];
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("这个数组的最大值为：" + maxIndex + "\t最小值为：" + minIndex);
        return new int[]{maxIndex,minIndex};
    }

    private Canvas canvas;

    private int viewTrueHeight,viewTrueWidth;

    public LineView(Context context) {
        super(context);
//        init();
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewTrueWidth = (24 - 1) * setpSize + 2 * startEndPadding;
        viewTrueHeight = viewHeight + topPadding + bottomPadding;
        setMeasuredDimension(viewTrueWidth, viewTrueHeight);
    }


    public void drawView(){
        init();
        invalidate();
    }


    private void init(){
        points = new ArrayList<>();
        int[] temp = MAXMIN(temperature);
        int maxTmp = temp[0];
        int minTmp = temp[1];
        int maxOffset = Math.abs(maxTmp - minTmp);
        Log.e("lineview", "最大差值   :   " + maxOffset);
        int perHeight = viewHeight / maxOffset;
        Log.e("lineview", "每度像素值   :   " + perHeight);
        boolean isminus = false;
        if (minTmp < 0) {
            isminus = true;
        }
        int[] tempArray = new int[temperature.length];
            for (int i = 0; i < temperature.length; i++) {
                int a ;
                if (isminus) {
                    a = temperature[i] + Math.abs(minTmp);
                }else{
                    a = temperature[i];
                }
                int y = a * perHeight;
                int half = viewHeight / 2;
                if (y > half ) {
                    int z = (y - half ) * 2 ;
                    int d = z / perHeight;//误差度数
                    tempArray[i] = a - d;
                }else{
                    int z = (half  - y) * 2 ;
                    int d = z / perHeight;//误差度数
                    tempArray[i] = a + d;
                }

            }

        for (int i = 0; i < 24; i++) {
            int y = tempArray[i] * perHeight;
            points.add(new Point(setpSize * i + startEndPadding,  y + bottomPadding ));
        }
        lineColor = Color.WHITE;
        cirColor = Color.WHITE;
        lineTextList = new ArrayList<>();
        textColor = Color.WHITE;
        imaginaryColor = Color.WHITE;


        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);
//        linePaint.setStrokeJoin(Paint.Join.ROUND );


        cirPaint = new Paint();
        cirPaint.setColor(cirColor);
        cirPaint.setAntiAlias(true);
        cirPaint.setDither(true);
        cirPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        cirPaint.setStrokeWidth(2);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(textSize);

        imaginaryPaint = new Paint();
        imaginaryPaint.setColor(textColor);
        imaginaryPaint.setAntiAlias(true);
        imaginaryPaint.setDither(true);
        imaginaryPaint.setStyle(Paint.Style.STROKE);
        imaginaryPaint.setTextSize(textSize);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        imaginaryPaint.setPathEffect(new DashPathEffect(new float[]{4, 4}, 0));
    }
    private int currentX;
    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        Path path = new Path();
        Point point;
        for (int i = 0; i < points.size(); i++) {
            point = points.get(i);
            if (i == 0) {
                path.moveTo(point.getX(), point.getY());
                canvas.drawCircle(point.getX(),point.getY(),cirRad,cirPaint);
                //绘制文字
                String title = temperature[i] + "°";
                canvas.drawText(title,point.getX() -  xOffset(title) , point.getY() - textYOffsite , textPaint);
                //绘制虚线
                canvas.drawLine(point.getX(),point.getY(),point.getX() , viewTrueHeight    ,imaginaryPaint);
                currentX = point.getX();
                continue;
            }
            path.lineTo( point.getX(), point.getY());
            canvas.drawCircle(point.getX(),point.getY(),5,cirPaint);
            //绘制文字
            String title = temperature[i] + "°";

            canvas.drawText(title , point.getX() -  xOffset(title)  , point.getY() - textYOffsite , textPaint);


            for (int j = 0; j < recharging.length; j++) {
                if (i == recharging[j]) {
                    drawImaginary(point, j - 1);
                }
            }
        }
        if (!points.isEmpty())
            canvas.drawPath(path, linePaint);
        super.onDraw(canvas);
    }

    /**
     * 绘制分段线
     * @param point
     */
    private void drawImaginary(Point point , int j){
        canvas.drawLine(point.getX(), point.getY(), point.getX(), viewTrueHeight, imaginaryPaint);
        BitmapBean bitmapBean = new BitmapBean();
        bitmapBean.setStartX(currentX);
        bitmapBean.setEndx(point.getX());
        bitmapBean.setResId(bitmapRes[j]);
        bitmapBean.setSize(bitmapSize);
        bitmapBean.setMaxoffset(((point.getX() - currentX) - bitmapSize));
        bitmapBean.setOffset(0);
        bitmapBeans.add(bitmapBean);
        currentX = point.getX();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

    }

    private float xOffset (String title){
        float temp = (float) textSize / 4;
        switch (title.length()-1) {
            case 1:
                return temp;
            case 2:
                return temp * 2;
            case 3:
                return temp * 3;
            case 4:
                return textSize;
        }
        return 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("lineview", "touchevent   :   " + event.getRawX());
        return super.onTouchEvent(event);
    }

    @Override
    public void onScrollChange(int l, int t, int oldl, int oldt) {
        Log.e("lineview", "onScrollChange");
    }
}
