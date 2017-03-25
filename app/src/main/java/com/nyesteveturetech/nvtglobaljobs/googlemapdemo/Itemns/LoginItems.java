package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lalu on 1/28/2017.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginItems {

    @SerializedName("tag")
    @Expose
    private String tag;

    @SerializedName("conductorId")
    @Expose
    private String cunductor_Id;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("trip")
    @Expose
    private List<Trip> trip = new ArrayList<>();

    public String getCunductor_Id() {
        return cunductor_Id;
    }

    public void setCunductor_Id(String cunductor_Id) {
        this.cunductor_Id = cunductor_Id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Trip> getTrip() {
        return trip;
    }

    public void setTrip(List<Trip> trip) {
        this.trip = trip;
    }

}

