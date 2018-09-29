package com.dangong.oksan.model;

/**
 * Created by vinchan on 2018/6/12/012.
 */

public class GetNearShopRequestModel {
    private double longitude;
    private double latitude;
    private double range;
    private String city;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
