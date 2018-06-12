package com.vinchan.shareumbrella.model;

/**
 * Created by Jian on 2018/6/12.
 */

public class StockModel {
    int longUmbrella; //长伞数目
    int shorUmbrella;  //短伞数目
    int alpenstock;    //登山杖数目
    String siteId;      //站点

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
}
