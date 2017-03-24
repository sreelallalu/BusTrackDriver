package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalu on 2/5/2017.
 */



public class Trip {

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
    @SerializedName("agencyID")
    @Expose
    private String agencyID;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("agencyName")
    @Expose
    private String agencyName;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("agencyProprietor")
    @Expose
    private String agencyProprietor;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("busId")
    @Expose
    private String busId;
    @SerializedName("6")
    @Expose
    private String _6;
    @SerializedName("busName")
    @Expose
    private String busName;
    @SerializedName("7")
    @Expose
    private String _7;
    @SerializedName("busNumber")
    @Expose
    private String busNumber;
    @SerializedName("8")
    @Expose
    private String _8;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("9")
    @Expose
    private String _9;
    @SerializedName("source_time")
    @Expose
    private String sourceTime;
    @SerializedName("10")
    @Expose
    private String _10;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("11")
    @Expose
    private String _11;
    @SerializedName("destination_time")
    @Expose
    private String destinationTime;
    @SerializedName("12")
    @Expose
    private String _12;
    @SerializedName("salary")
    @Expose
    private String salary;

    @SerializedName("date")
    @Expose
    private String date;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @SerializedName("stops")
    @Expose

    private List<Stop> stops = new ArrayList<>();

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

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }

    public String get3() {
        return _3;
    }

    public void set3(String _3) {
        this._3 = _3;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String get4() {
        return _4;
    }

    public void set4(String _4) {
        this._4 = _4;
    }

    public String getAgencyProprietor() {
        return agencyProprietor;
    }

    public void setAgencyProprietor(String agencyProprietor) {
        this.agencyProprietor = agencyProprietor;
    }

    public String get5() {
        return _5;
    }

    public void set5(String _5) {
        this._5 = _5;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String get6() {
        return _6;
    }

    public void set6(String _6) {
        this._6 = _6;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String get7() {
        return _7;
    }

    public void set7(String _7) {
        this._7 = _7;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String get8() {
        return _8;
    }

    public void set8(String _8) {
        this._8 = _8;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String get9() {
        return _9;
    }

    public void set9(String _9) {
        this._9 = _9;
    }

    public String getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }

    public String get10() {
        return _10;
    }

    public void set10(String _10) {
        this._10 = _10;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String get11() {
        return _11;
    }

    public void set11(String _11) {
        this._11 = _11;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String get12() {
        return _12;
    }

    public void set12(String _12) {
        this._12 = _12;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

}