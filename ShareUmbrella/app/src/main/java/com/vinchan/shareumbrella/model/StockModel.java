package com.vinchan.shareumbrella.model;

/**
 * Created by Jian on 2018/6/12.
 */

public class StockModel {
    Integer longUmbrella; //长伞数目
    Integer shorUmbrella;  //短伞数目
    Integer alpenstock;    //登山杖数目
    String siteId;      //站点

    public Integer getLongUmbrella() {
        return longUmbrella;
    }

    public void setLongUmbrella(Integer longUmbrella) {
        this.longUmbrella = longUmbrella;
    }

    public Integer getShorUmbrella() {
        return shorUmbrella;
    }

    public void setShorUmbrella(Integer shorUmbrella) {
        this.shorUmbrella = shorUmbrella;
    }

    public Integer getAlpenstock() {
        return alpenstock;
    }

    public void setAlpenstock(Integer alpenstock) {
        this.alpenstock = alpenstock;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
