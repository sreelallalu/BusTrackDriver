package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Items;

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Trip;

import java.util.List;

public class DataHolder {
    private static DataHolder dataObject = null;

    private DataHolder() {
        // left blank intentionally
    }

    public static DataHolder getInstance() {
        if (dataObject == null)
            dataObject = new DataHolder();
        return dataObject;
    }
    private List<Trip> distributor_id;;

    public List<Trip> getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(List<Trip> distributor_id) {

        this.distributor_id = distributor_id;
    }
}
