package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lalu on 2/5/2017.
 */

public class Stop {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("tripId")
    @Expose
    private String tripId;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("trip_code")
    @Expose
    private String tripCode;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String get1() {
        return _1;
    }

    public void set1(String _1) {
        this._1 = _1;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public String get2() {
        return _2;
    }

    public void set2(String _2) {
        this._2 = _2;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String get3() {
        return _3;
    }

    public void set3(String _3) {
        this._3 = _3;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String get4() {
        return _4;
    }

    public void set4(String _4) {
        this._4 = _4;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String get5() {
        return _5;
    }

    public void set5(String _5) {
        this._5 = _5;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
