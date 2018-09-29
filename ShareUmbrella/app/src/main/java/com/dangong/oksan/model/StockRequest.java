package com.dangong.oksan.model;

/**
 * Created by vinchan on 2018/9/29.
 */


public class StockRequest {
    private int longUmbrella; //长伞数目
    private int shorUmbrella; //短伞数目
    private int alpenstock; //登山杖数目
    private String siteId; //站点
    private String phone; //维护人员账号

    public int getLongUmbrella() {
        return longUmbrella;
    }

    public void setLongUmbrella(int longUmbrella) {
        this.longUmbrella = longUmbrella;
    }

    public int getShorUmbrella() {
        return shorUmbrella;
    }

    public void setShorUmbrella(int shorUmbrella) {
        this.shorUmbrella = shorUmbrella;
    }

    public int getAlpenstock() {
        return alpenstock;
    }

    public void setAlpenstock(int alpenstock) {
        this.alpenstock = alpenstock;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
