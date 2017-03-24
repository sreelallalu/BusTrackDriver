package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns;

/**
 * Created by lalu on 2/9/2017.
 */

public class NavStop {
    private String place;
    private String time;

    public NavStop(String place, String time) {
        this.place = place;
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
