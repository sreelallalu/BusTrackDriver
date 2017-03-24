package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by lalu on 2/8/2017.
 */
public class SlideMAin extends AppCompatActivity{
    private ListView listView;
    String arryads[]={"current Trip","Triplist","settings"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidemain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView=(ListView)findViewById(R.id.list_main);

        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_cool);
        ActionBarDrawerToggle toggles = new ActionBarDrawerToggle(
                SlideMAin.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggles);
        toggles.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_cool);
       // navaAdapter=new NavDrawerListAdapter(getApplicationContext(),navaItem);
        ArrayAdapter adapter=new ArrayAdapter(SlideMAin.this,android.R.layout.simple_list_item_1,arryads);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClick());


    }

    private class OnItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            display(position);
        }
    }

    private void display(int position) {

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_cool);
        switch (position) {
            case 0:

                ImgMain fragment = new ImgMain();
                fragmentTransaction.replace(R.id.frameik, fragment);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Current Trip");
                break;
            case 1:
                ImgTRrip fragment1 = new ImgTRrip();
                fragmentTransaction.replace(R.id.frameik, fragment1);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Trip List");
                break;
            case 2:
                SettingFr fragment2 = new SettingFr();
                fragmentTransaction.replace(R.id.frameik, fragment2);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle("Lung Cancer");
                break;
        }
    }
}
