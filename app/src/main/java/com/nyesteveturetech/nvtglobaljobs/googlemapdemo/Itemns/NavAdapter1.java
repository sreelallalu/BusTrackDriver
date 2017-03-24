package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.R;

import java.util.ArrayList;

/**
 * Created by lalu on 2/8/2017.
 */





public class NavAdapter1  extends BaseAdapter {

    private Context context;
    private ArrayList<NavStop> navDrawerItems;

    public NavAdapter1 (Context context, ArrayList<NavStop> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item1, null);
        }

        TextView place = (TextView) convertView.findViewById(R.id.fplace);
        TextView time = (TextView) convertView.findViewById(R.id.ftimed);
        // TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

        place.setText(navDrawerItems.get(position).getPlace());
        time.setText(navDrawerItems.get(position).getTime());

        // displaying count
        // check whether it set visible or not

        return convertView;
    }

}
