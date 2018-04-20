package com.liurui.common.assist;

/**
 * Created by LiuRui on 2018/4/17.
 */

public class Gps {

    private double lat;

    private double lng;

    public Gps(double wgLat, double wgLon) {
        setLat(wgLat);
        setLng(wgLon);
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
