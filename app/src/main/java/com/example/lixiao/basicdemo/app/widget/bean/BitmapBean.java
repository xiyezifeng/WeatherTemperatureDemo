package com.example.lixiao.basicdemo.app.widget.bean;

public class BitmapBean {
    private int startX;
    private int endx;
    private int resId;
    private int size;
    private int offset;
    private int maxoffset;
    public BitmapBean() {
    }

    public BitmapBean(int startX, int endx, int resId, int size, int offset) {
        this.startX = startX;
        this.endx = endx;
        this.resId = resId;
        this.size = size;
        this.offset = offset;
    }

    public int getMaxoffset() {
        return maxoffset;
    }

    public void setMaxoffset(int maxoffset) {
        this.maxoffset = maxoffset;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndx() {
        return endx;
    }

    public void setEndx(int endx) {
        this.endx = endx;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
