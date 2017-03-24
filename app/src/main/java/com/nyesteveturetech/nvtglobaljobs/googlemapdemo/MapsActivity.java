package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae.DATABASE_C;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae.MainDataBAse;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae.Trip2;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.LoginItems;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Stop;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Trip;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Items.DataHolder;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.WebService.RestBuilderPro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity {

    private String con_name,con_pass;
    private EditText _name,_pass;

    private Button _login,click;
    SimpleDateFormat standard;
    private ProgressDialog dialog;
    String currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _login=(Button)findViewById(R.id.user_lo_buttonbn);
        _name=(EditText)findViewById(R.id.bus_email);
        _pass=(EditText)findViewById(R.id.bus_pass);
        Calendar c = Calendar.getInstance();
        standard = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        currentDate=standard.format(c.getTime());
        dialog=new ProgressDialog(MapsActivity.this);


       /*
        try {
            Date start=df.parse(date);
            Date end=df.parse(serDate);
            long different = end.getTime() - start.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;

            long elapsedMinutes = different / minutesInMilli;


            Log.e("Date",""+elapsedMinutes);


        } catch (ParseException e) {
            e.printStackTrace();
        }*/




        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(v);
            }
        });






        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
    }

    private void check(View v) {


        String name_=_name.getText().toString();
        String pass_=_pass.getText().toString();
        if(name_.isEmpty()){
            _name.setError("empty");
            return;
        }
        else
        if(pass_.isEmpty()||pass_.length()<4)
        {
            _pass.setError("atleast 4");
            return;
        }
       dialog.show();
        RestBuilderPro.getService().authenticate("logincheck",name_,pass_).enqueue(new Callback<LoginItems>() {
            @Override
            public void onResponse(Call<LoginItems> call, Response<LoginItems> response) {

                if(response.isSuccessful())
                {

                final LoginItems logj=response.body();
                    Log.e("response ",""+logj.toString());

                    String suss=logj.getSuccess();
                    Log.e("response ",""+suss);

                    if(suss.contains("1"))
                    {


                    final List<Trip> tripItems=logj.getTrip();
                        Log.e("response ",""+tripItems.toString());

                        DataHolder.getInstance().setDistributor_id(tripItems);



        MainDataBAse dataBAse=new MainDataBAse(MapsActivity.this);
        dataBAse.open();
        try {
           boolean b= dataBAse.check(DATABASE_C.DATABASETABLENAME);
            if(b){
                Log.e("check",""+b);



                dataBAse.createuserTable(tripItems);
            }else{
                dataBAse.Delete(DATABASE_C.DATABASETABLENAME);
                dataBAse.createuserTable(tripItems);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        dataBAse.close();
                        dialog.dismiss();
         Intent intent=new Intent(MapsActivity.this,SlideMAin.class);
           startActivity(intent);
                        finish();








                    }else{
                        Toast.makeText(MapsActivity.this, "error login", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                }
            }

            @Override
            public void onFailure(Call<LoginItems> call, Throwable t) {
                dialog.dismiss();
            }
        });


    }


    // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

}
