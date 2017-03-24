package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Trip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;



public class MainDataBAse {

    private DbHelper myHelper;
    private final Context myContext;
    private SQLiteDatabase myDatabase;



    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_C.DATABASENAME, null, DATABASE_C.DATABSEVERSION);
//TODO Auto-generated constructor stub

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

// TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + DATABASE_C.DATABASETABLENAME + " (" +
                    DATABASE_C.TRIP.T_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATABASE_C.TRIP.T_code + " TEXT NOT NULL, " +
                    DATABASE_C.TRIP.T_agencyID + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_agency + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_Proprietor + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_busId + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_bName + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_bNum + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_sce + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_sTime + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_dn + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_salry + " TEXT NOT NULL," +
                    DATABASE_C.TRIP.T_deTime + " TEXT NOT NULL);"
            );
            db.execSQL("CREATE TABLE " + DATABASE_C.STOPSTABLENAME+ " (" +
                    DATABASE_C.STOP.S_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATABASE_C.STOP.S_tripid + " TEXT NOT NULL, " +
                    DATABASE_C.STOP.S_tripcode + " TEXT NOT NULL," +
                    DATABASE_C.STOP.S_place+ " TEXT NOT NULL," +
                    DATABASE_C.STOP.S_time + " TEXT NOT NULL," +
                    DATABASE_C.STOP.S_lat + " TEXT NOT NULL," +
                    DATABASE_C.STOP.S_log + " TEXT NOT NULL);"
            );


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_C.DATABASETABLENAME);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_C.STOPSTABLENAME);
            onCreate(db);
        }


    }


    public MainDataBAse(Context c){
        myContext = c;
    }


    public MainDataBAse open() throws SQLException {
        myHelper = new DbHelper(myContext);
        myDatabase = myHelper.getWritableDatabase();
        return this;
    }
    public boolean check(String tablename){
        boolean b=false;
        Cursor cursor = myDatabase.rawQuery("SELECT count(*) FROM "+tablename,null);
        cursor.moveToFirst();
        if (cursor.getInt(0) > 0)
        {
            b=false;
        }if(cursor.getInt(0)==0)
        {
            b=true;
        }
        cursor.close();
        return b;
    }

    public  void Delete(String tablename)
    {

        myDatabase.execSQL("Delete from "+tablename);
       myDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name='"+tablename+"';");

    }

    public void close(){
        myHelper.close();
    }

    public long createuserTable(List<Trip> item)throws Exception
    {     ContentValues cve = new ContentValues();
        ContentValues cve1 = new ContentValues();

        for(int i=0;i<item.size();i++) {
            cve.put(DATABASE_C.TRIP.T_code, item.get(i).getTripCode());
            cve.put(DATABASE_C.TRIP.T_agencyID, item.get(i).getAgencyID());
            cve.put(DATABASE_C.TRIP.T_agency, item.get(i).getAgencyName());
            cve.put(DATABASE_C.TRIP.T_Proprietor, item.get(i).getAgencyProprietor());
            cve.put(DATABASE_C.TRIP.T_busId, item.get(i).getBusId());
            cve.put(DATABASE_C.TRIP.T_bName, item.get(i).getBusName());
            cve.put(DATABASE_C.TRIP.T_bNum, item.get(i).getBusNumber());
            cve.put(DATABASE_C.TRIP.T_sce, item.get(i).getSource());
            cve.put(DATABASE_C.TRIP.T_sTime, item.get(i).getDate()+" "+item.get(i).getSourceTime());
            cve.put(DATABASE_C.TRIP.T_dn, item.get(i).getDestination());
            cve.put(DATABASE_C.TRIP.T_salry, item.get(i).getSalary());
            cve.put(DATABASE_C.TRIP.T_deTime, item.get(i).getDate()+" "+item.get(i).getDestinationTime());
           myDatabase.insert(DATABASE_C.DATABASETABLENAME, null, cve);



        }
        return 1;
    }
    public long createuserTable1(List<Trip> item)throws Exception {
        ContentValues cve1 = new ContentValues();

        for(int i=0;i<item.size();i++)
        {
        int h=item.get(i).getStops().size();
        if(h!=0) {
            for (int j = 0; j < h; h++) {
                cve1.put(DATABASE_C.STOP.S_tripid, item.get(i).getStops().get(j).getTripId());
                cve1.put(DATABASE_C.STOP.S_tripcode, item.get(i).getStops().get(j).getTripCode());
                cve1.put(DATABASE_C.STOP.S_place, item.get(i).getStops().get(j).getPlace());
                cve1.put(DATABASE_C.STOP.S_time, item.get(i).getStops().get(j).getTime());
                cve1.put(DATABASE_C.STOP.S_lat, item.get(i).getStops().get(j).getLatitude());
                cve1.put(DATABASE_C.STOP.S_log, item.get(i).getStops().get(j).getLongitude());
                myDatabase.insert(DATABASE_C.STOPSTABLENAME, null, cve1);

            }
        }
        }




            return 1;
    }

    public List<Trip2> getNameUser()throws SQLException
    {
        List<Trip2> uname =new ArrayList<>();
        String[] columns = new String[]{
                DATABASE_C.TRIP.T_id,
                DATABASE_C.TRIP.T_code,
                DATABASE_C.TRIP.T_agencyID ,
                DATABASE_C.TRIP.T_agency,
                DATABASE_C.TRIP.T_Proprietor,
                DATABASE_C.TRIP.T_busId,
                DATABASE_C.TRIP.T_bName,
                DATABASE_C.TRIP.T_bNum,
                DATABASE_C.TRIP.T_sce,
                DATABASE_C.TRIP.T_sTime,
                DATABASE_C.TRIP.T_dn,
                DATABASE_C.TRIP.T_salry,
                DATABASE_C.TRIP.T_deTime};
        Cursor c = myDatabase.query(DATABASE_C.DATABASETABLENAME, columns,null,null,null,null,null);

        while(c.moveToNext()){
            uname.add(new Trip2(
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_id)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_code)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_agencyID)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_agency)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_Proprietor)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_busId)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_bName)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_bNum)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_sce)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_sTime)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_dn)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_salry)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_deTime))
            ));

        }

        return uname;

    }

    public List<Trip2> getTimeId(String _time)
    {
        List<Trip2> uname =new ArrayList<>();
        Cursor c = myDatabase.rawQuery("SELECT * FROM "+DATABASE_C.DATABASETABLENAME+" WHERE "+DATABASE_C.TRIP.T_sTime
                +" >= '"+ _time + "' ORDER BY "+DATABASE_C.TRIP.T_sTime+" LIMIT 1",
                null);
       while (c.moveToNext())
            {
            uname.add(new Trip2(
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_id)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_code)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_agencyID)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_agency)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_Proprietor)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_busId)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_bName)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_bNum)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_sce)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_sTime)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_dn)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_salry)),
                    c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_deTime))

            ));
           // Log.e("time",c.getString(c.getColumnIndex(DATABASE_C.TRIP.T_sTime)));

        }






        return uname;
    }




























}
