
package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by lalu on 3/13/2017.
 */
public class StopsD {
    String stopplace;
    LatLng latitude;

    public String getStopplace() {
        return stopplace;
    }

    public void setStopplace(String stopplace) {
        this.stopplace = stopplace;
    }

    public LatLng getLatitude() {
        return latitude;
    }

    public void setLatitude(LatLng latitude) {
        this.latitude = latitude;
    }

    public StopsD(String stopplace, LatLng latitude) {
        this.stopplace = stopplace;
        this.latitude = latitude;
    }
}
