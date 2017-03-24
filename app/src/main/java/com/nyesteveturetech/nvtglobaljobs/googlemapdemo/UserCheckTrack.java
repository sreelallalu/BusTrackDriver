package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

/**
 * Created by lalu on 1/14/2017.
 */
public class UserCheckTrack extends AppCompatActivity {
    Button _next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _next=(Button)findViewById(R.id.check_vehi);
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                 Intent intent=new Intent(UserCheckTrack.this,MapActivity.class);
                    startActivity(intent);

            }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


    }



}
