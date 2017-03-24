package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavAdapter1;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavDrawerItem;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavDrawerListAdapter;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavStop;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Trip;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Items.DataHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalu on 2/8/2017.
 */
public class StopPlace extends AppCompatActivity {

   private  TextView _source,_desination,_stime,_dtime,_tripno;
   private ListView listView;
    private Button startj;

    ArrayList<NavStop> navItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triplist);

        Intent intent=getIntent();
        final int po=intent.getIntExtra("position",1);
        Log.e("ui",""+po);
        navItem=new ArrayList<>();

        startj=(Button)findViewById(R.id.statrtjr);
        _tripno=(TextView)findViewById(R.id.tripnol);
        _source=(TextView)findViewById(R.id.sourceplace);
        _desination=(TextView)findViewById(R.id.destiplace);
        _stime=(TextView)findViewById(R.id.sourcetime);
        _dtime=(TextView)findViewById(R.id.desttime);
        listView=(ListView)findViewById(R.id.liststops);
        List<Trip> list= DataHolder.getInstance().getDistributor_id();
        if(list.size()!=0) {
            String source = list.get(po).getSource();
            String destination = list.get(po).getDestination();
            String date = list.get(po).getDate();
            String sourcetime = list.get(po).getSourceTime();
            String destinationtime = list.get(po).getDestinationTime();

            _tripno.setText("Trip " + po);
            _source.setText(source);

            _desination.setText(destination);
            _stime.setText(date + " " + sourcetime);
            _dtime.setText(date + " " + destinationtime);

            int h = list.get(po).getStops().size();
            for (int j = 0; j < h; j++) {
                navItem.add(new NavStop(list.get(po).getStops().get(j).getPlace(), list.get(po).getStops().get(j).getTime()));

            }
            NavAdapter1 adapter = new NavAdapter1(StopPlace.this, navItem);
            listView.setAdapter(adapter);
            startj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StopPlace.this, MapActivity.class);
                    intent.putExtra("sposition", po);
                    startActivity(intent);
                    finish();


                }
            });

        }

    }
}
