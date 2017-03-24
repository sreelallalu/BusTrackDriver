package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae;

/**
 * Created by SLR on 10/25/2016.
 */
public interface DATABASE_C {

   String DATABASENAME="cancer_databse_db";
    int DATABSEVERSION=1;
    String DATABASETABLENAME="triptable";
    String STOPSTABLENAME="stoptable";
    interface TRIP
    {
        String T_id="tripId";
        String T_code="trip_code";
        String T_agency="agencyID";
        String T_agencyID="agencyName";
        String T_Proprietor="agencyProprietor";
        String T_busId="busId";
        String T_bName="busName";
        String T_bNum="busNumber";
        String T_sce="source";
        String T_sTime="source_time";
        String T_dn="destination";
        String T_deTime="destination_time";
        String T_salry="salary";
        String T_date="date";
        String T_stop="stops";
    }
    interface STOP
    {
        String S_id="stopid";

        String S_tripid="tripId";
        String S_tripcode="trip_code";
        String S_place="place";
        String S_time="time";
        String S_lat="latitude";
        String S_log="longitude";


    }


}
