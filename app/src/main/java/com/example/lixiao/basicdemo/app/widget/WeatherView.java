package com.example.lixiao.basicdemo.app.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.widget.bean.BitmapBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.http.GET;

public class WeatherView extends FrameLayout implements OnScrollChangeLisenter{
    private static final String TAG = "WeatherView";
    private LineView lineView;
    private int lineViewHeight , lineViewWidth;

    /**
     * 折线图距离底部的间距
     */
    private int padding = 15;
    /**
     * air布局和time布局间距
     */
    private int padding2 = 15;
    /**
     * 每个小块宽度
     */
    private int chunkWidth = 110;

    /**
     * 底部时间文字布局
     */
    private FrameLayout timeLayout;
    private boolean isLayoutTimeLayout;
    private int timeTextHeight = 40;
    private int timeTextWidth = 80;

    /**
     * 底部空气质量文字布局
     */
    private FrameLayout airLayout;
    private boolean isLayoutAirLayout;
    private int airTextHeight = 35;
    private int airTextWidth = 100;


    private FrameLayout bitmapLayout;
    private boolean isLayoutBitmapLayout;
    private boolean isDrawBitmap;


    private int viewLeft,viewRight;
    private int viewTrueWidth;



    private List<BitmapBean> bitmapBeans = new ArrayList<>();

    public WeatherView(@NonNull Context context) {
        super(context);
        init();
    }

    public WeatherView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeatherView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        addTimeLayout();
        addAirQuality();
        //绘制图标
        addBitmap();
    }

    public void initLineView(){
        lineView = new LineView(getContext());
        FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
        lineView.setLayoutParams(params);
        lineView.setTemperature(temperature);
        lineView.setRecharging(rechargint);
        lineView.setBitmapRes(bitmapRes);
        addView(lineView);
        lineView.drawView();
    }





    private void addAirQuality(){
        airLayout = new FrameLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
        airLayout.setLayoutParams(params);
        addView(airLayout);
//        int itemWidth = chunkWidth - airTextWidth / 2;
        /*for (int i = 0; i < 24; i++) {
            LayoutParams params1 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,Gravity.CENTER_VERTICAL);
            params1.leftMargin = itemWidth + i * chunkWidth;
            airLayout.addView(createAirTextView(i + ":00"), params1);
        }*/

    }
    private ArrayList<String> airdata;

    public void setAirdata(ArrayList<String> airdata) {
        this.airdata = airdata;
    }

    public void darwAirData(){
        int itemWidth = chunkWidth - airTextWidth / 2;
        for (int i = 0; i < airdata.size(); i++) {
            LayoutParams params1 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,Gravity.CENTER_VERTICAL);
            params1.leftMargin = itemWidth + i * chunkWidth;
            airLayout.addView(createAirTextView(airdata.get(i)), params1);
        }
    }

    public void drawAllView(){
        initLineView();
        darwAirData();
        drawTimeData();
    }

    private TextView createAirTextView(String title){
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(9);
        textView.setText(title);
        textView.setWidth(airTextWidth);
        textView.setHeight(airTextHeight);
        textView.setBackground(getResources().getDrawable(R.drawable.shape_air_text_view));
        textView.setGravity(Gravity.CENTER);
        return textView;

    }
    private void addTimeLayout(){
        timeLayout = new FrameLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
        timeLayout.setLayoutParams(params);
        addView(timeLayout);

        View line = new View(getContext());
        LayoutParams linep = new LayoutParams(LayoutParams.MATCH_PARENT , 1);
        linep.leftMargin = 50;
        linep.rightMargin = 50;
        line.setBackgroundColor(Color.WHITE);
        timeLayout.addView(line, linep);
    }

    public void drawTimeData(){
        int itemWidth = chunkWidth - timeTextWidth / 2;
        for (int i = 0; i < timedata.size(); i++) {
            LayoutParams params1 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,Gravity.CENTER_VERTICAL);
            params1.leftMargin = itemWidth + i * chunkWidth;
            timeLayout.addView(createTextView(timedata.get(i)), params1);
        }
    }

    private ArrayList<String> timedata;

    public void setTimedata(ArrayList<String> timedata) {
        this.timedata = timedata;
    }


    private TextView createTextView(String title){
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(11);
        textView.setText(title);
        textView.setWidth(timeTextWidth);
        textView.setHeight(timeTextHeight);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        viewLeft = 0;
        viewTrueWidth = dm.widthPixels - (dip2px(getContext(), 10));
        viewRight = dm.widthPixels - (dip2px(getContext(), 10));
        Log.e(TAG, "onLayout + " + getWidth());

    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed,left,top ,right ,bottom );
        if (lineView != null) {
            lineViewWidth = lineView.getWidth();
            lineViewHeight = lineView.getHeight();
            lineView.post(new Runnable() {
                @Override
                public void run() {
                    bitmapBeans = lineView.getBitmapBeans();
                    Log.e(TAG, ""+bitmapBeans.size());
                    if (!isDrawBitmap) {
                        drawBitmap();
                        isDrawBitmap = true;
                    }
                }
            });
        }


        if (!isLayoutAirLayout) {
            FrameLayout.LayoutParams params = (LayoutParams) airLayout.getLayoutParams();
            params.width = lineViewWidth;
            params.topMargin = lineViewHeight + padding;
            airLayout.setLayoutParams(params);
            isLayoutAirLayout = true;
        }

        if (!isLayoutTimeLayout) {
            FrameLayout.LayoutParams params = (LayoutParams) timeLayout.getLayoutParams();
            params.width = lineViewWidth;
            params.topMargin = lineViewHeight + padding + padding2 + airTextHeight;
            timeLayout.setLayoutParams(params);
            isLayoutTimeLayout = true;
        }

        if (!isLayoutBitmapLayout) {
            FrameLayout.LayoutParams params = (LayoutParams) bitmapLayout.getLayoutParams();
            params.width = lineViewWidth - chunkWidth * 2;
            params.leftMargin = chunkWidth;
            params.height = 40;
            params.topMargin = lineView.getBitmapDarwTopMargin();
            bitmapLayout.setLayoutParams(params);
            isLayoutBitmapLayout = true;
        }

    }


    private void addBitmap() {
        bitImageViews = new ArrayList<>();
        bitmapLayout = new FrameLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
        bitmapLayout.setLayoutParams(params);
        addView(bitmapLayout);
    }

    private List<ImageView> bitImageViews;
    private void drawBitmap(){
        //bitmapBeans.size()
        for (int i = 0; i < bitmapBeans.size(); i++) {
            BitmapBean bitmapBean = bitmapBeans.get(i);
            ImageView view = new ImageView(getContext());
            view.setImageResource(bitmapBean.getResId());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmapBean.getSize(), bitmapBean.getSize());
            params.gravity = Gravity.CENTER_VERTICAL;
            params.leftMargin = ( bitmapBean.getStartX() -  chunkWidth);
            view.setLayoutParams(params);
            bitmapLayout.addView(view);
            bitImageViews.add(view);
            layoutBitmap(bitmapBean, view);
        }
    }

    private void layoutBitmap(BitmapBean bitmapBean , ImageView view){
        //滑动过程中  始终让bitmap保持在左边界和右边界的中心位置
        int left = 0;
        int right = 0;
        if (bitmapBean.getStartX() >= viewLeft && bitmapBean.getEndx() <= viewRight) {
            //两端都处于屏幕上
            left = bitmapBean.getStartX();
            right = bitmapBean.getEndx();
            int offset = (right - left - bitmapBean.getSize()) / 2 ;
            view.setTranslationX(offset);
            bitmapBean.setOffset(offset);
            return;
        } else if (bitmapBean.getEndx() < viewLeft) {
            //两端都处于屏幕的左边
            view.setTranslationX(bitmapBean.getMaxoffset());
            bitmapBean.setOffset(bitmapBean.getMaxoffset());
            return;
        } else if (bitmapBean.getStartX() > viewRight) {
            //两端都处于屏幕的右边
            view.setTranslationX(0);
            bitmapBean.setOffset(0);
            return;
        } else if (bitmapBean.getStartX() < viewLeft && bitmapBean.getEndx() > viewRight) {
            //两端都在屏幕外，包裹
            left = viewLeft;
            right = viewRight;
            int offset  = right - bitmapBean.getStartX() - ((viewTrueWidth + bitmapBean.getSize()) / 2) ;
            log(" offset   " + offset);
            view.setTranslationX(offset);
            bitmapBean.setOffset(offset);
            return;
        } else if (bitmapBean.getStartX() < viewLeft && bitmapBean.getEndx() > viewLeft) {
            //一端处于屏幕上，左侧
            left = viewLeft;
            right = bitmapBean.getEndx();
            int offset =bitmapBean.getMaxoffset() -  (right - left - bitmapBean.getSize()) / 2 ;
            if (offset >= bitmapBean.getMaxoffset()) {
                offset = bitmapBean.getMaxoffset();
            }
            view.setTranslationX(offset);
            bitmapBean.setOffset(offset);
            return;
        } else if (bitmapBean.getStartX() < viewRight && bitmapBean.getEndx() > viewRight) {
            //一端处于屏幕上，右侧
            left = bitmapBean.getStartX();
            right = viewRight;

            int offset = (right - left - bitmapBean.getSize()) / 2 ;
            if (offset <= 0) {
                offset = 0;
            }
            view.setTranslationX(offset);
            bitmapBean.setOffset(offset);
            return;
        }


    }

    private void log(String str){
        Log.e(TAG,  str);
    }

    @Override
    public void onScrollChange(int l, int t, int oldl, int oldt) {
        for (int i = 0; i < bitmapBeans.size(); i++) {
            BitmapBean bitmapBean = bitmapBeans.get(i);
            ImageView view = bitImageViews.get(i);
            viewLeft = l;
            viewRight = l + viewTrueWidth;
            layoutBitmap(bitmapBean,view);
        }

    }

    private int temperature[];

    public void setTemperatureData(int temperature[]){
        this.temperature = temperature;
    }

    private int rechargint[];
    public void setRechargingData(int rechargint[]){
        this.rechargint = rechargint;
    }
    private int[] bitmapRes;
    public void setBitmapRes(int[] bitmapRes) {
        this.bitmapRes = bitmapRes;

    }

    public void drawView(){
        initLineView();
    }

}
