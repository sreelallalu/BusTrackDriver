package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae.MainDataBAse;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae.Trip2;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavDrawerItem;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.NavDrawerListAdapter;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Trip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalu on 2/8/2017.
 */
public class ImgTRrip extends Fragment {

    ListView listview;

    SimpleDateFormat standard;
    ArrayList<NavDrawerItem> navaItem;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.img_main, container, false);
        findidz(rootView);



        return  rootView;
    }

    private void findidz(View rootView) {
        listview=(ListView)rootView.findViewById(R.id.listviewsd);

        navaItem=new ArrayList<>();


        MainDataBAse databse=new MainDataBAse(getActivity());
        databse.open();
        List<Trip2> _list=databse.getNameUser();


        databse.close();

       for (int i=0;i<_list.size();i++)
       {
           navaItem.add(new NavDrawerItem("TRIP "+(i+1),
                                        _list.get(i).getSource(),
                          _list.get(i).getDestination(),
                   _list.get(i).getSourceTime()));
       }

NavDrawerListAdapter adapter=new NavDrawerListAdapter(getActivity(),navaItem);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItems());



    }
    private class OnItems implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            display(position);
        }
    }

    private void display(int position) {

        Intent ptrip=new Intent(getActivity(),StopPlace.class);
        ptrip.putExtra("position",position);
        startActivity(ptrip);

    }
}
