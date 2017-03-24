package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae.MainDataBAse;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae.Trip2;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavDrawerItem;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavDrawerListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lalu on 2/8/2017.
 */
public class ImgMain extends Fragment{


    ListView listview;
    private int id;
    SimpleDateFormat standard;
    ArrayList<NavDrawerItem> navaItem;

    String currentDate;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.img_main, container, false);
        findidz(rootView);



        return  rootView;
    }

      private void findidz(View rootView) {
        listview=(ListView)rootView.findViewById(R.id.listviewsd);

              navaItem=new ArrayList<>();

          Calendar c = Calendar.getInstance();
          standard = new SimpleDateFormat("yyyy-MM-dd HH:mm");

          currentDate=standard.format(c.getTime());


          MainDataBAse dataBAse=new MainDataBAse(getActivity());
          dataBAse.open();
          //List<Trip2> root=dataBAse.getTimeId("2017-02-05 12:03");
          List<Trip2> root=dataBAse.getTimeId(currentDate);

     if(root.size()!=0) {


         String time = root.get(0).getSourceTime();
         String source = root.get(0).getSource();
         String destination = root.get(0).getDestination();
         String trip = root.get(0).getTripCode();
         id = Integer.parseInt(root.get(0).get_tripid());
         dataBAse.close();

         navaItem.add(new NavDrawerItem("TRIP 1", source, destination, time));
         NavDrawerListAdapter adapter = new NavDrawerListAdapter(getActivity(), navaItem);
         listview.setAdapter(adapter);
         listview.setOnItemClickListener(new OnItems());
     }else{

         Toast.makeText(getActivity(), "current trip empty", Toast.LENGTH_SHORT).show();
     }
    }

    private class OnItems implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            display(position);
        }
    }

    private void display(int position) {
        Intent ptrip=new Intent(getActivity(),StopPlace.class);
        ptrip.putExtra("position",(id-1));
        startActivity(ptrip);



    }
}
