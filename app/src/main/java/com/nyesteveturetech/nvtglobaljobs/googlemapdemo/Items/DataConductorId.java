package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Items;

/**
 * Created by lalu on 3/25/2017.
 */

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Trip;

import java.util.List;



public class DataConductorId {
    private static DataConductorId dataObject = null;

    private DataConductorId() {
        // left blank intentionally
    }

    public static DataConductorId getInstance() {
        if (dataObject == null)
            dataObject = new DataConductorId();
        return dataObject;
    }
    private  String distributor_id;;

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {

        this.distributor_id = distributor_id;
    }
}
